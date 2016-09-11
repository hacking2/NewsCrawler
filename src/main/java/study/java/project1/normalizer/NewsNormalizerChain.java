/**
 * 
 */
package study.java.project1.normalizer;

import java.util.ArrayList;
import java.util.List;

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
public class NewsNormalizerChain implements NewsNormalizer<List<RawNews>, List<News>> {
  @Autowired
  private ImageSeperator imageSeperator;
  
  @Autowired
  private TagRemover tagRemover;
  
  @Override
  public List<News> normalize(NormalizeRecipe recipe, List<RawNews> input) {
    List<News> newsList = new ArrayList<>();
    for (RawNews rawNews : input) {
      newsList.add(tagRemover.normalize(recipe, imageSeperator.normalize(recipe, rawNews)));
    }
    return newsList;
  }
}
