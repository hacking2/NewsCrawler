/**
 * 
 */
package study.java.project1.uploader;

import java.io.File;

/**
 * @author hyeon
 *
 */
public interface Uploader {
  public String upload(String uri) throws Exception;
  public String upload(File file) throws Exception;
}
