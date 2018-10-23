package jetbrains;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JPanel;

/**
 * Pomocnicza klasa odpowiadająca za działanie panelu, który wyświetala informacje o aktualnie
 * modyfikowanej figurze.
 */

public class InformationPanel extends JPanel {
  private Frame f;
  private Label myshape;
  private Label xposition;
  private Label yposition;
  private Label height;
  private Label width;
  private Label myshape2;
  private Label xposition2;
  private Label yposition2;
  private Label height2;
  private Label width2;
  private Double myX;
  private Double myY;
  private Double myH;
  private Double myW;
  private String shapename;

  /**
   * Konstruktor klasy.
   *
   * @param f - Okno głowne
   */
  InformationPanel(Frame f) {
    this.f = f;
    setLayout(new GridLayout(1, 10));
    myshape = new Label("Shape: ");
    add(myshape);
    myshape2 = new Label();
    add(myshape2);
    xposition = new Label("x: ");
    add(xposition);
    xposition2 = new Label();
    add(xposition2);
    yposition = new Label("y: ");
    add(yposition);
    yposition2 = new Label();
    add(yposition2);
    height = new Label("height: ");
    add(height);
    height2 = new Label();
    add(height2);
    width = new Label("width: ");
    add(width);
    width2 = new Label();
    add(width2);

  }

  /**
   * Zapisywanie położenia figury podczas ruchu.
   *
   * @param x współrzędna x wierzchołka głównego
   */
  public void setx(double x) {
    myX = x;
    xposition2.setText(myX.toString());
  }

  /**
   * Zapisywanie położenia figury podczas ruchu.
   *
   * @param y wspolrzedna y wierzchołka głównego
   */
  public void sety(double y) {
    myY = y;
    yposition2.setText(myY.toString());
  }

  /**
   * Zapisywanie położenia figury podczas ruchu.
   *
   * @param h wysokość figury
   */
  public void seth(double h) {
    myH = h;
    height2.setText(myH.toString());
  }

  /**
   * Zapisywanie położenia figury podczas ruchu.
   *
   * @param w szerokość figury
   */
  public void setw(double w) {
    myW = w;
    width2.setText(myW.toString());
  }

  /**
   * Zapiswanie nazwy aktualnie poruszanej figury.
   *
   * @param s nazwa figury
   */
  public void setshape(String s) {
    shapename = s;
    myshape2.setText(shapename);
  }

}
