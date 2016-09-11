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
import study.java.project1.model.News;
import study.java.project1.normalizer.NewsNormalizer;

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
  
  @Autowired
  private NewsNormalizer<List<RawNews>, List<News>> normalizer;
  
  public void execute() {
    CrawlerContext context = new CrawlerContext();
    crawlRecipeDao.findAll().forEach(recipe -> {
      context.putParam(NewsCrawler.CrawlerContextProperty.RECIPE, recipe);
      try {
        List<RawNews> crawledNews = crawler.parse(context);
        List<News> refinedNews = normalizer.normalize(null, crawledNews); //TODO: normalized Recipe는 어떻게?
        newsDao.save(refinedNews);
      } catch (Exception e) {
        //TODO report mail
      }
    });
  }
}
