package com.tts.ms.resource.request;

import javax.validation.constraints.NotNull;

/**
 * 更新报告请求
 * @Author gaoyan
 * @Date: 2018/1/13
 */
public class UpdateReportRequest {

    /**
     * 报告名称
     */
    @NotNull(message = "报告名称不允许为空")
    private String reportName;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateReportRequest{");
        sb.append("reportName='").append(reportName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}