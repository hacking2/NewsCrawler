/**
 * Copyright (C) 2016, hyeon mun-duk
 */
package study.java.project1.html;

import org.jsoup.nodes.Element;

/**
 * @author hyeon
 *
 */
public class AnchorTag implements TagElement {
  private Element element;
  
  public AnchorTag(Element elem) {
    this.element = elem;
  }
  @Override
  public String getAttribute(String key) {
    return element.attr(key);
  }

}
