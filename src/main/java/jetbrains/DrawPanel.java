package jetbrains;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;



/**
 * Klasa odpowiedzialna za panel, na którym rysowane są figury.
 */
public class DrawPanel extends JPanel implements ActionListener {
  private int x1;
  private int x2;
  private int y1;
  private int y2;
  private Frame f;
  private InformationPanel panel;
  private JPanel masa;
  private JPopupMenu popup;
  private JMenuItem color;
  public Color mycolor;
  private ArrayList<Shape> shapes;
  private ArrayList<Color> colors;
  private ArrayList<Integer> pointsX = new ArrayList<>();
  private ArrayList<Integer> pointsY = new ArrayList<>();
  private boolean polygonready = false;
  public static String shapetype;
  public static String modifying;
  private MyColorItem objcolor;

  /**
   * Konstruktor klasy.
   *
   * @param f      okno główne
   * @param panel  panel, na którym wyświetlane są informacje dotyczące aktualnego
   *               położenia figury przy ruszaniu nią
   * @param shapes lista przechowująca rysowane przez uytkownika kształty
   * @param colors lista przechowująca kolory rysowanych przez użytwownika kształtów
   */

  DrawPanel(Frame f, InformationPanel panel, ArrayList<Shape> shapes, ArrayList<Color> colors) {
    this.f = f;
    this.panel = panel;
    this.shapes = shapes;
    this.colors = colors;

    MovingAdapter movea = new MovingAdapter();
    MyDrawingListener drawListener = new MyDrawingListener();
    addMouseListener(movea);
    addMouseMotionListener(movea);
    addMouseListener(drawListener);
    addMouseMotionListener(drawListener);
    addMouseWheelListener(new ScalingAdapter());
    popup = new JPopupMenu();
    color = new JMenuItem("Color");
    objcolor = new MyColorItem(f, this, mycolor);
    color.addActionListener(objcolor);
    popup.add(color);
    setComponentPopupMenu(popup);
    masa = new JPanel();
    add(masa);
    setLayout(null);
  }

  /**
   * Metoda określająca jaka czynność będzie teraz wykonywana przez użytkownika.
   *
   * @param me akcja wywołana przez użytkownika
   */
  public void actionPerformed(ActionEvent me) {
    String text;
    text = me.getActionCommand().toString();
    if (text.equals("Polygon") || text.equals("Circle") || text.equals("Rectangle")) {
      shapetype = text;
      modifying = "";
    } else {
      modifying = text;
      shapetype = "";
    }

  }

  /**
   * Klasa, która służy o rysowania figur geometrycznych. Wielokąt jest rysowany
   * poprzez łączenie punktów, w których użytkownik kliknął myszką. Ostatni punkt, po którym
   * następuje narysowanie wielokąta
   * jest oznaczany poprzez podówójne kliknięcie myszką w jednym miejscu.
   * Rysowanie koła i prostokąta następuje po naciśnieciu myszki, przeciągnięciu kursora myszki
   * po ekranie, a następnie zwolnienie przycisku myszki.
   */


