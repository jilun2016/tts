package com.tts.ms.bis.report.service;

import com.alibaba.fastjson.JSON;
import com.tts.ms.bis.common.AliyunOssService;
import com.tts.ms.bis.report.entity.ReportCheck;
import com.tts.ms.email.SendMailUtil;
import com.tts.ms.resource.request.CheckinRequest;
import com.xcrm.cloud.database.db.BaseDaoSupport;
import com.xcrm.common.util.DateFormatUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 打卡接口
 * @Author gaoyan
 * @Date: 2018/2/10
 */
@Service
@Transactional
public class CheckServiceImpl implements ICheckService {

    protected Logger log = LoggerFactory.getLogger(CheckServiceImpl.class);

    @Autowired
    private BaseDaoSupport dao;

    @Autowired
    private AliyunOssService aliyunOssService;

    @Override
    public void checkIn(CheckinRequest request) {
        ReportCheck reportCheck = new ReportCheck();
        reportCheck.setCheckAddress(request.getAddress());
        reportCheck.setCheckEmail(request.getCheckEmail());
        reportCheck.setCheckImage(request.getCheckImage());
        reportCheck.setCreated(DateFormatUtils.getNow());
        reportCheck.setOpenId(request.getOpenId());
        dao.save(reportCheck);
        //发送邮件
        HashMap<String,Object> checkResultMap = new HashMap<>();
        checkResultMap.put("打卡地址",reportCheck.getCheckAddress());
        checkResultMap.put("打卡时间",reportCheck.getCreated());
        String text = JSON.toJSONString(checkResultMap);
        SendMailUtil sendEmail = new SendMailUtil(
                "15604090129@163.com", "jlt2016YUIOYHN", request.getCheckEmail(),
                "打卡记录", text, "打卡记录", "", "");
        try {
            List<File> fileList = new ArrayList<>();
            if(StringUtils.isNotEmpty(request.getCheckImage())){
                for (String checkImage : request.getCheckImage().split(",")) {
                    File ossFile = aliyunOssService.downloadOssFile(checkImage);
                    if(Objects.nonNull(checkImage)){
                        fileList.add(ossFile);
                    }
                }
            }
            sendEmail.send(text,fileList);
        } catch (Exception ex) {
            log.error("SendMailUtil.send() error ",ex);
        }
    }
}
