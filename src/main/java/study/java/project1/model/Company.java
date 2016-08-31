/**
 * 
 */
package study.java.project1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
