package jetbrains;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

/**
 * Klasa pomocnicza odpowiadajaca za wybor koloru figury.
 */
public class MyColorItem implements ActionListener {
  private Frame f;
  private JPanel drawPanel;
  public Color mycolor;

  /**
   * Konstruktor klasy.
   *
   * @param f         - okno główne
   * @param drawPanel - panel, służący do rysowania figur
   * @param mycolor   - kolor wybrany przez użytkownika
   */
  public MyColorItem(Frame f, JPanel drawPanel, Color mycolor) {
    this.f = f;
    this.drawPanel = drawPanel;
    this.mycolor = mycolor;
  }

  /**
   * Metoda pokazujca okno dialogowe z wyborem koloru.
   * @param e - akcja uzytkownika
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == "Color") {
      Color one = Color.BLACK;
      mycolor = JColorChooser.showDialog(f, "Select a color", one);
    }
  }
}
