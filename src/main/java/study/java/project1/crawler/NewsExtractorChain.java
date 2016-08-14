/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.crawler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import study.java.project1.html.TagElement;
import study.java.project1.model.News;

/**
 * 게시판형 뉴스페이지 크롤러로, 1차로 목록으로부터 뉴스기사 링크를 가져오며(contentsListExtractor), 
 * 이 링크를 다시 한 번 따라가서 실제 뉴스 제목, 내용을 긁어온다.(contentsExtractor) 
 * @author hyeon
 */
public class NewsExtractorChain implements NewsCrawler<List<News>> {
  @Autowired
  private NewsCrawler<List<TagElement>> contentsListExtractor;
  
  @Autowired
  private NewsCrawler<News> contentsExtractor;
  
  @Override
  public List<News> parse(CrawlerContext ctx) {
    return null;
  }

}
