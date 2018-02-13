package com.tts.ms.bis.report.service;

import com.tts.ms.bis.report.entity.Report;
import com.tts.ms.bis.report.entity.ReportDetail;
import com.tts.ms.bis.report.entity.ReportTask;
import com.tts.ms.bis.report.vo.ReportVo;
import com.tts.ms.resource.request.ReportItemRequest;
import com.tts.ms.resource.request.ReportRequest;
import com.tts.ms.resource.request.ReportTaskRequest;

import java.util.List;

/**
 * 报告接口
 * @Author gaoyan
 * @Date: 2017/12/24
 */
public interface IReportService {

    /**
     * 查询报告列表
     * @param openId
     * @return
     */
    List<Report> queryReports(String openId);

    /**
     * 查询报告任务
     * @param reportId
     * @return
     */
    ReportTask queryReportTask(Long reportId);

    /**
     * 查询报告列表信息
     * @param reportId
     * @param reportType
     * @return
     */
    List<ReportVo> queryReportList(Long reportId,String reportType);

    /**
     * 查询报告综述
     * @param reportId
     * @return
     */
    String queryReportSummary(Long reportId);

    /**
     * 保存报告任务
     * @param request
     */
    Report saveReport(ReportRequest request);

    /**
     * 保存报告任务
     *
     * @param reportId
     * @param request
     * @return
     */
    ReportTask saveReportTask(Long reportId, ReportTaskRequest request);

    /**
     * 更新报告任务
     * @param reportId
     * @param request
     */
    void updateReportTask(Long reportId, ReportTaskRequest request);

    /**
     * 查询报告项
     * @param reportDetailId
     * @return
     */
    ReportDetail queryReportDetail(Long reportDetailId);

    /**
     * 更新报告项
     * @param reportDetailId
     * @param request
     */
    void updateReportItem(Long reportDetailId, ReportItemRequest request);

    /**
     * 保存报告项
     * @param request
     */
    ReportDetail saveReportItem(ReportItemRequest request);

    /**
     * 删除报告项
     * @param reportDetailId
     * @return
     */
    void deleteReportDetail(Long reportDetailId);

    /**
     * 更新报告综述
     * @param reportId
     * @param summary
     */
    void updateReportSummary(Long reportId, String summary);

    /**
     * 更新报告信息
     * @param reportId
     * @param reportName
     */
    void updateReport(Long reportId, String reportName);

}
