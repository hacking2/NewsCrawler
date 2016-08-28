/**
 * 
 */
package study.java.project1.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hyeon
 *
 */
@Table(name = "company")
public class Company {
  @Id
  @Column
  private int id;
  
  @Column
  private String name;

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
