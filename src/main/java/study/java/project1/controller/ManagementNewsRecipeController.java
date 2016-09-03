/**
 * 
 */
package study.java.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import study.java.project1.model.CrawlRecipe;

/**
 * @author hyeon
 *
 */
@Controller
@RequestMapping("newsrecipe")
public class ManagementNewsRecipeController {
  @RequestMapping(name = "", method = RequestMethod.GET)
  public String managementRecipe() {
    return "recipe";
  }
  
  @RequestMapping(name = "", method = RequestMethod.POST)
  public @ResponseBody CrawlRecipe newRecipe(CrawlRecipe company) {
    return null;
  }
  
  @RequestMapping(name = "", method = RequestMethod.PUT)
  public @ResponseBody CrawlRecipe updateRecipe(CrawlRecipe recipe) {
    return null;
  }
  
  @RequestMapping(name = "", method = RequestMethod.DELETE)
  public @ResponseBody boolean removeRecipe(CrawlRecipe recipe) {
    return false;
  }
}
