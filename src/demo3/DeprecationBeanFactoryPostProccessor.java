package demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Mariia Nikonova
 * @since 8/16/16.
 */

//works on stage when exist only beanDefinition, no any Beans
public class DeprecationBeanFactoryPostProccessor implements BeanFactoryPostProcessor {
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    String[] beanDefintionNames = beanFactory.getBeanDefinitionNames();
    for (String name : beanDefintionNames) {
      BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
      String beanClassName = beanDefinition.getBeanClassName();
      try {
        Class<?> beanClass = Class.forName(beanClassName);
        DeprecatedClass annotation = beanClass.getAnnotation(DeprecatedClass.class);
        if (annotation != null) {
          beanDefinition.setBeanClassName(annotation.newImpl().getName()); //get name of new class - never do it in prod
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
