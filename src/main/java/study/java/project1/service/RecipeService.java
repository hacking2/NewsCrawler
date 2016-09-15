/**
 * 
 */
package study.java.project1.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.java.project1.dao.CrawlRecipeDao;
import study.java.project1.model.CrawlRecipe;

/**
 * @author hyeon
 *
 */
@Service
public class RecipeService {
  @Autowired
  private CrawlRecipeDao recipeDao;
  
  public Collection<CrawlRecipe> receipes(int companyId) {
    return recipeDao.findByCompany_Id(companyId);
  }
  
}
