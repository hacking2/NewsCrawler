package study.java.project1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;

import study.java.project1.crawler.ContentsListExtractor;

@SpringBootTest
public class NewsCrawlPlatformApplicationTests {
  @Autowired
  private MockRestServiceServer server;
  
  @Test
  public void contextLoads() {
    System.out.println(server);
  }

}
