/**
 * 
 */
package study.java.project1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.java.project1.byproduct.RawNews;
import study.java.project1.crawler.NewsCrawler;
import study.java.project1.crawler.NewsCrawler.CrawlerContext;
import study.java.project1.crawler.NewsExtractorChain;
import study.java.project1.dao.CrawlRecipeDao;
import study.java.project1.dao.NewsDao;
import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.News;
import study.java.project1.normalizer.NewsNormalizerChain;

/**
 * @author hyeon
 *
 */
@Service
public class CrawlService {
  @Autowired
  private NewsExtractorChain crawler;
          
  @Autowired
  private CrawlRecipeDao crawlRecipeDao;
  
  @Autowired
  private NewsDao newsDao;
  
  @Autowired
  private NewsNormalizerChain normalizer;
  
  public void execute() {
    CrawlerContext context = new CrawlerContext();
    crawlRecipeDao.findAll().forEach(recipe -> {
      try {
        context.putParam(NewsCrawler.CrawlerContextProperty.RECIPE, recipe);
        List<News> news = crawl(context);
        newsDao.save(news);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
  }
  
  private List<News> crawl(CrawlerContext context) throws Exception {
    List<RawNews> crawledNews = crawler.parse(context);
    return normalizer.normalize(null, crawledNews); //TODO: normalized Recipe는 어떻게?
  }
  
  public List<News> preview(CrawlRecipe recipe) throws Exception {
    CrawlerContext context = new CrawlerContext();
    context.putParam(NewsCrawler.CrawlerContextProperty.RECIPE, recipe);
    return crawl(context);
  }
}
