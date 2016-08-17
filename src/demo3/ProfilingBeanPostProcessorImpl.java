package demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */
public class ProfilingBeanPostProcessorImpl implements BeanPostProcessor {
  private Map<String, Class> map = new HashMap();  //bean name to bean wrapped class
  private ProfilingController controller = new ProfilingController();

  public ProfilingBeanPostProcessorImpl() throws Exception {
    MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
    mBeanServer.registerMBean(controller, new ObjectName("mProfiling", "mName", "mProfilingController"));
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    Class<?> beanClass = bean.getClass();
    if (beanClass.isAnnotationPresent(Profiling.class)) {
      map.put(beanName, beanClass);
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("Phase 2: works postProcessAfterInitialization: Profiling of sayQuote() method");
    Class beanClass = bean.getClass();
    if (map.containsKey(beanName)) {
      return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
          new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              if (controller.isEnabled()) {
                System.out.println("profiling started .... ");
                long before = System.nanoTime();
                Object retVal = method.invoke(bean, args);
                long after = System.nanoTime();
                System.out.println("time for op: " + (after - before));
                System.out.println("profiling finished");
                return retVal;
              } else {
                return method.invoke(bean, args);
              }
            }
          }

      );//dynamic proxy pattern
    }
    return bean;
  }


  public ProfilingController getController() {

    return controller;
  }

  public void setController(ProfilingController controller) {
    this.controller = controller;
  }


  public Map<String, Class> getMap() {
    return map;
  }

  public void setMap(Map<String, Class> map) {
    this.map = map;
  }
}
