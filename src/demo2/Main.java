package demo2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app_Demo2.xml");
    while (true) {
      Thread.sleep(1000);
      context.getBean(demo2.Quoter.class).sayQuote();
    }
  }
}
