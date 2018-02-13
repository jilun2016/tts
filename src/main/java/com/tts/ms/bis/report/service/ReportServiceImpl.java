package com.tts.ms.bis.report.service;

import com.tts.ms.bis.report.entity.Report;
import com.tts.ms.bis.report.entity.ReportDetail;
import com.tts.ms.bis.report.entity.ReportTask;
import com.tts.ms.bis.report.vo.ReportVo;
import com.tts.ms.resource.request.ReportItemRequest;
import com.tts.ms.resource.request.ReportRequest;
import com.tts.ms.resource.request.ReportTaskRequest;
import com.xcrm.cloud.database.db.BaseDaoSupport;
import com.xcrm.cloud.database.db.query.BaseQuery;
import com.xcrm.cloud.database.db.query.QueryBuilder;
import com.xcrm.cloud.database.db.query.Ssqb;
import com.xcrm.cloud.database.db.query.expression.Restrictions;
import com.xcrm.common.util.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author gaoyan
 */
@Service
@Transactional
public class ReportServiceImpl implements IReportService {

    protected Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private BaseDaoSupport dao;


    @Override
    public List<Report> queryReports(String openId) {
        QueryBuilder queryReportQb = QueryBuilder.where(Restrictions.eq("openId",openId))
                .and(Restrictions.eq("dataStatus",1))
                .setOrderBys("created", BaseQuery.OrderByType.desc);
        return dao.queryList(queryReportQb,Report.class);
    }

    @Override
    public ReportTask queryReportTask(Long reportId) {
        QueryBuilder queryReportQb = QueryBuilder.where(Restrictions.eq("reportId",reportId))
                .and(Restrictions.eq("dataStatus",1));
        return dao.query(queryReportQb,ReportTask.class);
    }

    @Override
    public List<ReportVo> queryReportList(Long reportId, String reportType) {
        Ssqb queryReportQb = Ssqb.create("com.jlt.tts.report.queryReportList")
                .setParam("reportId",reportId)
                .setParam("reportType",reportType);
        return dao.findForList(queryReportQb,ReportVo.class);
    }

    @Override
    public String queryReportSummary(Long reportId) {
        QueryBuilder queryReportQb = QueryBuilder.where(Restrictions.eq("id",reportId))
                .and(Restrictions.eq("dataStatus",1));
        Report report = dao.query(queryReportQb,Report.class);
        return report.getSummary();
    }

    @Override
    public Report saveReport(ReportRequest request) {
        Date now = DateFormatUtils.getNow();
        Report report = new Report();
        report.setCreated(now);
        report.setOpenId(request.getOpenId());
        report.setReportName(request.getReportName());
        dao.save(report);
        return report;
    }

    @Override
    public ReportTask saveReportTask(Long reportId, ReportTaskRequest request) {
        ReportTask reportTask = new ReportTask();
        reportTask.setTaskDesc(request.getTaskDesc());
        reportTask.setOrderCount(request.getOrderCount());
        reportTask.setSampleCount(request.getSampleCount());
        reportTask.setReportId(reportId);
        reportTask.setSampleProportion(request.getSampleProportion());
        reportTask.setCreated(DateFormatUtils.getNow());
        dao.save(reportTask);
        return reportTask;
    }

    @Override
    public void updateReportTask(Long reportId, ReportTaskRequest request) {
        Report report = new Report();
        report.setReportName(request.getReportName());
        report.setId(reportId);
        dao.update(report);
        ReportTask reportTask = new ReportTask();
        reportTask.setSampleCount(request.getSampleCount());
        reportTask.setOrderCount(request.getOrderCount());
        reportTask.setTaskDesc(request.getTaskDesc());
        reportTask.setSampleProportion(request.getSampleProportion());
        reportTask.setUpdated(DateFormatUtils.getNow());
        QueryBuilder updateQb = QueryBuilder.where(Restrictions.eq("dataStatus",1))
                .and(Restrictions.eq("reportId",reportId));
        dao.updateByQuery(reportTask,updateQb);
    }

    @Override
    public ReportDetail queryReportDetail(Long reportDetailId) {
        QueryBuilder queryQb = QueryBuilder.where(Restrictions.eq("id",reportDetailId))
                .and(Restrictions.eq("dataStatus",1));
        return dao.query(queryQb,ReportDetail.class);
    }

    @Override
    public void updateReportItem(Long reportDetailId, ReportItemRequest request) {
        QueryBuilder updateQb = QueryBuilder.where(Restrictions.eq("reportId",request.getReportId()))
                .and(Restrictions.eq("reportType",request.getReportType()))
                .and(Restrictions.eq("id",reportDetailId))
                .and(Restrictions.eq("dataStatus",1));
        ReportDetail reportDetail = new ReportDetail();
        reportDetail.setReportDesc(request.getReportDesc());
        reportDetail.setReportImages(request.getReportImages());
        reportDetail.setUpdated(DateFormatUtils.getNow());
        dao.updateByQuery(reportDetail,updateQb);
    }

    @Override
    public ReportDetail saveReportItem(ReportItemRequest request) {
        ReportDetail reportDetail = new ReportDetail();
        reportDetail.setReportId(request.getReportId());
        reportDetail.setReportType(request.getReportType());
        reportDetail.setReportDesc(request.getReportDesc());
        reportDetail.setReportImages(request.getReportImages());
        reportDetail.setCreated(DateFormatUtils.getNow());
        dao.save(reportDetail);
        return  reportDetail;
    }

    @Override
    public void deleteReportDetail(Long reportDetailId) {
        QueryBuilder deleteQb = QueryBuilder.where(Restrictions.eq("id",reportDetailId))
                .and(Restrictions.eq("dataStatus",1));
        dao.deleteByQuery(deleteQb,ReportDetail.class);
    }

    @Override
    public void updateReportSummary(Long reportId, String summary) {
        QueryBuilder updateQb = QueryBuilder.where(Restrictions.eq("id",reportId))
                .and(Restrictions.eq("dataStatus",1));
        Report report = new Report();
        report.setSummary(summary);
        dao.updateByQuery(report,updateQb);
    }

    @Override
    public void updateReport(Long reportId, String reportName) {
        Report report = new Report();
        report.setId(reportId);
        report.setReportName(reportName);
        dao.update(report);
    }
}
