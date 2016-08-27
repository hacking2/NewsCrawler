/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.model;

/**
 * @author hyeon
 */
public class CrawlRecipe {
  private Company company;
  private Integer recipeId;
  private String newsLinkSelector;
  private String titleSelector;
  private String contentSelector;
  private String idSelector;
  private IdSpot idSpot;
  private ValueType idValueType;
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
