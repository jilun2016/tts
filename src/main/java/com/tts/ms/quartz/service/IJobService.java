package com.tts.ms.quartz.service;

public interface IJobService {
	/**
	 * 定时更新微信token
	 */
	void wxRefreshTokenJob() throws Exception;
}
