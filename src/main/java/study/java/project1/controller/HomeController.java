/**
 * 
 */
package study.java.project1.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hyeon
 *
 */
@Controller
public class HomeController {
  @Value("${crawler.version}")
  private String version;
  
  @RequestMapping("/version")
  public String getVersion(ModelMap model) {
    model.addAttribute("version", version);
    return "home";
  }
}
