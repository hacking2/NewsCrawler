/**
 * 
 */
package study.java.project1.normalizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import study.java.project1.byproduct.RawNews;
import study.java.project1.model.News;
import study.java.project1.model.NormalizeRecipe;

/**
 * @author hyeon
 *
 */
@Component
public class ImageSeperator implements NewsNormalizer<RawNews, News> {
  
  @Override
  public News normalize(NormalizeRecipe recipe, RawNews rawNews) {
    String content = rawNews.getRawContent();
    Document doc = Jsoup.parse(content);
    for (Element imgTag : doc.getElementsByTag("img")) {
      String src = imgTag.attr("src");
      if (StringUtils.isEmpty(src)) {
        continue;
      }
      //TODO:image download and replace
    }
    News news = new News();
    news.setContent(rawNews.getRawContent());
    news.setTitle(rawNews.getRawTitle());
    return news;
  }
}
