package demo5;

import javafx.util.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * @author Mariia Nikonova
 * @since 8/17/16.
 */

//should be registered in config
public class PeriodicalScope implements Scope {

  //Bean Name vs Pair of Time (how much time before bean was instantiated) and Bean
  Map<String, Pair<LocalTime, Object>> map = new HashMap();

  @Override
  public Object get(String name, ObjectFactory<?> objectFactory) {
    if (map.containsKey(name)) {
      Pair<LocalTime, Object> pair = map.get(name);
      int secondsSinceLastRequest = now().getSecond() - pair.getKey().getSecond();
      if (secondsSinceLastRequest > 5) { //5 seconds - by request
        map.put(name, new Pair<>(now(), objectFactory.getObject()));
      }
    } else {
      map.put(name, new Pair<>(now(), objectFactory.getObject()));
    }
    return map.get(name).getValue();
  }

  @Override
  public Object remove(String name) {
    return null;
  }

  @Override
  public void registerDestructionCallback(String name, Runnable callback) {

  }

  @Override
  public Object resolveContextualObject(String key) {
    return null;
  }

  @Override
  public String getConversationId() {
    return null;
  }
}
