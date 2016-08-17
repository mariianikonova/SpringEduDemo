package demo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

/**
 * @author Mariia Nikonova
 * @since 8/15/16.
 */
//works after all initializations and constructors, after init methods
public class PostProxyContextListenerImpl implements ApplicationListener<ContextRefreshedEvent> {
  @Autowired
  private ConfigurableListableBeanFactory factory;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    System.out.println("Phase 3: sayQuote() method, works ApplicationListener");
    ApplicationContext context = event.getApplicationContext();
    String[] names = context.getBeanDefinitionNames();
    for (String name : names) {
      BeanDefinition beanDefinition = factory.getBeanDefinition(name);
      String originalClassName = beanDefinition.getBeanClassName();

      Class<?> originalClass = null;
      try {
        originalClass = Class.forName(originalClassName);

        Method[] methods = originalClass.getMethods();
        for (Method method : methods) {
          if (method.isAnnotationPresent(PostProxy.class)) {
            Object bean = context.getBean(name);
            Method currentMethod =
                bean.getClass().getMethod(method.getName(), method.getParameterTypes());
            currentMethod.invoke(bean);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
