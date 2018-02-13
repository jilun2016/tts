package com.tts.ms.resource;

import com.tts.ms.bis.wx.JsAPISignature;
import com.tts.ms.bis.wx.service.IWxService;
import com.tts.ms.config.SysConfig;
import com.tts.ms.exception.BizCoreRuntimeException;
import com.tts.ms.rest.common.BizErrorConstants;
import com.tts.ms.rest.common.CommonConstants;
import com.tts.ms.util.CookieUtils;
import com.tts.ms.util.HTTPUtil;
import com.xcrm.log.Logger;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信处理
 * @author gaoyan
 * @Date: 2018/2/11
 */
@Path("/wx")
public class WxResource {
	
	private static Logger logger = Logger.getLogger(WxResource.class);

	@Autowired
	private IWxService wxService;

	@Autowired
	private SysConfig sysConfig;


	/**
	 * 微信授权回调
	 * @param code
	 * @param state
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GET
	@Path("/auth/callback")
	@Produces(MediaType.APPLICATION_JSON)
	public void wxRedirect(@QueryParam("code") String code, @QueryParam("state") String state, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {
		logger.info("WxResource.wxRedirect({},{})",code,state);
		if(StringUtils.isNotBlank(code) && StringUtils.isNotBlank(state)) {
			String redirectUrl = String.valueOf(state);
			//通过回调获取的code,获取授权的accessToken和openId
			Map<String,Object> outhTokenParaMap = new HashMap<>();
			outhTokenParaMap.put("appid",sysConfig.getWxAppId());
			outhTokenParaMap.put("code",code);
			outhTokenParaMap.put("grant_type","authorization_code");
			outhTokenParaMap.put("secret",sysConfig.getWxAppSecret());
			Map<String,Object> resultMap = HTTPUtil.sendGet(sysConfig.getWxAuthTokenUrl(),outhTokenParaMap);

			if(MapUtils.isNotEmpty(resultMap)){
				logger.info("WxResource.wxRedirect resultMap:" + resultMap);
				if(StringUtils.isNotEmpty(MapUtils.getString(resultMap,"errmsg"))){
					String errmsg = MapUtils.getString(resultMap,"errmsg","获取授权信息失败");
					logger.error("VoteController.wxRedirect get token error :" + errmsg);
					throw new BizCoreRuntimeException(BizErrorConstants.WX_AUTH_TOKEN_ERROR);
				}else{
					String accessToken = MapUtils.getString(resultMap,"access_token");
					String openId = MapUtils.getString(resultMap,"openid");

					if(StringUtils.isNotBlank(openId)) {
						logger.info("get openId from wx and save it to cookie, openId is ={}" ,openId);
						CookieUtils.addCookie(request, response, CommonConstants.WX_OPEN_ID_COOKIE
								, openId, null, sysConfig.getTtsCookieHost());
					}

					//获取用户信息
					Map<String,Object> userInfoParaMap = new HashMap<>();
					userInfoParaMap.put("access_token",accessToken);
					userInfoParaMap.put("openid",openId);
					userInfoParaMap.put("lang","zh_CN");
					Map<String, Object> wxUserMap = HTTPUtil.sendGet(sysConfig.getWxUserInfoUrl(),userInfoParaMap);

					if(MapUtils.isNotEmpty(wxUserMap)){
						if(wxUserMap.containsKey("errmsg")){
							String errmsg = MapUtils.getString(wxUserMap,"errmsg","获取用户信息失败");
							logger.error("WxResource.queryWxUser occurs error.redirectUrl:{},openId:{},userInfoParaMap:{},errmsg:{}",
									redirectUrl,openId,userInfoParaMap,errmsg);
							throw new BizCoreRuntimeException(BizErrorConstants.WX_AUTH_USER_INFO_ERROR);
						}
						logger.info("WxResource.queryWxUser user:" + wxUserMap);
						//如果没有授权,那么返回未授权code
						Boolean subscribe = MapUtils.getBoolean(wxUserMap,"subscribe");
						if(BooleanUtils.isFalse(subscribe)){
							throw new BizCoreRuntimeException(BizErrorConstants.WX_AUTH_USER_NO_SUBSCRIBE);
						}
						logger.info("WxResource reirect url:" + redirectUrl);
						response.sendRedirect(response.encodeRedirectURL(redirectUrl));

					}else{
						logger.error("WxResource.queryWxUser occurs error.redirectUrl:{},openId:{},userInfoParaMap:{}",
								redirectUrl,openId,userInfoParaMap);
						throw new BizCoreRuntimeException(BizErrorConstants.WX_AUTH_USER_INFO_ERROR);
					}
				}

			}
		}
	}

	/**
	 * 获取微信jssdk配置信息
	 * @param currentUrl
	 * @return
	 */
	@GET
	@Path("/jssdk_config")
	@Produces(MediaType.APPLICATION_JSON)
	public JsAPISignature queryJsSdkConfig(@QueryParam("currentUrl") String currentUrl) {
		logger.info("WxResource.queryJsSdkConfig({})",currentUrl);
		try {
			return wxService.getShareConfigInfo(currentUrl);
		} catch (Exception e) {
			logger.error("queryJsSdkConfig occurs exception ",e);
			throw new BizCoreRuntimeException(BizErrorConstants.WX_JSDK_CONFIG_ERROR);
		}
	}
}