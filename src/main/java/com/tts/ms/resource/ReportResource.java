package com.tts.ms.resource;

import com.tts.ms.bis.report.entity.Report;
import com.tts.ms.bis.report.entity.ReportDetail;
import com.tts.ms.bis.report.entity.ReportTask;
import com.tts.ms.bis.report.service.IReportService;
import com.tts.ms.bis.report.vo.ReportVo;
import com.tts.ms.exception.BizCoreRuntimeException;
import com.tts.ms.resource.request.*;
import com.tts.ms.rest.common.BizErrorConstants;
import com.xcrm.log.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 报告资源
 */
@Path("/report")
public class ReportResource {
	
	private static Logger logger = Logger.getLogger(ReportResource.class);

	@Autowired
	private IReportService reportService;

	/**
	 * 获取报告列表
	 * @param openId
	 * @return
	 */
	@GET
	@Path("/reports")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Report> queryReports(@NotEmpty(message="openId不能为空") @QueryParam("openId") String openId) {
		logger.debug("OrderResource.queryReports({})", openId);
		return reportService.queryReports(openId);
	}

	/**
	 * 保存报告
	 * @param reportRequest
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Report saveReport(@Valid ReportRequest reportRequest) {
		logger.debug("OrderResource.saveReport({})", reportRequest);
		return reportService.saveReport(reportRequest);
	}

	/**
	 * 更新报告名称
	 * @param reportId
	 * @return
	 */
	@PUT
	@Path("/{reportId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateReport(@NotNull(message="报告id不能为空") @PathParam("reportId") Long reportId
			,@Valid UpdateReportRequest updateReportRequest) {
		logger.debug("OrderResource.updateReport({},{})", reportId,updateReportRequest);
		reportService.updateReport(reportId,updateReportRequest.getReportName());
		return Response.ok().build();
	}

	/**
	 * 获取报告任务
	 * @param reportId
	 * @return
	 */
	@GET
	@Path("/{reportId}/task")
	@Produces(MediaType.APPLICATION_JSON)
	public ReportTask queryReportTask(@NotNull(message="报告id不能为空") @PathParam("reportId") Long reportId) {
		logger.debug("OrderResource.queryReportTask({})", reportId);
		return reportService.queryReportTask(reportId);
	}

	/**
	 * 更新报告任务
	 * @return
	 */
	@POST
	@Path("/{reportId}/task")
	@Produces(MediaType.APPLICATION_JSON)
	public ReportTask saveReportTask(@NotNull(message="报告id不能为空") @PathParam("reportId") Long reportId,@Valid ReportTaskRequest request) {
		logger.debug("OrderResource.saveReportTask({})", request);
		return reportService.saveReportTask(reportId,request);
	}

	/**
	 * 更新报告任务
	 * @return
	 */
	@PUT
	@Path("/{reportId}/task")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateReportTask(@NotNull(message="报告id不能为空") @PathParam("reportId") Long reportId,@Valid ReportTaskRequest request) throws URISyntaxException {
		logger.debug("OrderResource.updateReportTask({})", request);
		reportService.updateReportTask(reportId,request);
		return Response.created(new URI("/v1/report/" + reportId)).build();
	}

	/**
	 * 获取具体报告列表
	 * @param reportId
	 * @return
	 */
	@GET
	@Path("/{reportId}/{reportType}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReportVo> queryReportList(@NotNull(message="报告id不能为空") @PathParam("reportId") Long reportId
				,@NotNull(message="报告类型不能为空") @PathParam("reportType") String reportType) {
		logger.debug("OrderResource.queryReportList({},{})", reportId,reportType);
		return reportService.queryReportList(reportId,reportType);
	}

	/**
	 * 保存报告项
	 * @param request
	 * @return
	 */
	@POST
	@Path("/item")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveReportItem(@Valid ReportItemRequest request) {
		logger.debug("OrderResource.saveReportItem({})", request);
		if(Objects.isNull(request.getReportDesc())
				&&Objects.isNull(request.getReportImages())){
			throw new BizCoreRuntimeException(BizErrorConstants.REPORT_ITEM_PARA_ERROR);
		}
		ReportDetail reportDetail = reportService.saveReportItem(request);
		return Response.ok(reportDetail).build();
	}

	/**
	 * 查询报告列表
	 * @param reportDetailId
	 * @return
	 */
	@GET
	@Path("/item/{reportDetailId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response queryReportDetail(@NotNull(message="报告项ID不允许为空") @PathParam("reportDetailId") Long reportDetailId) {
		logger.debug("OrderResource.queryReportDetail({},{})", reportDetailId);
		ReportDetail reportDetail = reportService.queryReportDetail(reportDetailId);
		return Response.ok(reportDetail).build();
	}

	/**
	 * 删除报告列表
	 * @param reportDetailId
	 * @return
	 */
	@DELETE
	@Path("/item/{reportDetailId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteReportDetail(@NotNull(message="报告项ID不允许为空") @PathParam("reportDetailId") Long reportDetailId) {
		logger.debug("OrderResource.deleteReportDetail({},{})", reportDetailId);
		reportService.deleteReportDetail(reportDetailId);
		return Response.noContent().build();
	}

	/**
	 * 更新报告项
	 * @param request
	 * @return
	 */
	@PUT
	@Path("/item/{reportDetailId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateReportItem(@NotNull(message="报告项ID不允许为空") @PathParam("reportDetailId") Long reportDetailId
									 ,@Valid ReportItemRequest request) {
		logger.debug("OrderResource.updateReportItem({})", request);
		if((Objects.isNull(request.getReportDesc())
				&&Objects.isNull(request.getReportImages()))){
			throw new BizCoreRuntimeException(BizErrorConstants.REPORT_ITEM_PARA_ERROR);
		}
		reportService.updateReportItem(reportDetailId,request);
		return Response.noContent().build();
	}

	/**
	 * 获取报告的综述
	 * @param reportId
	 * @return
	 */
	@GET
	@Path("/{reportId}/summary")
	@Produces(MediaType.APPLICATION_JSON)
	public Response queryReportSummary(@NotNull(message="报告id不能为空") @PathParam("reportId") Long reportId) {
		logger.debug("OrderResource.queryReportSummary({})", reportId);
		String reportSummary = reportService.queryReportSummary(reportId);
		Map<String,String> resultMap = new HashMap<>();
		resultMap.put("reportSummary",reportSummary);
		return Response.ok(resultMap).build();
	}

	/**
	 * 更新报告的综述
	 * @param reportId
	 * @return
	 */
	@POST
	@Path("/{reportId}/summary")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateReportSummary(@NotNull(message="报告id不能为空") @PathParam("reportId") Long reportId
			,ReportSummaryRequest request) {
		logger.debug("OrderResource.updateReportSummary({},{})", reportId,request);
		reportService.updateReportSummary(reportId,request.getSummary());
		return Response.ok().build();
	}
}