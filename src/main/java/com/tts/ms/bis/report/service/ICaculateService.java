package com.tts.ms.bis.report.service;

import com.tts.ms.bis.report.vo.AqlCaculateResultVo;

import java.util.List;

/**
 * aql计算接口
 * @Author gaoyan
 * @Date: 2017/12/24
 */
public interface ICaculateService {

    List<AqlCaculateResultVo> caculate(String caculateType, Integer shippingSize, String majorValue, String minorValue);
}
