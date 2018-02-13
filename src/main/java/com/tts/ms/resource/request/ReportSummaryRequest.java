package com.tts.ms.resource.request;

/**
 * 报告综述请求
 * @Author gaoyan
 * @Date: 2018/1/13
 */
public class ReportSummaryRequest {

    /**
     * 报告的综述
     */
    private String summary;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportSummaryRequest{");
        sb.append("summary='").append(summary).append('\'');
        sb.append('}');
        return sb.toString();
    }
}