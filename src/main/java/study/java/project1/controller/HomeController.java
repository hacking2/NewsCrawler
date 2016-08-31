/**
 * 
 */
package study.java.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hyeon
 *
 */
@Controller
public class HomeController {
  @RequestMapping("/version")
  public @ResponseBody String getVersion() {
    return "1.0.0.BUILD-SNAPSHOT";
  }
  
}
