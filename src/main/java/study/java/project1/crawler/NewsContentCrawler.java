package study.java.project1.crawler;

import java.net.URL;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.CrawlRecipe.IdSpot;
import study.java.project1.model.News;

public class NewsContentCrawler implements NewsCrawler<News> {
  @Override
  public News parse(CrawlerContext context) throws Exception {
    String url = context.getParam(CrawlerContextProperty.SEED_URL);
    CrawlRecipe recipe = context.getParam(CrawlerContextProperty.RECIPE);
    Document doc = Jsoup.parse(new URL(url), 3000);
    News news = new News();
    if (recipe.getIdSpot() == IdSpot.URL) {
      Pattern regex = Pattern.compile(recipe.getIdSelector());
      news.setId(regex.matcher(url).group());
    } else {
      news.setId(doc.select(recipe.getIdSelector()).text());
    }
    news.setTitle(doc.select(recipe.getTitleSelector()).text());
    news.setContent(doc.select(recipe.getContentSelector()).text());
    return news;
  }
}
