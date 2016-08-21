/**
 * 
 */
package study.java.project1.crawler;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.java.project1.crawler.NewsCrawler.CrawlerContext;
import study.java.project1.crawler.NewsCrawler.CrawlerContextProperty;
import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.News;
import study.java.project1.model.CrawlRecipe.IdSpot;

/**
 * @author hyeon
 *
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({ Jsoup.class })
@ContextConfiguration("classpath:study/java/project1/crawler/content_crawl_test.xml")
public class ContentCrawlerTest {

  @Autowired
  private NewsCrawler<News> newsContentCrawler;
  
  @Test
  public void ID가_URL에_있을때_기사제목_내용_ID_잘_긁어오는지_테스트() throws Exception {
    Document mockPage = Jsoup.parse(new File(getResourceUrl().toURI()), "utf-8");
    String mockUrl = "http://myapp.mock.url/id12345"; //맨 오른쪽이 기사 ID

    CrawlRecipe mockRecipe = mock(CrawlRecipe.class);
    when(mockRecipe.getIdSpot()).thenReturn(IdSpot.URL);
    when(mockRecipe.getIdSelector()).thenReturn("id[0-9].+$"); //id다음으로 숫자가 나온는 끝부분을 추출
    when(mockRecipe.getTitleSelector()).thenReturn("div.title_area p span");
    when(mockRecipe.getContentSelector()).thenReturn("div div.content span.content_area");

    CrawlerContext mockCtx = mock(CrawlerContext.class);
    when(mockCtx.getParam(CrawlerContextProperty.SEED_URL)).thenReturn(mockUrl);
    when(mockCtx.getParam(CrawlerContextProperty.RECIPE)).thenReturn(mockRecipe);
    
    mockStatic(Jsoup.class);
    when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(mockPage);
    
    News crawledNews = newsContentCrawler.parse(mockCtx);
    verifyStatic();
    assertEquals("id12345", crawledNews.getId());
    assertEquals("This is content", crawledNews.getContent());
    assertEquals("This is Title", crawledNews.getTitle());
  }
  
  @Test
  public void ID가_Document에_있을때_기사제목_내용_ID_잘_긁어오는지_테스트() throws Exception {
    Document mockPage = Jsoup.parse(new File(getResourceUrl().toURI()), "utf-8");
    String mockUrl = "http://myapp.mock.url/mycontent"; //맨 오른쪽이 기사 ID

    CrawlRecipe mockRecipe = mock(CrawlRecipe.class);
    when(mockRecipe.getIdSpot()).thenReturn(IdSpot.DOCUMENT);
    when(mockRecipe.getIdSelector()).thenReturn("div.meta_area input:hidden#news_id"); //id다음으로 숫자가 나온는 끝부분을 추출
    when(mockRecipe.getTitleSelector()).thenReturn("div.title_area p span");
    when(mockRecipe.getContentSelector()).thenReturn("div div.content span.content_area");

    CrawlerContext mockCtx = mock(CrawlerContext.class);
    when(mockCtx.getParam(CrawlerContextProperty.SEED_URL)).thenReturn(mockUrl);
    when(mockCtx.getParam(CrawlerContextProperty.RECIPE)).thenReturn(mockRecipe);
    
    mockStatic(Jsoup.class);
    when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(mockPage);
    
    News crawledNews = newsContentCrawler.parse(mockCtx);
    verifyStatic();
    assertEquals("id12345", crawledNews.getId());
    assertEquals("This is content", crawledNews.getContent());
    assertEquals("This is Title", crawledNews.getTitle());
  }
  
  private URL getResourceUrl() {
    return getClass().getResource("mock_news_content.html");
  }
}
