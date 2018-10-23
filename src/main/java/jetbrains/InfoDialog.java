package jetbrains;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JTextArea;


/**
 * Klasa, która wyświetla okno dialogowe zawierające informacje o autorze oraz opis działania
 * programu.
 */

public class InfoDialog extends JDialog {
  Frame f;
  private Button close;
  private JTextArea information;
  private Dialog infoDialog;

  /**
   * Metoda, która wyświetla okno dialogowe wraz z informacjami.
   * @param owner - okno głowne
   * @param title - nazwa okna dialogowego
   * @param modal - blokowanie okna głownego
   */
  public InfoDialog(Frame owner, String title, boolean modal) {
    infoDialog = new Dialog(f, "Information", false);
    infoDialog.setSize(300, 200);
    infoDialog.setVisible(true);
    infoDialog.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.insets = new Insets(5, 5, 5, 5);

    information = new JTextArea();
    information.setEditable(false);
    information.setText(" Autor: Eliza Zawisza\n Program umożliwia rysowanie oraz modyfikowanie "
            + "figur geometrycznych takich jak: Koło (Circle), Prostokąt (Rectangle), "
            + "Wielokąt(Polygon). \n Przez modyfikacje rozumiemy odpowiednio: Rozszerzanie figury "
            + "(Resizing), Ruszanie figurą (Moving) oraz zmianę koloru figury.\n "
            + "Aby narysować figure należy wybrać kształt z menu. Wybrany przez użytkownika "
            + "kształt będzie rysowany \n dopóki użytkownik nie zmieni kształtu lub nie wybierze "
            + "jednej z opcji modyfikowania (Modify).\n Aby przesuwać figurę należy wybrać "
            + "odpowiednią funkcje z menu - Modify -> Moving \n Ruszanie figurą odbywa się"
            + " poprzez naciśnięcie lewego klawisza myszki i poruszanie kursorem po panelu.\n"
            + " Podczas przesuwania prostokąta i koła wypisywane zostają informacje o obecnym "
            + "położeniu i rozmiarze danej figury.\n Aby zmienić rozmiar figury należy wybrać "
            + "odpowiednić funkcjć z menu - Modify -> Resizing\n "
            + "Zmana rozmiaru figury odbywa sić poprzez scrollowanie w momencie, gdy kursor jest "
            + "wewnątrz figury\n Aby zmienić kolor danej figury należy kliknac na nią prawym "
            + "przyciskiem myszki pamietając, aby nie było aktywne rysowanie figury\n "
            + "Najlepiej upewnić sie wybierając End w menu wyboru kształtu (Shape).");
    c.gridx = 0;
    c.gridy = 0;
    infoDialog.add(information, c);

    close = new Button("Close");
    c.gridx = 0;
    c.gridy = 1;
    infoDialog.add(close, c);
    infoDialog.pack();
    close.addActionListener(new Exitbutton(infoDialog));
  }
}
