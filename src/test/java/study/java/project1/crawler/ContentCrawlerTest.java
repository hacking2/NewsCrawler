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

import study.java.project1.byproduct.RawNews;
import study.java.project1.crawler.NewsCrawler.CrawlerContext;
import study.java.project1.crawler.NewsCrawler.CrawlerContextProperty;
import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.News;
import study.java.project1.model.CrawlRecipe.IdSpot;
import study.java.project1.model.CrawlRecipe.ValueType;

/**
 * html 파싱 함수가 static이므로 불가피하게 static function을 mocking할 수 있는 PowerMock 라이브러리를 사용함
 * 이 테스트 파일에서는 NewsContentCrawler.class를 테스트한다.
 * @author hyeon
 *
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({ Jsoup.class })
@ContextConfiguration("classpath:study/java/project1/crawler/content_crawl_test.xml")
public class ContentCrawlerTest {

  @Autowired
  private NewsCrawler<RawNews> newsContentCrawler;
  
  /**
   * 이 테스트에서는 기사 id가 기사 url에 심어져 있는 경우를 테스트한다. 
   * 뉴스 페이지는 classpath:/mock_news_content.html을 사용한다.
   * @throws Exception
   */
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
    
    RawNews crawledNews = newsContentCrawler.parse(mockCtx);
    verifyStatic();
    assertEquals("id12345", crawledNews.getId());
    assertEquals("This is content", crawledNews.getRawContent());
    assertEquals("This is Title", crawledNews.getRawTitle());
  }
  
  /**
   * 기사 id가 url에 포함되어 있지 않은 경우 document 어딘가에 있을 것으로 예상되기에
   * document에서 id를 뽑아오는지 테스트한다.
   * 뉴스 페이지는 classpath:/mock_news_content.html을 사용한다.
   * @throws Exception
   */
  @Test
  public void ID가_Document에_있을때_기사제목_내용_ID_잘_긁어오는지_테스트() throws Exception {
    Document mockPage = Jsoup.parse(new File(getResourceUrl().toURI()), "utf-8");
    String mockUrl = "http://myapp.mock.url/mycontent"; //맨 오른쪽이 기사 ID

    //id가 value에 심어져있을 때
    CrawlRecipe mockRecipe = mock(CrawlRecipe.class);
    when(mockRecipe.getIdSpot()).thenReturn(IdSpot.DOCUMENT);
    when(mockRecipe.getIdSelector()).thenReturn("div.meta_area input#news_id_hidden");
    when(mockRecipe.getTitleSelector()).thenReturn("div.title_area p span");
    when(mockRecipe.getContentSelector()).thenReturn("div div.content span.content_area");
    when(mockRecipe.getIdValueType()).thenReturn(ValueType.VALUE);

    CrawlerContext mockCtx = mock(CrawlerContext.class);
    when(mockCtx.getParam(CrawlerContextProperty.SEED_URL)).thenReturn(mockUrl);
    when(mockCtx.getParam(CrawlerContextProperty.RECIPE)).thenReturn(mockRecipe);
    
    mockStatic(Jsoup.class);
    when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(mockPage);
    
    RawNews crawledNews = newsContentCrawler.parse(mockCtx);
    //valuetype이 attr=value일 때 테스트
    assertEquals("hidden12345", crawledNews.getId());
    assertEquals("This is content", crawledNews.getRawContent());
    assertEquals("This is Title", crawledNews.getRawTitle());
    
    //valuetype이 text일 때 테스트
    when(mockRecipe.getIdSelector()).thenReturn("div.meta_area div#news_id_span span");
    when(mockRecipe.getTitleSelector()).thenReturn("div.title_area p span");
    when(mockRecipe.getContentSelector()).thenReturn("div div.content span.content_area");
    when(mockRecipe.getIdValueType()).thenReturn(ValueType.TEXT);
    
    crawledNews = newsContentCrawler.parse(mockCtx);
    assertEquals("span12345", crawledNews.getId());
    assertEquals("This is content", crawledNews.getRawContent());
    assertEquals("This is Title", crawledNews.getRawTitle());
    
    //valuetype이 html일 때 테스트
    when(mockRecipe.getIdSelector()).thenReturn("div.meta_area div#news_id_html");
    when(mockRecipe.getTitleSelector()).thenReturn("div.title_area p span");
    when(mockRecipe.getContentSelector()).thenReturn("div div.content span.content_area");
    when(mockRecipe.getIdValueType()).thenReturn(ValueType.HTML);
    
    crawledNews = newsContentCrawler.parse(mockCtx);
    assertEquals("<div>12345</div>", crawledNews.getId().replaceAll("\\s", ""));//공백무시비교
    assertEquals("This is content", crawledNews.getRawContent());
    assertEquals("This is Title", crawledNews.getRawTitle());
  }
  
  private URL getResourceUrl() {
    return getClass().getResource("mock_news_content.html");
  }
}
