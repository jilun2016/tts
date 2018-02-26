package com.tts.ms.bis.report.service;

import com.tts.ms.resource.request.CheckinRequest;

/**
 * 打卡接口
 * @Author gaoyan
 * @Date: 2017/12/24
 */
public interface ICheckService {

    /**
     * 打卡接口
     * @param openId
     * @param request
     */
    void checkIn(String openId, CheckinRequest request);
}
