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
public class TagRemover implements NewsNormalizer<News, News> {

  @Override
  public News normalize(NormalizeRecipe recipe, News input) {
    // TODO Auto-generated method stub
    return null;
  }
}
