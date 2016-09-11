/**
 * 
 */
package study.java.project1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author hyeon
 *
 */
@Entity(name = "news_image")
public class NewsImage {
  @Id
  @GeneratedValue
  @Column
  private int id;
  
  @ManyToOne
  @JoinColumn(name = "news_id")
  private News news;
  
  @Column(name = "origin_url")
  private String originUrl;
  
  @Column(name = "static_url")
  private String staticUrl;
  
  @Column(name = "order")
  private int order;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOriginUrl() {
    return originUrl;
  }

  public void setOriginUrl(String originUrl) {
    this.originUrl = originUrl;
  }

  public String getStaticUrl() {
    return staticUrl;
  }

  public void setStaticUrl(String staticUrl) {
    this.staticUrl = staticUrl;
  }

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }
}
