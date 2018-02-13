package com.tts.ms.action;

import com.aliyuncs.exceptions.ClientException;
import com.tts.ms.bis.common.AliyunOssService;
import com.tts.ms.web.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
    private AliyunOssService aliyunOssService;

	//文件上传相关代码
	@RequestMapping(value = "/common/upload")
	public void upload(@RequestParam("file") MultipartFile file,HttpServletResponse response) throws IOException, ClientException {
        logger.info("CommonController.upload()");
		if (file.isEmpty()) {
			WebResult.renderJsonDataFail(response,"文件为空");
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件的后缀名
		String fileType = fileName.substring(fileName.lastIndexOf("."));
        logger.info("CommonController.upload(),fileType:{}",fileType);
        String key = aliyunOssService.upload2OssWithOutKey(file.getBytes(),fileType);
        Map<String,Object> result = new HashMap<>();
        result.put("key",key);
		WebResult.renderJsonDataDefault(response,result);
	}

	@RequestMapping(value = "/common/test",method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {
		WebResult.renderJsonSucMsg(response);
	}



}