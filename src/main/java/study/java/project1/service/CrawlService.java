/**
 * 
 */
package study.java.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import study.java.project1.byproduct.RawNews;
import study.java.project1.crawler.NewsCrawler;
import study.java.project1.crawler.NewsCrawler.CrawlerContext;
import study.java.project1.dao.CrawlRecipeDao;
import study.java.project1.dao.NewsDao;

/**
 * @author hyeon
 *
 */
public class CrawlService {
  @Autowired
  private NewsCrawler<List<RawNews>> crawler;
  
  @Autowired
  private CrawlRecipeDao crawlRecipeDao;
  
  @Autowired
  private NewsDao newsDao;
  
  public void execute() {
    CrawlerContext context = new CrawlerContext();
    crawlRecipeDao.findAll().forEach(recipe -> {
      context.putParam(NewsCrawler.CrawlerContextProperty.RECIPE, recipe);
      try {
        List<RawNews> crawledNews = crawler.parse(context);
        //TODO normalizer 거치고 insert 되야 함, 테스트에 verify 추가해야 함
        newsDao.save(crawledNews);
      } catch (Exception e) {
        //TODO report mail
      }
    });
  }
}
