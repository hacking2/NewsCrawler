/**
 * 
 */
package study.java.project1.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.java.project1.dao.CompanyDao;
import study.java.project1.model.Company;

/**
 * @author hyeon
 *
 */
@Service
public class CompanyService {
  @Autowired
  private CompanyDao companyDao;

  public Iterable<Company> allCompanies() {
    return companyDao.findAll();
  }
  
  public void addCompany(Company company) {
    companyDao.save(company);
  }
  
  public void addAllCompany(Iterable<Company> companies) {
    companyDao.save(companies);
  }
  
  public void deleteCompany(Integer companyId) {
    companyDao.delete(companyId);
  }
  
  public void modify(Company company) {
    companyDao.save(company);
  }
}
