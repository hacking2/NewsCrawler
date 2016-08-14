/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.crawler;

import java.util.List;

import study.java.project1.html.TagElement;

/**
 * seed 페이지에서 뉴스목록링크를 가져옴
 * @author hyeon
 *
 */
public class ContentsListExtractor implements NewsCrawler<List<TagElement>> {

  @Override
  public List<TagElement> parse(CrawlerContext ctx) {
    // TODO Auto-generated method stub
    return null;
  }
  
  public static interface ContentsListProperty {
    public static final String CONTENT_LIST_SELECTOR = "contentListSelector";
  }
}
