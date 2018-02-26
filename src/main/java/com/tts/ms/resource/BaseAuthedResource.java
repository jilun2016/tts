package com.tts.ms.resource;


import com.tts.ms.rest.common.CommonConstants;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

/**
 * 授权登录后 资源基类
 * @author Brian
 *
 */
public class BaseAuthedResource {
	
	@Context
    private ContainerRequestContext containerRequestContext;
	
	public String getOpenId() {
		return (String) containerRequestContext.getProperty(CommonConstants.WX_OPEN_ID_COOKIE);
	}

}
