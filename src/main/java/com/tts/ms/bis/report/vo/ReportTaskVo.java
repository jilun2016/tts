package com.tts.ms.bis.report.vo;

/**
 * 报告任务vo
 * @Author gaoyan
 * @Date: 2017/12/24
 */
public class ReportTaskVo {

    /**
     * 任务描述
     */
    private String taskDesc;

    /**
     * 订单数量
     */
    private Integer orderCount;

    /**
     * 抽样比例
     */
    private Integer sampleProportion;

    /**
     * 验货抽样数
     */
    private Integer sampleCount;

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getSampleProportion() {
        return sampleProportion;
    }

    public void setSampleProportion(Integer sampleProportion) {
        this.sampleProportion = sampleProportion;
    }

    public Integer getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }
}
