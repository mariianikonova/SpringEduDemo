package demo1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */
public class Main {

  public static void main(String[] args) {
     ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app_Demo1.xml");
    context.getBean(QuoterImpl.class).sayQuote();
  }
}
