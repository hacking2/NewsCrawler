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
    model.addAttribute("companyId", companyId);
    model.addAttribute("recipes", recipeService.receipes(companyId));
    return "recipe";
  }
  
  @RequestMapping(path = "/new", method = RequestMethod.POST)
  public String newRecipe(CrawlRecipe recipe, int companyId) {
    recipeService.newRecipe(recipe, companyId);
    return "redirect:/recipe/list/" + companyId;
  }
  
  @RequestMapping(path = "/update", method = RequestMethod.POST)
  public String updateRecipe(CrawlRecipe recipe, int companyId) {
    recipeService.updateRecipe(recipe, companyId);
    return "redirect:/recipe/list/" + companyId;
  }
  
  @RequestMapping(path = "/removal", method = RequestMethod.POST)
  public String removeRecipe(int recipeId, int companyId) {
    recipeService.removeRecipe(recipeId);
    return "redirect:/recipe/list/" + companyId;
  }
}
