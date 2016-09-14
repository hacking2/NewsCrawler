package study.java.project1;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:properties/version.properties", encoding = "UTF-8")
public class AppConfiguration {

}
