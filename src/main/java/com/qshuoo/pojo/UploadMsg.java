package com.qshuoo.pojo;

public class UploadMsg {
	
	private Long id;
	private String name;
	private String url;
	
	
	public UploadMsg() {
		super();
	}


	public UploadMsg(Long id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return "UploadMsg [id=" + id + ", name=" + name + ", url=" + url + "]";
	}
	
	

}
