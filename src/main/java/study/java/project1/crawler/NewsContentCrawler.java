package study.java.project1.crawler;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import study.java.project1.byproduct.RawNews;
import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.CrawlRecipe.IdSpot;

public class NewsContentCrawler implements NewsCrawler<RawNews> {
  @Override
  public RawNews parse(CrawlerContext context) throws Exception {
    String url = context.getParam(CrawlerContextProperty.SEED_URL);
    CrawlRecipe recipe = context.getParam(CrawlerContextProperty.RECIPE);
    Document doc = Jsoup.parse(new URL(url), NewsCrawler.DEFAULT_TIMEOUT_MILLIS);
    RawNews news = new RawNews();
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
      Elements idElement = doc.select(recipe.getIdSelector());
      String id = null;
      switch (recipe.getIdValueType()) {
        case VALUE :
          id = idElement.val();
          break;
        case TEXT :
          id = idElement.text();
          break;
        case HTML :
          id = idElement.html();
          break;
        default : 
          throw new IllegalArgumentException("Only Value, Text, Html!");
      }
      news.setId(id);
    }
    news.setRawTitle(doc.select(recipe.getTitleSelector()).html());
    news.setRawContent(doc.select(recipe.getContentSelector()).html());
    return news;
  }
}
