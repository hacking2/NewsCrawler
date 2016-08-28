/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author hyeon
 */
@Table(name = "crawl_recipe")
public class CrawlRecipe {
  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;
  
  @Id
  @Column(name = "recipe_id")
  private Integer recipeId;
  
  @Column(name = "news_link_selector")
  private String newsLinkSelector;
  
  @Column(name = "title_selector")
  private String titleSelector;
  
  @Column(name = "content_selector")
  private String contentSelector;
  
  @Column(name = "id_selector")
  private String idSelector;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "id_spot")
  private IdSpot idSpot;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "id_value_type")
  private ValueType idValueType;
  
  @Column(name = "seed_url")
  private String seedUrl;
  
  public String getSeedUrl() {
    return seedUrl;
  }

  public void setSeedUrl(String seedUrl) {
    this.seedUrl = seedUrl;
  }

  public ValueType getIdValueType() {
    return idValueType;
  }

  public void setIdValueType(ValueType idValueType) {
    this.idValueType = idValueType;
  }

  public String getIdSelector() {
    return idSelector;
  }

  public void setIdSelector(String idSelector) {
    this.idSelector = idSelector;
  }

  public String getNewsLinkSelector() {
    return newsLinkSelector;
  }

  public void setNewsLinkSelector(String newsLinkSelector) {
    this.newsLinkSelector = newsLinkSelector;
  }

  public String getTitleSelector() {
    return titleSelector;
  }

  public void setTitleSelector(String titleSelector) {
    this.titleSelector = titleSelector;
  }

  public String getContentSelector() {
    return contentSelector;
  }

  public void setContentSelector(String contentSelector) {
    this.contentSelector = contentSelector;
  }

  public IdSpot getIdSpot() {
    return idSpot;
  }

  public void setIdSpot(IdSpot idSpot) {
    this.idSpot = idSpot;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public Integer getRecipeId() {
    return recipeId;
  }

  public void setRecipeId(Integer recipeId) {
    this.recipeId = recipeId;
  }

  public static enum IdSpot {
    URL, DOCUMENT
  }
  
  public static enum ValueType {
    REGEX, VALUE, HTML, TEXT;
  }
}
