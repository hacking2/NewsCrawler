/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.crawler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import study.java.project1.byproduct.RawNews;
import study.java.project1.html.TagElement;

/**
 * 게시판형 뉴스페이지 크롤러로, 1차로 목록으로부터 뉴스기사 링크를 가져오며(contentsListExtractor), 
 * 이 링크를 다시 한 번 따라가서 실제 뉴스 제목, 내용을 긁어온다.(contentsExtractor) 
 * @author hyeon
 */
@Component
public class NewsExtractorChain implements NewsCrawler<List<RawNews>> {
  @Autowired
  private NewsLinkCrawler contentsListExtractor;
  
  @Autowired
  private NewsContentCrawler contentsExtractor;
  
  @Override
  public List<RawNews> parse(CrawlerContext ctx) throws Exception {
    List<RawNews> news = new ArrayList<>();
    List<TagElement> links = contentsListExtractor.parse(ctx);
    links.forEach(linkElement -> {
      ctx.putParam(CrawlerContextProperty.SEED_URL, linkElement.getAttribute("abs:href"));
      try {
        news.add(contentsExtractor.parse(ctx));
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    return news;
  }
}
