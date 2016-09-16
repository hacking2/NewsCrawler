/**
 * 
 */
package study.java.project1.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;

import study.java.project1.uploader.Uploader;

/**
 * @author hyeon
 *
 */
@Service
public class ImageResolveService {
  @Autowired
  private Uploader imageUploader;
  
  public String uploadAndResolve(String originSrc) throws Exception {
    return imageUploader.upload(originSrc);
  }
}
