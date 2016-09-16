package study.java.project1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
@PropertySource(value = "classpath:properties/version.properties", encoding = "UTF-8")
@PropertySource(value = "classpath:properties/external_api.properties", encoding = "UTF-8")
@PropertySource(value = "file:/data/external_api.properties", ignoreResourceNotFound=true, encoding = "UTF-8")
public class AppConfiguration {

}
