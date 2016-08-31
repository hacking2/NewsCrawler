/**
 * 
 */
package study.java.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import study.java.project1.model.Company;

/**
 * @author hyeon
 *
 */
@Controller
public class ManagementCompanyController {
  @RequestMapping(name = "/company", method = RequestMethod.GET)
  public String managementCompany() {
    return "company";
  }
  
  @RequestMapping(name = "/company", method = RequestMethod.POST)
  public @ResponseBody Company newCompany(Company company) {
    return null;
  }
  
  @RequestMapping(name = "/company", method = RequestMethod.PUT)
  public @ResponseBody Company updateCompany(Company company) {
    return null;
  }
  
  @RequestMapping(name = "/company", method = RequestMethod.DELETE)
  public @ResponseBody boolean removeCompany(Company company) {
    return false;
  }
}
