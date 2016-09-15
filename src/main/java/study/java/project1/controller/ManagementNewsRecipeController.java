/**
 * 
 */
package study.java.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import study.java.project1.dao.CrawlRecipeDao;
import study.java.project1.model.CrawlRecipe;
import study.java.project1.service.RecipeService;

/**
 * @author hyeon
 *
 */
@Controller
@RequestMapping(path = "/recipe")
public class ManagementNewsRecipeController {
  @Autowired
  private RecipeService recipeService;
  
  @RequestMapping(path = "/list/{companyId}", method = RequestMethod.GET)
  public String managementRecipe(@PathVariable int companyId, ModelMap model) {
    model.addAttribute("recipes", recipeService.receipes(companyId));
    return "recipe";
  }
  
  @RequestMapping(path = "", method = RequestMethod.POST)
  public @ResponseBody CrawlRecipe newRecipe(CrawlRecipe company) {
    return null;
  }
  
  @RequestMapping(path = "", method = RequestMethod.PUT)
  public @ResponseBody CrawlRecipe updateRecipe(CrawlRecipe recipe) {
    return null;
  }
  
  @RequestMapping(path = "", method = RequestMethod.DELETE)
  public @ResponseBody boolean removeRecipe(CrawlRecipe recipe) {
    return false;
  }
}
