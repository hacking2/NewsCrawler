/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.crawler;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.*;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
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
import study.java.project1.html.TagElement;
import study.java.project1.model.CrawlRecipe;

/**
 * @author hyeon
 *
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({ Jsoup.class })
//왜인지 모르겠지만 PowerMock은 JavaConfig가 안 됨..
@ContextConfiguration("classpath:study/java/project1/crawler/crawl_test.xml")
public class CrawlerTest {
	@Autowired
	private NewsCrawler<List<TagElement>> newsLinkCrawler;

	@Before
	public void init() throws Exception {
		
	}

	@Test
	public void 게시판형_뉴스페이지의_seed에서_뉴스목록_뽑아오기() throws Exception {
	  final String mockUrlStr = "http://news.mock.myapp";
	  CrawlRecipe mockRecipe = mock(CrawlRecipe.class);
	  when(mockRecipe.getSeedUrl()).thenReturn(mockUrlStr);
    when(mockRecipe.getNewsLinkSelector()).thenReturn("ul li.edit a");
    
    CrawlerContext mockContext = mock(CrawlerContext.class);
    when(mockContext.getParam(CrawlerContextProperty.RECIPE)).thenReturn(mockRecipe);

    //parsing result mock
    URL expectedDocUrl = getClass().getResource("mock_news.html");
    File expectedResource = new File(expectedDocUrl.toURI());
    Document mockDoc = Jsoup.parse(expectedResource, "utf-8");

    mockStatic(Jsoup.class);
    when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(mockDoc);    
    
	  //parse 메소드 내에서, Jsoup.parse는 mocking 될 것이다.
    List<TagElement> elements = newsLinkCrawler.parse(mockContext);
		assertEquals(3, elements.size());
		assertEquals("first", elements.get(0).getAttribute("href"));
		assertEquals("second", elements.get(1).getAttribute("href"));
		assertEquals("third", elements.get(2).getAttribute("href"));
	}
}
