/**
 * 
 */
package study.java.project1.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import study.java.project1.model.Company;
import study.java.project1.service.CompanyService;

/**
 * @author hyeon
 *
 */
@Controller
@RequestMapping(path = "/company")
public class ManagementCompanyController {
  @Autowired
  private CompanyService companyService;
  
  @RequestMapping(path = "/list", method = RequestMethod.GET)
  public String managementCompany(ModelMap model) {
    model.addAttribute("companies", companyService.allCompanies());
    return "company";
  }
  
  @RequestMapping(path = "/new", method = RequestMethod.POST)
  public String newCompany(Company company, ModelMap model) {
    companyService.addCompany(company);
    managementCompany(model);
    return "company";
  }
  
  @RequestMapping(path = "/new/list", method = RequestMethod.POST)
  public String newCompanies(@RequestBody Collection<Company> companies, ModelMap model) {
    companyService.addAllCompany(companies);
    managementCompany(model);
    return "company";
  }
  
//  @RequestMapping(name = "", method = RequestMethod.PUT)
//  public @ResponseBody Company updateCompany(Company company) {
//    return null;
//  }
//  
//  @RequestMapping(name = "", method = RequestMethod.DELETE)
//  public @ResponseBody boolean removeCompany(Company company) {
//    return false;
//  }
}
