package com.tts.ms.bis.report;

/**
 * 报告类型
 * @Author gaoyan
 * @Date: 2018/1/14
 */
public enum ReportTypeEnum {

	/**
	 * 场况
	 */
	REPORT_CK("report_ck"),
	/**
	 * 大货情况
	 */
	REPORT_DHQK("report_dhqk"),
	/**
	 * 抽样
	 */
	REPORT_CY("report_cy"),
	/**
	 * 包装
	 */
	REPORT_BZ("report_bz"),
	/**
	 * 款式\型号
	 */
	REPORT_KSXH("report_ksxh"),
	/**
	 * 辅料
	 */
	REPORT_FL("report_fl"),
	/**
	 * 做工
	 */
	REPORT_ZG("report_zg"),
	/**
	 * 尺寸
	 */
	REPORT_CC("report_cc"),
	/**
	 * 测试
	 */
	REPORT_CS("report_cs");

	private final String value;

	private ReportTypeEnum(final String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
