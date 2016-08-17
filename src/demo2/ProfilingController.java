package demo2;

/**
 * @author Mariia Nikonova
 * @since 8/10/16.
 */
public class ProfilingController implements ProfilingControllerMBean  {

  private boolean isEnabled = true;

  public boolean isEnabled() {
    return isEnabled;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }
}
