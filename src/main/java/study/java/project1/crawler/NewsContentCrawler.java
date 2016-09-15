package study.java.project1.crawler;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import study.java.project1.byproduct.RawNews;
import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.CrawlRecipe.IdSpot;

/**
 * 이 크롤러에 의해 정제되지 않은 기사 제목, 내용과 정제된 기사 ID를 뽑아온다.
 * 정제의 의미는 텍스트만 깨끗하게 있지 않고, 이미지가 분리되지 않았으며, 기타 잡Tag들이 있는 경우를 말한다.
 * @author hyeon
 *
 */
@Component
public class NewsContentCrawler implements NewsCrawler<RawNews> {
  @Override
  public RawNews parse(CrawlerContext context) throws Exception {
    String url = context.getParam(CrawlerContextProperty.SEED_URL);
    CrawlRecipe recipe = context.getParam(CrawlerContextProperty.RECIPE);
    Document doc = Jsoup.parse(new URL(url), NewsCrawler.DEFAULT_TIMEOUT_MILLIS);
    RawNews news = new RawNews();
    //url에 기사 id가 심어져 있는 경우는 정규식으로 기사id를 추출하도록 한다.
    if (recipe.getIdSpot() == IdSpot.URL) {
      Matcher matcher = Pattern.compile(recipe.getIdSelector()).matcher(url);
      if (matcher.groupCount() > 1) { //id는 하나만 매칭되는 정규식이어야 한다.
        throw new Exception("Too many ID candidate in url");
      }
      if (matcher.find()) {
        news.setId(matcher.group()); //매칭 결과는 무조건 1개여야 함
      } else {
        throw new Exception("No such news ID in url");
      }
    } else {
      //url에 id가 없는 특수한 경우, document에서 찾도록 한다.
      Elements idElement = doc.select(recipe.getIdSelector());
      String id = null;
      switch (recipe.getIdValueType()) {
        case VALUE :  //select되는 것이 input 등의 경우 value attribute에서 뽑아온다.
          id = idElement.val();
          break;
        case TEXT : //span 등 텍스트로 존재할 경우
          id = idElement.text();
          break;
        case HTML : //HTML인 경우는 아마 없을 것 같지만 그래도 일단 가능성은 열어둔다.
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
