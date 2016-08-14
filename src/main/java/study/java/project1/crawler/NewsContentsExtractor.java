/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.crawler;

import study.java.project1.model.News;

/**
 * Seed에서 추출된 뉴스 기사 링크를 따라가 뉴스 제목, 내용 등을 파싱한다.
 * @author hyeon
 *
 */
public class NewsContentsExtractor implements NewsCrawler<News> {

  @Override
  public News parse(CrawlerContext ctx) {
    // TODO Auto-generated method stub
    return null;
  }

  public static interface NewsContentsProperty {
    public static final String TITLE_SELECTOR = "titleSelector";
    public static final String CONTENT_SELECTOR = "contentSelector";
  }
}
