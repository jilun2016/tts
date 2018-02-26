package com.tts.ms.resource.request;

import javax.validation.constraints.NotNull;

/**
 * 打卡资源
 * @Author gaoyan
 * @Date: 2018/2/10
 */
public class CheckinRequest {

    /**
     * 打卡发送邮箱
     */
    @NotNull(message = "打卡发送邮箱不允许为空")
    private String checkEmail;

    /**
     * 打卡地址
     */
    @NotNull(message = "打卡地址不允许为空")
    private String address;

    /**
     * 打卡图片
     */
    private String checkImage;

    /**
     * 打卡备注
     */
    private String checkRemark;


    public String getCheckEmail() {
        return checkEmail;
    }

    public void setCheckEmail(String checkEmail) {
        this.checkEmail = checkEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckImage() {
        return checkImage;
    }

    public void setCheckImage(String checkImage) {
        this.checkImage = checkImage;
    }

    public String getCheckRemark() {
        return checkRemark;
    }

    public void setCheckRemark(String checkRemark) {
        this.checkRemark = checkRemark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CheckinRequest{");
        sb.append(", checkEmail='").append(checkEmail).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", checkImage='").append(checkImage).append('\'');
        sb.append(", checkRemark='").append(checkRemark).append('\'');
        sb.append('}');
        return sb.toString();
    }
}