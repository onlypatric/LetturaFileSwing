import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

class Vertical extends JPanel {

  public static enum AlignmentGuide {
    TOP,
    CENTER,
    BOTTOM,
    LEFT,
    RIGHT
  }

  /**
   * Constructs a new Vertical layout panel with the specified components.
   * 
   * @param items The components to be added to the panel.
   */
  public Vertical(Component... items) {
    super();
    // make component have "background" painted
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    add(items);
  }

  public Vertical opaque(boolean b) {
    this.setOpaque(b);
    return this;
  }

  public Vertical add(Component... items) {
    for (Component component : items)
      if (component != null)
        super.add(component);
    return this;
  }

  public Vertical background(Color color) {
    if (color != null)
      this.opaque(true).setBackground(color);
    else
      this.opaque(false);
    return this;
  }

  public Vertical padding(int padding) {
    super.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
    return this;
  }

  public Vertical title(String title) {
    super.setBorder(BorderFactory.createTitledBorder(super.getBorder(), title));
    return this;
  }

  public Vertical border(Border b) {
    super.setBorder(b);
    return this;
  }

  private float parseAlignment(AlignmentGuide g) {
    return switch (g) {
      case TOP -> Component.TOP_ALIGNMENT;
      case CENTER -> Component.CENTER_ALIGNMENT;
      case BOTTOM -> Component.BOTTOM_ALIGNMENT;
      case LEFT -> Component.LEFT_ALIGNMENT;
      case RIGHT -> Component.RIGHT_ALIGNMENT;
    };
  }

  public Vertical alignmentX(AlignmentGuide g) {
    this.setAlignmentX(
        parseAlignment(g));
    return this;
  }

  public Vertical size(Dimension d) {
    this.setMinimumSize(d);
    return this;
  }

  public Vertical alignmentY(AlignmentGuide g) {
    this.setAlignmentY(
        parseAlignment(g));
    return this;
  }
}