package study.java.project1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import study.java.project1.crawler.ContentCrawlerTest;
import study.java.project1.crawler.CrawlerChainTest;
import study.java.project1.crawler.CrawlerTest;
import study.java.project1.service.CrawlerServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
  ContentCrawlerTest.class,
  CrawlerChainTest.class,
  CrawlerTest.class,
  CrawlerServiceTest.class
})
public class AllTests {

}
