package com.qshuoo.test;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DownloadTest {

	@RequestMapping("/download01")
	public ResponseEntity<byte[]> download01() {
		//组装返回数据
		File file = new File("E:\\springupload01\\wxsk.png");
		byte[] bytes;
		try {
			bytes = FileUtils.readFileToByteArray(file);
			//组装头部
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", 
					URLEncoder.encode(file.getName(), "utf-8"));
			
			return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
