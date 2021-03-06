package com.sw.domain.facade.file;


import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileFacade {
	private static Logger logger = LoggerFactory.getLogger(FileFacade.class);
	public static final String path0 = "/etc/nginx/html/file/upload/";
	public static final String path1 = "/etc/nginx/html/file/";
	public static final String home = "/home/";

	public String uploadFile(FormDataMultiPart multiPart, int type) throws Exception {
		FormDataBodyPart file = multiPart.getField("file");
		InputStream ins = file.getValueAs(InputStream.class);
		String fileName = file.getContentDisposition().getFileName();
		fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		String uploadPath = fileName.contains("sw-") ? home : type == 0 ? path0 : path1;
		File f = new File(uploadPath + fileName);
		logger.info("upload " + fileName + " to " + uploadPath);
		OutputStream os = new FileOutputStream(f);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		ins.close();
		String url = "https://bitcoinrobot.cn/file/upload/" + fileName;
		return "{url:'" + url + "', error:'0',message:''}";
	}
}
