package com.tts.ms.resource.request;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 报告项请求
 * @Author gaoyan
 * @Date: 2018/1/13
 */
public class ReportItemRequest {

    /**
     * 报告id
     */
    @NotNull(message = "报告id不允许为空")
    private Long reportId;

    /**
     * 报告类型
     */
    @NotEmpty(message = "报告类型不允许为空")
    private String reportType;

    /**
     * 报告图片
     */
    private String reportImages;

    /**
     * 报告描述
     */
    private String reportDesc;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportItemRequest{");
        sb.append("reportId=").append(reportId);
        sb.append(", reportType='").append(reportType).append('\'');
        sb.append(", reportImages='").append(reportImages).append('\'');
        sb.append(", reportDesc='").append(reportDesc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}