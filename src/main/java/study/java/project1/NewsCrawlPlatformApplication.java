package study.java.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "study.java.project1.model")
@EnableJpaRepositories(basePackages = "study.java.project1.dao")
public class NewsCrawlPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsCrawlPlatformApplication.class, args);
	}
}