  class MyDrawingListener extends MouseAdapter {
    /**
     * Metoda dzięki którejnastępuje rysowanie wielokąta oraz zmiana koloru aktualnie
     * klikniętej figury w zależności od tego która opcja modify zostanie wybrana oraz
     * króry przycisk myszy zostanie klikniety.
     *
     * @param me akcja wywołana przez użytkownika
     */
    public void mouseClicked(MouseEvent me) {
      if (me.isMetaDown()) {
        popup.show(me.getComponent(), me.getX(), me.getY());
      }
      if (me.isMetaDown() == false && shapetype == "" && modifying != "") {
        for (int i = 0; i < shapes.size(); i++) {
          Shape sh = shapes.get(i);
          if (sh.contains(me.getX(), me.getY())) {
            colors.set(i, objcolor.mycolor);
            repaint();
          }

        }
      }

      Shape shape = null;
      if (shapetype == "Polygon") {
        int x = me.getX();
        int y = me.getY();
        switch (me.getClickCount()) {
          case 1:
            if (polygonready) {
              pointsX.clear();
              pointsY.clear();
              polygonready = false;
            }
            pointsX.add(x);
            pointsY.add(y);
            break;

          case 2:
            polygonready = true;
            pointsX.add(x);
            pointsY.add(y);
            break;

          default:
            break;
        }
        int[] px = new int[pointsX.size()];
        int[] py = new int[pointsX.size()];
        for (int i = 0; i < px.length; i++) {
          px[i] = pointsX.get(i).intValue();
          py[i] = pointsY.get(i).intValue();
        }
        if (polygonready) {
          shape = new Polygon(px, py, px.length);
        }
        if (shape != null) {
          shapes.add(shape);
          setcol();
          repaint();
        }

      }
    }

    /**
     * Metoda pobierająca współrzędne x i y początkowego punktu naciśnięcia myszki podczas
     * rysowania figury.
     *
     * @param e akcja wywołana przez użytkownika
     */
    public void mousePressed(MouseEvent e) {
      x1 = e.getX();
      y1 = e.getY();
    }

    /**
     * Metoda pobierająca współrzędne x i y w trakcie przesuwania myszki podczas rysowania figury.
     *
     * @param e akcja wywolana przez uzytkownika
     */
    public void mouseDragged(MouseEvent e) {
      x2 = e.getX();
      y2 = e.getY();
    }

    /**
     * Metoda, która pobiera współrzędne x i y po zakończeniu przesuwania rysowania figury.
     *
     * @param e akcja wywolana przez uzytkownika
     */
    public void mouseReleased(MouseEvent e) {
      x2 = e.getX();
      y2 = e.getY();
      Shape shape = null;
      if (shapetype == "Rectangle") {
        shape = new Rectshape(Math.min(x1, x2), Math.min(y1, y2),
                Math.abs(x1 - x2), Math.abs(y1 - y2));
      } else if (shapetype == "Circle") {
        shape = new Ellipshape(Math.min(x1, x2), Math.min(y1, y2),
                Math.abs(x1 - x2), Math.abs(x1 - x2));

      }
      if (shape != null) {
        shapes.add(shape);
        setcol();
        repaint();

      }
    }
  }

  /**
   * Metoda, w kórej ustawiany jest początkowy kolor każdej figury (czarny).
   */
  public void setcol() {
    for (int h = colors.size(); h < shapes.size(); h++) {
      colors.add(Color.BLACK);
    }
  }

  /**
   * Metoda rysujaca ksztłty na panelu.
   *
   * @param g - grafika
   */
  public void paint(Graphics g) {
    super.paintComponents(g);
    Graphics2D g2 = (Graphics2D) g;
    for (int i = 0; i < shapes.size(); i++) {
      Shape s = shapes.get(i);
      g2.setPaint(colors.get(i));
      g2.fill(s);
    }
  }

  /**
   * Klasa odpowiedzialna za modyfikowanie figury.  Zmiana położnenia figury następuje
   * po kliknięciu na nią, a następnie przejechaniu figurą w miejsce, w które chcemy przenieść
   * figurę. Zmiana położenia figury możliwa jest tylko po wcześniejszym wybraniu opcji 'moving'
   * w menu modify.
   */


  class MovingAdapter extends MouseAdapter {


    private int x;
    private int y;

    /**
     * Metoda pobierająca współrzędne x i y na początku wywołania ruszania kształtu.
     *
     * @param e akcja wywolana przez uzytkownika
     */
    public void mousePressed(MouseEvent e) {

      x = e.getX();
      y = e.getY();
    }

    /**
     * Metoda pobierająca współrzędne x i y w trakcie przesuwania myszki podczas poruszania figury.
     *
     * @param e akcja wywolana przez uzytkownika
     */
    public void mouseDragged(MouseEvent e) {

      doMove(e);
    }

