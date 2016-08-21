package study.java.project1.crawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import study.java.project1.html.AnchorTag;
import study.java.project1.html.TagElement;
import study.java.project1.model.CrawlRecipe;

public class NewsLinkCrawler implements NewsCrawler<List<TagElement>> {
  public static final int DEFAULT_TIMEOUT_MILLIS = 3000;
  
  @Override
  public List<TagElement> parse(CrawlerContext context) throws Exception {
    CrawlRecipe recipe = context.getParam(CrawlerContextProperty.RECIPE);
    String seedUrl = context.getParam(CrawlerContextProperty.SEED_URL);
    Document doc = Jsoup.parse(new URL(seedUrl), DEFAULT_TIMEOUT_MILLIS);
    Elements elements = doc.select(recipe.getNewsLinkSelector());
    List<TagElement> links = new ArrayList<>();
    elements.forEach(element -> links.add(new AnchorTag(element)));
    return links;
  }
}
