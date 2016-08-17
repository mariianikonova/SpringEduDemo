package demo1;

/**
 * @author Mariia Nikonova
 * @since 8/9/16.
 */
public class QuoterImpl implements Quoter {

  private String message;

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public void sayQuote() {
    System.out.println("message = " + message);
  }
}
