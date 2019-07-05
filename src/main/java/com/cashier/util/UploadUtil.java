package com.cashier.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @ClassName: UploadUtil

 * @description  图片上传的工具类
 *
 * @author pangchong
 * @createDate 2018年12月4日
 */

public class UploadUtil {

	public static String uploadFile(MultipartFile file,String path) throws IOException {
		String name = file.getOriginalFilename();//上传文件的真实名称
		String suffixName = name.substring(name.lastIndexOf("."));//获取后缀名
//		String hash = Integer.toHexString(new Random().nextInt());//自定义随机数（字母加数字）
	//	String hash = UUID.randomUUID().toString();
		String fileName = name /*+ suffixName*/;
	path ="D:/img/";
		File tempFile = new File(path, fileName);
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		if (tempFile.exists()) {
			tempFile.delete();
		}
		tempFile.createNewFile();
		file.transferTo(tempFile);
		return path+fileName;
	//	return tempFile.getName();
	}
	

}
