package jetbrains;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 * Pomcnicza klasa odpowiadająca a zamykanie okna głównego.
 */

class ZadanieWindowAdapter extends WindowAdapter {
  public void windowClosing(WindowEvent e) {
    System.exit(0);
  }
}

/**
 * Główna klasa. Odpowidająca za wygląd okna, rozmieszczenie komponentów oraz wygląd menu.
 */
public class Zadanie extends JFrame implements ActionListener {
  private MenuBar myMenu;
  private Menu mainMenu;
  private Menu shape;
  private Menu modify;
  private MenuItem circle;
  private MenuItem polygon;
  private MenuItem rectangle;
  private MenuItem exit;
  private MenuItem resizing;
  private MenuItem moving;
  private MenuItem end;
  private MenuItem info;
  private Dialog Info;
  private JFrame f;
  private ArrayList<Shape> shapes = new ArrayList<>();
  private ArrayList<Color> colors = new ArrayList<>();

  /**
   * Główna metoda main.
   * @param args - args
   */
  public static void main(String[] args) {
    JFrame f = new Zadanie();
    f.setBounds(100, 100, 700, 400);
    f.setVisible(true);
    f.setResizable(false);
  }

  /**
   * Konstruktor klasy.
   */
  public Zadanie() {
    setLayout(new BorderLayout());
    InformationPanel information = new InformationPanel(f);
    DrawPanel paint = new DrawPanel(f, information, shapes, colors);
    Color mycolor = new Color(204, 204, 204);
    information.setBackground(mycolor);
    add(BorderLayout.CENTER, paint);
    add(BorderLayout.SOUTH, information);


    myMenu = new MenuBar();
    mainMenu = new Menu("Menu");
    shape = new Menu("Shape");
    modify = new Menu("Modify");

    modify.addActionListener(new DrawPanel(f, information, shapes, colors));
    circle = new MenuItem("Circle");
    circle.addActionListener(new DrawPanel(f, information, shapes, colors));
    rectangle = new MenuItem("Rectangle");
    rectangle.addActionListener(new DrawPanel(f, information, shapes, colors));
    polygon = new MenuItem("Polygon");
    polygon.addActionListener(new DrawPanel(f, information, shapes, colors));
    exit = new MenuItem("Exit");
    exit.addActionListener(this);
    info = new MenuItem("Info");
    info.addActionListener(this);
    end = new MenuItem("End");
    end.addActionListener(new DrawPanel(f, information, shapes, colors));
    resizing = new MenuItem("Resizing");
    resizing.addActionListener(new DrawPanel(f, information, shapes, colors));
    moving = new MenuItem("Moving");
    moving.addActionListener(new DrawPanel(f, information, shapes, colors));
    myMenu.add(mainMenu);
    myMenu.add(shape);
    myMenu.add(modify);
    mainMenu.add(info);
    mainMenu.add(exit);
    shape.add(circle);
    shape.add(rectangle);
    shape.add(polygon);
    shape.add(end);
    modify.add(resizing);
    modify.add(moving);
    setMenuBar(myMenu);
    addWindowListener(new ZadanieWindowAdapter());
  }

  /**
   * Metoda odpowaidajaca za amykanie aplikacji.
   * @param e - akcja użytkownika
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Exit")) {
      System.exit(0);
    } else if (e.getActionCommand().equals("Info")) {
      Info = new InfoDialog(f, "Information", true);
    }
  }
}
