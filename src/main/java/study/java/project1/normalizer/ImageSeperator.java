/**
 * 
 */
package study.java.project1.normalizer;

import org.springframework.stereotype.Component;

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
  public News normalize(NormalizeRecipe recipe, RawNews input) {
    // TODO Auto-generated method stub
    return null;
  }
}
