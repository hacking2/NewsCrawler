/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import study.java.project1.html.AnchorTag;
import study.java.project1.html.TagElement;

/**
 * seed 페이지에서 뉴스목록링크를 가져옴
 * @author hyeon
 *
 */
@Component
public class ContentsListExtractor implements NewsCrawler<List<TagElement>> {
  public static final int DEFAULT_TIMEOUT_MILLIS = 3000;
  
  @Override
  public List<TagElement> parse(CrawlerContext ctx) throws MalformedURLException, IOException {
    String seedUrl = ctx.getParam(CrawlerContextProperty.SEED_URL);
    Document doc = Jsoup.parse(new URL(seedUrl), DEFAULT_TIMEOUT_MILLIS);
    String listSelector = ctx.getParam(ContentsListProperty.CONTENT_LIST_SELECTOR);
    List<TagElement> elements = new ArrayList<>();
    for (Element e : doc.select(listSelector)) {
      elements.add(new AnchorTag(e));
    }
    return elements;
  }
  
  public static interface ContentsListProperty {
    public static final String CONTENT_LIST_SELECTOR = "contentListSelector";
  }
}
