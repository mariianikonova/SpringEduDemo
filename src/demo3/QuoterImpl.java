package demo3;

import javax.annotation.PostConstruct;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */

//all beans marked annotation deprecated replaced with newImpl of bean
@DeprecatedClass(newImpl = QuoterNewImpl.class)
@Profiling
public class QuoterImpl implements Quoter {

  private String message;

  public int getRepeat() {
    return repeat;
  }

  public void setRepeat(int repeat) {
    this.repeat = repeat;
  }

  public String getMessage() {
    return message;
  }

  @InjectRandomInt(min = 2, max = 7)
  //depends on InjectRandomIntAnnotationPostProcessor in context.xml
  private int repeat;

  public QuoterImpl() {
    System.out.println("Phase 1: default java constructor");
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @PostConstruct //depends on CommonAnnotationBeanPostProcessor in context.xml
  public void init() {
    System.out.println("Phase 2: Init method (works CommonAnnotationBeanPostProcessor)");
    System.out.println("Phase 2, repeat: " + repeat);
  }

  @Override
  @PostProxy //third phase constructor
  public void sayQuote() {
    for (int i = 0; i < repeat; i++) {
      System.out.println("message = " + message);
    }
  }
}
