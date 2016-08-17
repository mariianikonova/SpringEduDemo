package demo3;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author Mariia Nikonova
 * @since 8/16/16.
 */
public class PropertyFileApplicationContext extends GenericApplicationContext {

  public PropertyFileApplicationContext(String fileName) {
    PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(
        this); //as parameter is context for which beans will be registered
    int i = reader.loadBeanDefinitions(fileName);
    System.out.println( i + " beans founded in context.properties...");
    refresh(); //context does it always when finishes bean adding
  }
}

