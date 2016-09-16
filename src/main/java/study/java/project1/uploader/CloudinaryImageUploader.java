/**
 * 
 */
package study.java.project1.uploader;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * @author hyeon
 *
 */
@Component
public class CloudinaryImageUploader implements Uploader {
  @Value("${api.cloudinary.name}") 
  private String cloudName;

  @Value("${api.cloudinary.key}") 
  private String apiKey;

  @Value("${api.cloudinary.secret}")
  private String apiSecret;

  private com.cloudinary.Uploader uploader;

  @PostConstruct
  public void init() {
    this.uploader = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", cloudName,
        "api_key", apiKey,
        "api_secret", apiSecret)).uploader();
  }

  @SuppressWarnings("unchecked")
  @Override
  public String upload(String uri) throws IOException {
    Map<String, Object> result = this.uploader.upload(uri, Collections.emptyMap());
    return (String) result.get("url");
  }

  @SuppressWarnings("unchecked")
  @Override
  public String upload(File file) throws IOException {
    Map<String, Object> result = this.uploader.upload(file, Collections.emptyMap());
    return (String) result.get("url");
  }
}
