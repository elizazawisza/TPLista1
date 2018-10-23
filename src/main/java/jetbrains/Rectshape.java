package jetbrains;

import java.awt.geom.Rectangle2D;

/**
 * Klasa prostokąta zawierająca zmienne rzecywiste.
 */

public class Rectshape extends Rectangle2D.Float {

  /**
   * Konstruktor klasy tworzący obiekt.
   *
   * @param x      współrzędna wierzchołka głównego
   * @param y      współrzędna wierzchołka głownego
   * @param width  szerokość prostokąta
   * @param height wysokość prostokąta
   */
  public Rectshape(float x, float y, float width, float height) {

    setRect(x, y, width, height);
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
   * Metoda aktualizująca szerokość prostokąta.
   *
   * @param w szerokość prostokąta
   */

  public void addWidth(float w) {

    this.width += w;
  }

  /**
   * Metoda aktualizująca wysokość prostokąta.
   *
   * @param h wysokość prostokąta
   */

  public void addHeight(float h) {

    this.height += h;
  }
}
