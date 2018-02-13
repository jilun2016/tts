package com.tts.ms.bis.report.entity;import com.xcrm.cloud.database.db.annotation.PrimaryKeyField;import com.xcrm.cloud.database.db.annotation.Table;import java.util.Date;/******************************************************************************* * javaBeans * t_t_report_detail --> ReportDetail * <table explanation> * @author Generate Code Tool * @created 2018-01-08 10:38:33 *  */	@Table(tableName = "t_t_report_detail")public class ReportDetail {	@PrimaryKeyField	private Long id;	/**	 * 报告id	 */	private Long reportId;	/**	 * 报告类型	 */	private String reportType;	/**	 * 报告图片,半角英文逗号分隔	 */	private String reportImages;	/**	 * 报告说明	 */	private String reportDesc;	/**	 * 创建时间	 */	private Date created;	/**	 * 更新时间	 */	private Date updated;	/**	 * 1:正常 0:删除	 */	private Boolean dataStatus;	public Long getId() {		return id;	}	public void setId(Long id) {		this.id = id;	}	public Long getReportId() {		return reportId;	}	public void setReportId(Long reportId) {		this.reportId = reportId;	}	public String getReportType() {		return reportType;	}	public void setReportType(String reportType) {		this.reportType = reportType;	}	public String getReportImages() {		return reportImages;	}	public void setReportImages(String reportImages) {		this.reportImages = reportImages;	}	public String getReportDesc() {		return reportDesc;	}	public void setReportDesc(String reportDesc) {		this.reportDesc = reportDesc;	}	public Date getCreated() {		return created;	}	public void setCreated(Date created) {		this.created = created;	}	public Date getUpdated() {		return updated;	}	public void setUpdated(Date updated) {		this.updated = updated;	}	public Boolean getDataStatus() {		return dataStatus;	}	public void setDataStatus(Boolean dataStatus) {		this.dataStatus = dataStatus;	}	@Override	public String toString() {		final StringBuilder sb = new StringBuilder("ReportDetail{");		sb.append("id=").append(id);		sb.append(", reportId=").append(reportId);		sb.append(", reportType='").append(reportType).append('\'');		sb.append(", reportImages='").append(reportImages).append('\'');		sb.append(", reportDesc='").append(reportDesc).append('\'');		sb.append(", created=").append(created);		sb.append(", updated=").append(updated);		sb.append(", dataStatus=").append(dataStatus);		sb.append('}');		return sb.toString();	}}