/**
 * 
 */
package study.java.project1.normalizer;

import javax.annotation.PostConstruct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cloudinary.Cloudinary;

import study.java.project1.byproduct.RawNews;
import study.java.project1.model.News;
import study.java.project1.model.NormalizeRecipe;
import study.java.project1.service.ImageResolveService;

/**
 * @author hyeon
 *
 */
@Component
public class ImageSeperator implements NewsNormalizer<RawNews, News> {
  @Autowired
  private ImageResolveService imagePathResolver;
  
  @Override
  public News normalize(NormalizeRecipe recipe, RawNews rawNews) {
    String content = rawNews.getRawContent();
    Document doc = Jsoup.parse(content);
    for (Element imgTag : doc.getElementsByTag("img")) {
      String src = imgTag.absUrl("src");
      if (StringUtils.isEmpty(src)) {
        src = imgTag.absUrl("data-src"); //for image lazy loading page
        if (StringUtils.isEmpty(src)) {
          for (Attribute attr : imgTag.attributes()) {
            if (attr.getKey().contains("src")) {
              src = attr.getValue();
              break;
            }
          }
          if (StringUtils.isEmpty(src)) {
            imgTag.remove(); //img src가 없는 tag는 버림
            continue;
          }
        }
      }
      try {
        src = imagePathResolver.uploadAndResolve(src);
      } catch (Exception e) {
        e.printStackTrace();
        continue;
      }
      Attributes attr = new Attributes();
      attr.put(new Attribute("src", src));
      imgTag.replaceWith(new Element(Tag.valueOf("img"), doc.baseUri(), attr));
      break;
    }
    System.out.println(doc);
    News news = new News();
    news.setContent(rawNews.getRawContent());
    news.setTitle(rawNews.getRawTitle());
    return news;
  }
}
