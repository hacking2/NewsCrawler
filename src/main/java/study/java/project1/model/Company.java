/**
 * 
 */
package study.java.project1.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author hyeon
 *
 */
@Entity(name = "company")
public class Company {
  @Id
  @GeneratedValue
  @Column
  private int id;
  
  @Column
  private String name;

  @Column
  private String description;
  
  @Column
  private String image;
  
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private Collection<CrawlRecipe> recipes;
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
