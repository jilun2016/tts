package com.tts.ms.resource;

import com.tts.ms.bis.report.service.ICheckService;
import com.tts.ms.resource.request.CheckinRequest;
import com.xcrm.log.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * 打卡资源处理
 */
@Path("/check")
public class CheckResource {
	
	private static Logger logger = Logger.getLogger(CheckResource.class);

	@Autowired
	private ICheckService checkService;

	/**
	 * 保存打卡记录
	 * @param request
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkIn(@Valid CheckinRequest request) {
		logger.debug("CheckResource.checkIn({})", request);
		checkService.checkIn(request);
		return Response.noContent().build();
	}

}