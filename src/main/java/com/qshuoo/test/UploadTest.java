package com.qshuoo.test;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * 测试文件上传
 * @author qshuoo
 *
 */

@Controller
public class UploadTest {
	
	/**
	 * 测试文件上传，需要加 requestParam注解文件入参处
	 * @param desc
	 * @param file
	 * @return
	 */
	@RequestMapping("upload01")
	public String testUpload(String desc, @RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		System.out.println(desc);
		System.out.println(fileName);
		File file01 = new File("E:\\springupload01\\" + fileName);
		try {
			file.transferTo(file01);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}

}
