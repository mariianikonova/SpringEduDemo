package demo3;


/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    PropertyFileApplicationContext context = new PropertyFileApplicationContext("application.properties");
    while (true) {
      Thread.sleep(1000);
      context.getBean(Quoter.class).sayQuote();
    }
  }
}
