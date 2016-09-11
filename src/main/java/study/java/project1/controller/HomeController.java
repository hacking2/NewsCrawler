/**
 * 
 */
package study.java.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author hyeon
 *
 */
@Controller
public class HomeController {
  @Autowired
  private VersionInfo versionInfo;
  
  @RequestMapping("/version")
  public @ResponseBody VersionInfo getVersion(ModelMap model) {
    return versionInfo;
  }
  
  @Component
  private static class VersionInfo {
    @Value("${crawler.version}")
    @JsonProperty
    private String version;
    
    @Value("${crawler.build.timestamp}")
    @JsonProperty
    private String buildTime;
  }
}
