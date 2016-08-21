package study.java.project1;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;

@SpringBootTest
public class NewsCrawlPlatformApplicationTests {
  @Autowired
  private MockRestServiceServer server;
  
  @Test
  public void contextLoads() {
    System.out.println(server);
  }

}
