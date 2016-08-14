/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.crawler;

import java.util.Map;

/**
 * 특정페이지에서 특정 태그를 파싱하는 크롤러 추상화
 * @author hyeon
 *
 */
public interface NewsCrawler<T> {
  T parse(CrawlerContext context);
  
  /**
   * Crawler에 필요한 파라미터 전달용 Parameter
   * @author hyeon
   *
   */
  public static class CrawlerContext {
    private Map<String, Object> params;
    
    @SuppressWarnings("unchecked")
    public <T> T getParam(String key) {
      return (T) params.get(key);
    }
    
    public CrawlerContext putParam(String key, Object value) {
      params.put(key, value);
      return this;
    }
  }
  
  public static interface CrawlerContextProperty {
    public static final String SEED_URL = "seedUrl";
    public static final String SELECTOR = "selector";
  }
}
