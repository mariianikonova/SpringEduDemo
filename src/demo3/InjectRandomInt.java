package demo3;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
   int max();
   int min();
}
