/**
 * 
 */
package study.java.project1.dao;

import org.springframework.data.repository.CrudRepository;

import study.java.project1.model.Company;

/**
 * @author hyeon
 *
 */
public interface CompanyDao extends CrudRepository<Company, Integer> {

}
