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

  @Override
  public List<TagElement> parse(CrawlerContext context) throws Exception {
    CrawlRecipe recipe = context.getParam("recipe");
    String seedUrl = context.getParam("seed");
    Document doc = Jsoup.parse(new URL(seedUrl), 3000);
    Elements elements = doc.select(recipe.getNewsLinkSelector());
    List<TagElement> links = new ArrayList<>();
    elements.forEach(element -> links.add(new AnchorTag(element)));
    return links;
  }
}
