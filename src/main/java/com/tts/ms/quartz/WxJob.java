package com.tts.ms.quartz;

import com.tts.ms.config.SysConfig;
import com.tts.ms.quartz.service.IJobService;
import com.tts.ms.util.SystemProfileEnum;
import com.xcrm.common.util.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 定时刷新公众号授权的token
 * @Author gaoyan
 * @Date: 2017/6/12
 */
@Component
public class WxJob {

	private static final Logger logger = LoggerFactory.getLogger(WxJob.class);

	@Autowired
	private SysConfig sysConfig;

	@Autowired
	private IJobService jobService;
	
	@Scheduled(cron="0 * * * * ?")
	public void wxRefreshTokenJob() {
		//每小时扫描时效
//		if(!Objects.equals(sysConfig.getProjectProfile(), SystemProfileEnum.DEVELOP.value())){
			logger.info("#############wxRefreshTokenJob############");
			logger.info("----------------wxRefreshTokenJob JOB begin "+ DateFormatUtils.formatDate(new Date(),null)+"-------------------");
			try {
				jobService.wxRefreshTokenJob();
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("----------------wxRefreshTokenJob JOB end "+ DateFormatUtils.formatDate(new Date(),null)+"-------------------");
//		}
	}
}