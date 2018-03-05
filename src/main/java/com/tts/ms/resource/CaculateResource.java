package com.tts.ms.resource;

import com.tts.ms.bis.report.service.ICaculateService;
import com.tts.ms.bis.report.vo.AqlCaculateResultVo;
import com.xcrm.log.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * aql计算资源
 */
@Path("/caculate")
public class CaculateResource extends BaseAuthedResource{
	
	private static Logger logger = Logger.getLogger(CaculateResource.class);

	@Autowired
	private ICaculateService caculateService;

	/**
	 * 获取报告列表
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<AqlCaculateResultVo> caculate(@NotNull(message = "计算类型不能为空") @QueryParam("caculateType") String caculateType, @QueryParam("shippingSize") Integer shippingSize,
											  @QueryParam("majorValue") String majorValue, @QueryParam("minorValue") String minorValue) {
		logger.debug("CaculateResource.caculate({},{},{},{})", caculateType,shippingSize,majorValue,minorValue);
		return caculateService.caculate(caculateType,shippingSize,majorValue,minorValue);
	}

}