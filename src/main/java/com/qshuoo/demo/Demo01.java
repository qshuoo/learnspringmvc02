package com.qshuoo.demo;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.qshuoo.pojo.UploadMsg;
import com.sun.net.httpserver.Headers;

@Controller
@SessionAttributes(names = {"list"})
public class Demo01 {

	@RequestMapping("uploaddemo")
	public String upload(@RequestParam("file") MultipartFile file) {

		// 文件存入服务器
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		String url = "E:\\springupload01\\" + fileName;
		File file2 = new File(url);
		try {
			file.transferTo(file2);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 存入数据库

		Configuration conn = new Configuration().configure();

		SessionFactory sf = conn.buildSessionFactory();

		Session session = sf.openSession();

		session.beginTransaction();

		UploadMsg um = new UploadMsg();

		um.setName(fileName);

		um.setUrl(url);
		
		System.out.println(um);

		session.save(um);

		session.getTransaction().commit();

		session.close();

		return "forward:list.do";
	}

	@RequestMapping("list")
	public String showList(Model model) {

		Configuration conn = new Configuration().configure();

		SessionFactory sf = conn.buildSessionFactory();

		Session session = sf.openSession();

		session.beginTransaction();

		List<UploadMsg> list = new ArrayList<UploadMsg>();

		list = session.createQuery("from UploadMsg").list();

		model.addAttribute("list", list);

		session.getTransaction().commit();

		session.close();
		return "redirect:show.jsp";
	}
	
	@RequestMapping("downloaddemo")
	public ResponseEntity<byte[]> download(String url) {
		File file = new File("E:\\springupload01\\"+url);
		try {
			byte[] bs = FileUtils.readFileToByteArray(file);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", 
					URLEncoder.encode(file.getName(), "utf-8"));
			
			return new ResponseEntity<byte[]>(bs, headers, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
