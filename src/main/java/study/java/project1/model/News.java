/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * News db의 도메인 객체
 * @author hyeon
 *
 */
@Entity(name = "news")
public class News {
  @Id
  @GeneratedValue
  @Column
	private int id;
  
  @ManyToOne
  @JoinColumn(name = "company_id")
	private Company company;
  
  @Column
	private String title;
  
  @Column
	private String content;
  
  @OneToMany
  @JoinColumn(name = "news_image")
  private List<NewsImage> newsImages;
  
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
