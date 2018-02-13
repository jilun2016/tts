package com.tts.ms.resource.request;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 报告任务请求
 * @Author gaoyan
 * @Date: 2018/1/13
 */
public class ReportTaskRequest {

    /**
     * 报告名称
     */
    @NotEmpty(message = "报告名称不允许为空")
    private String reportName;

    /**
     * 任务描述
     */
    @NotEmpty(message = "任务描述不允许为空")
    private String taskDesc;

    /**
     * 订单数量
     */
    @NotNull(message = "订单数量不允许为空")
    @Range(min=1,message = "订单数量无效")
    private Integer orderCount;

    /**
     * 抽样比例
     */
    @NotNull(message = "抽样比例不允许为空")
    @Range(min=0,message = "抽样比例无效")
    private BigDecimal sampleProportion;

    /**
     * 验货抽样数
     */
    @NotNull(message = "验货抽样数不允许为空")
    @Range(min=1,message = "验货抽样数无效")
    private Integer sampleCount;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

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

    public BigDecimal getSampleProportion() {
        return sampleProportion;
    }

    public void setSampleProportion(BigDecimal sampleProportion) {
        this.sampleProportion = sampleProportion;
    }

    public Integer getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReportTaskRequest{");
        sb.append(", reportName='").append(reportName).append('\'');
        sb.append(", taskDesc='").append(taskDesc).append('\'');
        sb.append(", orderCount=").append(orderCount);
        sb.append(", sampleProportion=").append(sampleProportion);
        sb.append(", sampleCount=").append(sampleCount);
        sb.append('}');
        return sb.toString();
    }
}