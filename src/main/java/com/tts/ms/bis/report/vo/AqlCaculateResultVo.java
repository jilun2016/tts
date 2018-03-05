package com.tts.ms.bis.report.vo;

/**
 * aql计算处理
 * @Author gaoyan
 * @Date: 2017/12/24
 */
public class AqlCaculateResultVo {

    /**
     * Acceptable Quality Levels
     */
    private String aqlLevel;

    /**
     * Acceptable Quality Levels
     */
    private Integer sampleSize;

    /**
     * acceptValue 接受的值
     */
    private Integer acceptValue;

    /**
     * rejectionValue 拒绝的值
     */
    private Integer rejectionValue;

    public String getAqlLevel() {
        return aqlLevel;
    }

    public void setAqlLevel(String aqlLevel) {
        this.aqlLevel = aqlLevel;
    }

    public Integer getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(Integer sampleSize) {
        this.sampleSize = sampleSize;
    }

    public Integer getAcceptValue() {
        return acceptValue;
    }

    public void setAcceptValue(Integer acceptValue) {
        this.acceptValue = acceptValue;
    }

    public Integer getRejectionValue() {
        return rejectionValue;
    }

    public void setRejectionValue(Integer rejectionValue) {
        this.rejectionValue = rejectionValue;
    }
}
