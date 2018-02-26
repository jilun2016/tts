package com.tts.ms.rest.integration;

import com.tts.ms.bis.wx.service.IWxService;
import com.tts.ms.config.SysConfig;
import com.tts.ms.rest.common.CommonConstants;
import com.tts.ms.rest.common.ErrorMessage;
import com.tts.ms.rest.common.RestErrorCode;
import com.tts.ms.util.CookieUtils;
import com.tts.ms.util.ErrorCodeMessageUtil;
import com.xcrm.log.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Cookie;
import java.io.IOException;
import java.util.Objects;


/**
 * 微信授权验证
 * @Author gaoyan
 * @Date: 2018/2/10
 */
public class AuthoricationFilter implements ContainerRequestFilter,ContainerResponseFilter {
	
	private static Logger logger = Logger.getLogger(AuthoricationFilter.class);

	private String[] excludeUrls = {"/auth/callback"};

	@Autowired
	private IWxService wxService;

	@Autowired
	private SysConfig sysConfig;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String agent = requestContext.getHeaderString("user-agent");

		//授权回调排除
		if (exclude(requestContext.getUriInfo().getPath())){
			return;
		}
		//判断是否微信登录,非微信登陆的话 跳转提示
		if (!agent.toLowerCase().contains("micromessenger")) {
			ErrorMessage errorMessage = ErrorCodeMessageUtil.buildErrorMessage(RestErrorCode.UNSUPPORTED_WX_BROWSER);
			requestContext.abortWith(errorMessage.buildUnauthorizedResponse());
			return;
		}

		Cookie cookieFromOpenId = CookieUtils.getCookie(requestContext.getCookies(), CommonConstants.WX_OPEN_ID_COOKIE);
		//如果cookie为空,那么返回未授权,需要重新登录
		if(Objects.isNull(cookieFromOpenId)){
			String wxAuthUrl = wxService.buildWxAuthRedirect(sysConfig.getWxRedirectUrl());
			logger.info("AuthoricationFilter login redirectUrl :{},wxAuthUrl :{}",wxAuthUrl);
			ErrorMessage errorMessage = ErrorCodeMessageUtil.buildErrorMessage(RestErrorCode.WX_NOT_AUTH.code(),wxAuthUrl);
			requestContext.abortWith(errorMessage.buildUnauthorizedResponse());
			return ;
		}else{
			requestContext.setProperty(CommonConstants.WX_OPEN_ID_COOKIE, cookieFromOpenId.getValue());
		}
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {

	}

	private boolean exclude(String uri) {
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (uri.contains(exc)) {
					return true;
				}
			}
		}
		return false;
	}

}
