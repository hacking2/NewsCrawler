/**
 * 
 */
package study.java.project1.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import study.java.project1.byproduct.RawNews;
import study.java.project1.model.CrawlRecipe;

/**
 * @author hyeon
 */
public interface CrawlRecipeDao extends CrudRepository<CrawlRecipe, Long> {
  Collection<CrawlRecipe> findByCompany_Id(int id);
}
