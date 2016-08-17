package demo5;

import org.springframework.context.annotation.Scope;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.Random;

/**
 * @author Mariia Nikonova
 * @since 8/17/16.
 */

@org.springframework.stereotype.Component
@Scope("singleton")
public abstract class ColorFrame extends JFrame {

  public ColorFrame() throws HeadlessException {
    setSize(200, 200);
    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  public void showOnRandomPlace() {
    Random random = new Random();
    setLocation(random.nextInt(1200), random.nextInt(600));
    //    Q: How to inject prototype into singleton
    //    Good solution:::
    getContentPane().setBackground(getColor());
    repaint();
  }

  //    Q: How to inject prototype into singleton
  //    Good solution:::
  protected abstract Color getColor();

/*
Q: How to inject prototype into singleton
Bad solution::: cause business logic depends on all context
  @Autowired
  private ApplicationContext context;

  public void showOnRandomPlace() {
    Random random = new Random();
    setLocation(random.nextInt(1200), random.nextInt(600));
    getContentPane().setBackground(context.getBean(Color.class));
    repaint();
  }
 */

}
