package jetbrains;

import java.awt.geom.Ellipse2D;

/**
 * Klasa okręgu zawierające zmienne rzeczywiste.
 */
public class Ellipshape extends Ellipse2D.Float {

  /**
   * Konstruktor klasy twrzącej koło.
   *
   * @param x      współrzędna wierzchołka głównego prostokąta, na którego bazie rysowany jest okrąg
   * @param y      współrzędna wierzchołka głównego prostokąta, na którego bazie rysowany jest okrąg
   * @param width  szerokość koła
   * @param height wysokość koła
   */
  public Ellipshape(float x, float y, float width, float height) {

    setFrame(x, y, width, height);
  }

  /**
   * Metoda aktualizująca położenie głównego wierzchołka x.
   *
   * @param x współrzędna x głównego wierzchołka
   */
  public void addX(float x) {

    this.x += x;
  }

  /**
   * Metoda aktualizująca położenie głównego wierzchołka y.
   *
   * @param y współrzędna y głównego wierzchołka
   */
  public void addY(float y) {

    this.y += y;
  }

  /**
   * Metoda aktualizująca szerokość okręgu.
   *
   * @param w szerokość okręgu
   */
  public void addWidth(float w) {

    this.width += w;
  }

  /**
   * Metoda aktualizująca wysokość okręgu.
   *
   * @param h wysokość okręgu
   */

  public void addHeight(float h) {

    this.height += h;
  }


}

