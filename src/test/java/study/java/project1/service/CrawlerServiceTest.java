/**
 * 
 */
package study.java.project1.service;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import study.java.project1.byproduct.RawNews;
import study.java.project1.crawler.NewsCrawler;
import study.java.project1.crawler.NewsCrawler.CrawlerContext;
import study.java.project1.dao.CrawlRecipeDao;
import study.java.project1.dao.NewsDao;
import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.News;
import study.java.project1.normalizer.NewsNormalizer;

/**
 * @author hyeon
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CrawlerServiceTest {
  @Autowired
  private CrawlService service;
  
  @MockBean
  private NewsCrawler<List<RawNews>> crawler;
  
  @MockBean
  private CrawlRecipeDao recipeDao;
  
  @MockBean
  private NewsDao newsDao;
  
  @MockBean
  private NewsNormalizer<List<RawNews>, List<News>> normalizer;
  
  @SuppressWarnings("unchecked")
  @Test
  public void 크롤서비스_내에서_crawler_호출_후_db_인서트가_수행되어야_함() throws Exception {
    when(recipeDao.findAll()).thenReturn(Arrays.asList(mock(CrawlRecipe.class))); //list를 최소 한 번 돌게 하기 위해서 mock list를 리턴하게 함
    when(crawler.parse(any())).thenReturn(mock(List.class));
    when(normalizer.normalize(any(), any())).thenReturn(mock(List.class));
    
    service.execute();
    InOrder methodCallOrder = inOrder(crawler, recipeDao, normalizer, newsDao);
    methodCallOrder.verify(recipeDao).findAll();
    methodCallOrder.verify(crawler).parse(any(CrawlerContext.class));
    methodCallOrder.verify(normalizer).normalize(any(), any(List.class));
    methodCallOrder.verify(newsDao).save(any(Collection.class));
  }
  
  @Configuration
  public static class Config {
    @Bean
    public CrawlService service() {
      return new CrawlService();
    }
  }
}
