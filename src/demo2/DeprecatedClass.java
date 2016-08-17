package demo2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Mariia Nikonova
 * @since 8/16/16.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedClass {
  Class newImpl();
}
