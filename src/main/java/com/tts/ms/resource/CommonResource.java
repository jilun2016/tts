package com.tts.ms.resource;

import com.aliyuncs.exceptions.ClientException;
import com.tts.ms.bis.common.AliyunOssService;
import com.xcrm.common.util.InputStreamUtils;
import com.xcrm.log.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

/**
 * 通用服务resource
 * @Author gaoyan
 * @Date: 2017/12/24
 */
@Path("/common")
public class CommonResource {
	
	private static Logger logger = Logger.getLogger(CommonResource.class);

	@Autowired
	private AliyunOssService aliyunOssService;
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(FormDataMultiPart form) throws IOException, ClientException {
		//获取文件流
		FormDataBodyPart filePart = form.getField("file");
		//ContentDisposition headerOfFilePart = filePart.getContentDisposition();
		//把表单内容转换成流
		InputStream fileInputStream = filePart.getValueAs(InputStream.class);
		byte[] fileBytes = InputStreamUtils.InputStreamTOByte(fileInputStream);
		FormDataContentDisposition formDataContentDisposition = filePart.getFormDataContentDisposition();
		String fileName = formDataContentDisposition.getFileName();
		String fileType = "";
		if(fileName.lastIndexOf(".") >= 0){
			fileType = fileName.substring(fileName.lastIndexOf(".")+1);
		}
		return aliyunOssService.upload2OssWithOutKey(fileBytes,fileType);
	}

}