    /**
     * Metoda aktualizująca parametry figury.
     *
     * @param e akcja wywołana przez użytkownika
     */
    private void doMove(MouseEvent e) {
      if (modifying == "Moving") {
        int dx = e.getX() - x;
        int dy = e.getY() - y;

        for (int i = 0; i < shapes.size(); i++) {
          Shape s = shapes.get(i);
          if (s.contains(x, y) && s instanceof Rectshape) {
            Rectshape shr = (Rectshape) s;
            shr.addY(dy);
            shr.addX(dx);
            panel.setshape("Rectangle");
            panel.sety(shr.getY());
            panel.setx(shr.getX());
            panel.seth(shr.getHeight());
            panel.setw(shr.getWidth());
            repaint();
          } else if (s.contains(x, y) && s instanceof Ellipshape) {
            Ellipshape she = (Ellipshape) s;
            she.addX(dx);
            she.addY(dy);
            panel.setshape("Circle");
            panel.sety(she.getY());
            panel.setx(she.getX());
            panel.seth(she.getHeight());
            panel.setw(she.getWidth());
            repaint();
          } else if (s.contains(x, y)) {
            AffineTransform at = new AffineTransform();
            at.translate(dx, dy);
            shapes.set(i, at.createTransformedShape(s));
            panel.setshape("Polygon");
            panel.sety(0);
            panel.setx(0);
            panel.seth(0);
            panel.setw(0);
            repaint();
          }
        }
        x += dx;
        y += dy;
      } else {
        panel.setshape(" ");
        panel.sety(0);
        panel.setx(0);
        panel.seth(0);
        panel.setw(0);

      }
    }

  }

  /**
   * Klasa odpowiedzialna za modyfikowanie figury. Zmiana rozmiaru następuje poprzez scrollowanie.
   * Zmiana rozmiaru jest możliwa tylko po wcześniejszym wybraniu opcji 'resizing' w menu modify.
   */

  class ScalingAdapter implements MouseWheelListener {
    /**
     * Metoda wywołana w trakcie scalowania figury.
     *
     * @param e akcja wywołana przez użytkownika
     */
    public void mouseWheelMoved(MouseWheelEvent e) {

      doScale(e);
    }

    /**
     * Metoda aktualizująca rozmiar figury.
     *
     * @param e akcja wywołana przez użytkownika
     */
    private void doScale(MouseWheelEvent e) {
      if (modifying == "Resizing") {
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i < shapes.size(); i++) {
          Shape s = shapes.get(i);
          if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            if (s.contains(x, y) && s instanceof Rectshape) {
              Rectshape shr = (Rectshape) s;
              float amount = e.getWheelRotation() * 5f;
              shr.addWidth(amount);
              shr.addHeight(amount);
              repaint();
            } else if (s.contains(x, y) && s instanceof Ellipshape) {
              Ellipshape she = (Ellipshape) s;
              float amount = e.getWheelRotation() * 5f;
              she.addWidth(amount);
              she.addHeight(amount);
              repaint();
            } else if (s.contains(x, y)) {
              AffineTransform at = new AffineTransform();
              double amount = 1.0 + e.getWheelRotation() / 20.0;
              double px = s.getBounds().getX() + s.getBounds().getWidth() / 2;
              double py = s.getBounds().getY() + s.getBounds().getHeight() / 2;
              at.scale(amount, amount);
              Shape tmpshape = at.createTransformedShape(s);
              at = new AffineTransform();
              at.translate(
                      px - tmpshape.getBounds().getX() - tmpshape.getBounds().getWidth() / 2,
                      py - tmpshape.getBounds().getY() - tmpshape.getBounds().getHeight() / 2
              );
              Shape tmpsh = at.createTransformedShape(tmpshape);
              shapes.set(i, tmpsh);
              repaint();
            }
          }
        }
      }
    }
  }
}
