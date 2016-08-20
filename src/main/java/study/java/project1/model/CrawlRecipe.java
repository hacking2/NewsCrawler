/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import study.java.project1.html.AnchorTag;
import study.java.project1.html.TagElement;

/**
 * 
 * @author hyeon
 *
 */
public class CrawlRecipe {
  private Company company;
  private Integer recipeId;
  private String newsLinkSelector;
  private String titleSelector;
  private String contentSelector;
  private String idSelector;
  private IdSpot idSpot;
  private boolean removeImg;
  
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

  public boolean isRemoveImg() {
    return removeImg;
  }

  public void setRemoveImg(boolean removeImg) {
    this.removeImg = removeImg;
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
}
