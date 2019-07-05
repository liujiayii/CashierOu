package com.cashier.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.cashier.util.UpdateFile;

/**
 *
 * @ClassName: UploadController
 * 
 * @description 图片上传的Controller层
 *
 * @author
 * @createDate 2018年11月27日
 */
@Controller
public class UploadController {
	// 上传图片
	@RequestMapping(value = "/upload"/* ,method=RequestMethod.POST */)
	@ResponseBody
	public Map<String, Object> image(MultipartFile file, HttpServletRequest request) {
		// System.out.println(file.getSize());
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// String name = file.getOriginalFilename();//上传文件的真实名称
			// String suffixName =
			// name.substring(name.lastIndexOf(".")+1);//获取后缀名
			// 图片的存储位置
			// String path =
			// request.getServletContext().getRealPath("/public/image//"/*+suffixName+"\\"*/);
			// String path
			// ="D:/workspace/SilverCollectingSystem/src/main/webapp/public/image";
			// String path =
			// request.getSession().getServletContext().getRealPath("/public/imge");
			// String path ="D:/img";
			// String image = UploadUtil.uploadFile(file, path);
			Map<String, String> image = UpdateFile.update(file);
			map.put("code", 0);
			map.put("message", "上传成功");
			map.put("data", image.get("Path"));
		} catch (Exception e) {
			map.put("code", 1);
			e.printStackTrace();
		}
		return map;
	}

}
