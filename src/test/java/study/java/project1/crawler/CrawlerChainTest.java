/**
 * 
 */
package study.java.project1.crawler;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.java.project1.crawler.NewsCrawler.CrawlerContext;
import study.java.project1.html.TagElement;

/**
 * @author hyeon
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CrawlerChainTest {
  @Autowired
  private NewsExtractorChain crawler;
  
  @MockBean
  private NewsLinkCrawler contentsListExtractor;
  
  @MockBean
  private NewsContentCrawler contentsExtractor;
  
  @Test
  public void contentsList가_먼저_호출되고_contentsExtractor가_호출되어야_함() throws Exception {
    TagElement mockTag = mock(TagElement.class);
    when(contentsListExtractor.parse(any(CrawlerContext.class))).thenReturn(Arrays.asList(mockTag));
    crawler.parse(mock(CrawlerContext.class));
    
    InOrder callOrder = inOrder(contentsListExtractor, contentsExtractor);
    callOrder.verify(contentsListExtractor).parse(any(CrawlerContext.class));
    callOrder.verify(contentsExtractor).parse(any(CrawlerContext.class));
  }
  @Configuration
  public static class Config {
    @Bean
    public NewsExtractorChain service() {
      return new NewsExtractorChain();
    }
  }
}
