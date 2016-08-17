package demo4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Mariia Nikonova
 * @since 8/17/16.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(JavaConfiguration.class);
    context.getBean(ColorFrame.class).showOnRandomPlace();
    Thread.sleep(100);
  }
}
