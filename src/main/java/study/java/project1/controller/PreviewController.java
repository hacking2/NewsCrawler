/**
 * 
 */
package study.java.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import study.java.project1.model.CrawlRecipe;
import study.java.project1.model.News;
import study.java.project1.service.CrawlService;

/**
 * @author hyeon
 *
 */
@Controller
@RequestMapping(path = "/preview")
public class PreviewController {
  @Autowired
  private CrawlService crawlService;
  
  @RequestMapping(path = "/crawl", method = RequestMethod.GET)
  public @ResponseBody List<News> managementRecipe(CrawlRecipe recipe) throws Exception {
    return crawlService.preview(recipe);
  }
}
