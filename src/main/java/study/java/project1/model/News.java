/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.model;

/**
 * News db의 도메인 객체
 * @author hyeon
 *
 */
public class News {
	private String company;
	private String id;
	private String title;
	private String content;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
