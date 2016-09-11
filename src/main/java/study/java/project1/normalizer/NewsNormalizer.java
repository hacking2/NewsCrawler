/**
 * 
 */
package study.java.project1.normalizer;

import study.java.project1.model.NormalizeRecipe;

/**
 * @author hyeon
 *
 */
public interface NewsNormalizer<I, O> {
  O normalize(NormalizeRecipe recipe, I input);
}
