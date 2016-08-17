package demo5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.awt.Color;
import java.util.Random;

/**
 * @author Mariia Nikonova
 * @since 8/17/16.
 */

//Create Own Bean Scope
//Inject prototype into singleton
//creates one frame, when we do lookup (context.getBean()),
// always we get the same bean and we don`t have any reason to update this bean
// with new Color instance

//Usage: e.g. currency update for bean
@Configuration
@ComponentScan(basePackages = "demo5")
public class JavaConfiguration {
  @Bean
  @Scope("periodical")
  public Color color() {
    Random random = new Random();
    return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
  }


  // Q: How to inject prototype into singleton
  // Good solution::: cause here we have natural access to ApplicationContext
  @Bean
  public ColorFrame frame() {
    return new ColorFrame() {
      @Override
      protected Color getColor() {
        return color(); //appeal to bean and use scope of appealed bean > @Bean @Scope("prototype") public Color color()
      }
    };
  }

/*
  Q: How to inject prototype into singleton
  Not good solution, depends on requirements:::
    @Configuration
    @ComponentScan(basePackages = "demo4")
    public class JavaConfiguration {
      @Bean
      @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS) //proxyMode = ScopedProxyMode.TARGET_CLASS:
      //each time when I cal Color I get a new Bean - Not good.
      public Color color() {
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
      }*/
}
