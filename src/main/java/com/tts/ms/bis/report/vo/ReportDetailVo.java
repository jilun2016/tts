package com.tts.ms.bis.report.vo;

/**
 * 报告详情vo
 * @Author gaoyan
 * @Date: 2017/12/24
 */
public class ReportDetailVo {

    /**
     * 报告图片,半角英文逗号分隔
     */
    private String reportImages;

    /**
     * 报告说明
     */
    private String reportDesc;

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
