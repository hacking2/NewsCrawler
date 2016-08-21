package study.java.project1.crawler;

import java.net.URL;
import java.util.regex.Matcher;
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
      Matcher matcher = Pattern.compile(recipe.getIdSelector()).matcher(url);
      if (matcher.groupCount() > 1) {
        throw new Exception("Too many ID candidate in url");
      }
      if (matcher.find()) {
        news.setId(matcher.group()); //매칭 결과는 무조건 1개여야 함
      } else {
        throw new Exception("No such news ID in url");
      }
    } else {
      news.setId(doc.select(recipe.getIdSelector()).text());
    }
    news.setTitle(doc.select(recipe.getTitleSelector()).text());
    news.setContent(doc.select(recipe.getContentSelector()).text());
    return news;
  }
}
