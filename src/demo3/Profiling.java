package demo3;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/***
 * all methods of marked class should be profiled and printed to console
 * pattern for class wrapping: DynamicProxy (works if exists interfaces) and CGLib (if doesn`t exists interfaces)
 * Spring by convention prepare all DynamicProxy on AfterInitialization Stage
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Profiling {

}
