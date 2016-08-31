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
 *  TODO 하이버네이트 설정하고 구현해야 함
 */
public interface CrawlRecipeDao extends CrudRepository<CrawlRecipe, Long> {

}
