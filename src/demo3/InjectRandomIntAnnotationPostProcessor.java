package demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */
public class InjectRandomIntAnnotationPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {

    Field[] fields = bean.getClass().getDeclaredFields();
    for (Field field : fields) {
      InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
      if (annotation != null) {
        int min = annotation.min();
        int max = annotation.max();
        Random random = new Random();
        int i = min + random.nextInt(max-min);
        field.setAccessible(true);
        ReflectionUtils.setField(field, bean, i);
      }
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
    return bean;
  }
}
