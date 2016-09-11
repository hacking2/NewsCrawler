/**
 * 
 */
package study.java.project1.normalizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import study.java.project1.byproduct.RawNews;
import study.java.project1.model.News;
import study.java.project1.model.NormalizeRecipe;

/**
 * @author hyeon
 *
 */
@Component
public class NewsNormalizerChain implements NewsNormalizer<RawNews, News> {
  @Autowired
  private ImageSeperator imageSeperator;
  
  @Autowired
  private TagRemover tagRemover;
  
  @Override
  public News normalize(NormalizeRecipe recipe, RawNews input) {
    return tagRemover.normalize(recipe, imageSeperator.normalize(recipe, input));
  }
}
