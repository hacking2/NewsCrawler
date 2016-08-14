/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.html;

import java.util.Map;

/**
 * @author hyeon
 *
 */
public class AnchorTag implements TagElement {
  private Map<String, String> attr;
  
  @Override
  public String getAttribute(String key) {
    return attr.get(key);
  }

}
