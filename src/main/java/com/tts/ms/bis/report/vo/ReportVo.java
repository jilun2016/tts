package com.tts.ms.bis.report.vo;

/**
 * 报告列表vo
 * @Author gaoyan
 * @Date: 2017/12/24
 */
public class ReportVo {

    /**
     * 报告详情id
     */
    private Long reportDetailId;

    /**
     * 报告类型
     */
    private String reportType;

    /**
     * 报告图片,半角英文逗号分隔
     */
    private String reportImages;

    /**
     * 报告说明
     */
    private String reportDesc;

    public Long getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(Long reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportImages() {
        return reportImages;
    }

    public void setReportImages(String reportImages) {
        this.reportImages = reportImages;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }
}
