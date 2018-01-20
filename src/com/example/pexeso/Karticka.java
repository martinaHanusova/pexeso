package com.example.pexeso;

import java.awt.event.*;
import javax.swing.*;

/**
 * Trida pro karticku pexesa
 */

public class Karticka extends JLabel {
    private String srcObrazku;
    private ImageIcon obrazek;
    private ImageIcon rubKarticky;
    private boolean jeKartickaOtocena = false;

    /**
     * Konstruktor pro karticku
     * @param srcObrazku cesta k obrazku pro karticku
     */
    Karticka(String srcObrazku, String background) {
            this.srcObrazku = srcObrazku;
            obrazek = new ImageIcon(getClass().getResource(srcObrazku));
            rubKarticky = new ImageIcon(getClass().getResource("/com/example/pexeso/img/" + background + ".jpg"));
            this.setIcon(rubKarticky);
            this.setSize(this.getPreferredSize());
            jeKartickaOtocena = false;
        }

    /**
     * Otoci karticku a nastavi vlastnost zda je otocena
      * @param e
     */

    public void otocitKarticku(MouseEvent e) {
        JLabel otocenaKarticka = (JLabel)e.getSource();
        if (!jeKartickaOtocena) {
            otocenaKarticka.setIcon(obrazek);
            jeKartickaOtocena = true;
        } else {
            otocenaKarticka.setIcon(rubKarticky);
            jeKartickaOtocena = false;
        }
    }

    /**
     * Metoda pro zjisteni zda je karticka otocena licem nahoru
     * @return zda je karticka otocena
     */

    public boolean isJeKartickaOtocena() {
        return jeKartickaOtocena;
    }

    /**
     * Metoda nastavi karticce rubovou stranu
     */

    public void setRubKarticky() {
        jeKartickaOtocena = false;
        this.setIcon(rubKarticky);
    }

    /**
     * Metoda pro zjisteni cesty k obrazku na karticce
     * @return vraci cestu k obrazku
     */

    public String getSrcObrazku() {
        return srcObrazku;
    }


}
