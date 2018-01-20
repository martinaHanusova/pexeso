package com.example.pexeso;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;
import net.miginfocom.swing.*;

public class Hra {

    private Timer obracecKarticek;
    private Timer zesedovacKarticek;
    private JPanel contentPane;
    private int pocetKarticekNaRadku;
    private int pocetObrazku;
    private List<Karticka> otocene = new ArrayList<>();
    private int body;
    private String motiv;
    private JLabel labelZprava = new JLabel();
    private JLabel labelMericCasu = new JLabel();
    private List<Karticka> karticky;
    private MericCasu mericCasu;
    private boolean majiSeOtocitKarticky = false;

    /**
     * Konstruktor pro tridu Hra
     *
     * @param contentPane  kontejner do ktereho bude hra umistena
     * @param pocetObrazku pro pexeso, ktere nebude obsahovat 8 obrazku
     * @param motiv        pro volbu tematu pexesa
     */

    Hra(JPanel contentPane, int pocetObrazku, String motiv) {

        this.contentPane = contentPane;
        this.pocetKarticekNaRadku = (int) (Math.sqrt(pocetObrazku * 2));
        this.pocetObrazku = pocetObrazku;
        this.motiv = motiv;
        karticky = new ArrayList<>();
    }

    /**
     * Vytvori hru
     */

    public void nastavHru() {
        int margin = 6;
        for (int i = 0; i < pocetObrazku * 2; i++) {
            Karticka karticka = new Karticka("/com/example/pexeso/img/" + motiv + "/" + i % pocetObrazku + ".jpg", motiv + "_background"); // vytvori novou karticku
            karticka.addMouseListener(new MouseAdapter() {   // prida karticce listener pro sledovani zda na ni bylo kliknuto
                @Override
                public void mouseClicked(MouseEvent e) {
                    priKliknutinaKarticku(e, karticka);
                }
            });
            karticky.add(karticka);
        }

        // vytvori tabulku
        String sloupce = "";
        for (int i = 0; i < pocetKarticekNaRadku; i++) {
            sloupce += "[fill]";
        }
        String radky = "";
        for (int i = 0; i <= pocetKarticekNaRadku; i++) {
            radky += "[fill]";
        }
        contentPane.setLayout(new MigLayout("insets 0,hidemode 3", margin + sloupce + margin, margin + radky + margin));


        Collections.shuffle(karticky);  // zamicha karticky
        // rozlozi karticky do tabulky
        for (int i = 0; i < pocetObrazku * 2; i++) {
            Karticka karticka = karticky.get(i);
            this.contentPane.add(karticka, "cell " + i % pocetKarticekNaRadku + " " + (i / pocetKarticekNaRadku + 1));     // urceni sloupce a radku
        }

        // Vytvoreni a nastaveni labelu pro zpravu o konci hry
        labelZprava.setText("KONEC HRY");
        this.contentPane.add(labelZprava, "cell 0 0 " + (pocetKarticekNaRadku - 1) + " 0,alignx center,growx 0");
        labelZprava.setBounds(0, 5, 400, labelZprava.getPreferredSize().height);
        labelZprava.setHorizontalAlignment(SwingConstants.CENTER);
        labelZprava.setFont(new Font(".SF NS Text", Font.PLAIN, 18));
        labelZprava.setVisible(false);

        //Vytvoreni a nastaveni labelu pro meric casu
        labelMericCasu.setText("00:00");
        this.contentPane.add(labelMericCasu, "cell" + (pocetKarticekNaRadku - 1) + " 0,alignx right,growx 0");
        labelMericCasu.setBounds(320, 10, 400, labelMericCasu.getPreferredSize().height);
        labelMericCasu.setFont(new Font(".SF NS Text", Font.PLAIN, 18));
        mericCasu = new MericCasu(labelMericCasu);
        mericCasu.spustitMeric();
    }

    /**
     * Metoda pro otoceni nespravne dvojice zpet na rub
     */
    private void otocitDvojici() {
        otocene.get(0).setRubKarticky();
        otocene.get(1).setRubKarticky();
        otocene.clear();
    }

    /**
     * Metoda pro otoceni karticky pri kliknuti
     * @param e
     * @param karticka na kterou bylo kliknuto
     */

    private void priKliknutinaKarticku(MouseEvent e, Karticka karticka) {
        if (majiSeOtocitKarticky) {
            otocitDvojici();
            majiSeOtocitKarticky = false;
            obracecKarticek.stop();
        }
        if (!karticka.isEnabled()) {
            return;
        }
        if (!karticka.isJeKartickaOtocena() && otocene.size() < 2) {   // pokud nejsou otocene dve karticky
            karticka.otocitKarticku(e);
            otocene.add(karticka);
        } else {
            return;
        }
        if (otocene.size() == 2) {                                                         // pokud jsou otocene prave dve karticky
            if (otocene.get(0).getSrcObrazku().equals(otocene.get(1).getSrcObrazku())) {   // pokud jsou otocene karticky totozne
                zesedovacKarticek = new Timer(0, it -> {
                    otocene.get(0).setEnabled(false);
                    otocene.get(1).setEnabled(false);
                    body += 1;
                    if (body == pocetObrazku) {
                        labelZprava.setVisible(true);
                        mericCasu.zastavitMeric();

                    }
                    otocene.clear();
                });
                zesedovacKarticek.setInitialDelay(500);
                zesedovacKarticek.setRepeats(false);
                zesedovacKarticek.start();
            } else {                                                                        // pokud otocene karticky nejsou totozne
                majiSeOtocitKarticky = true;
                obracecKarticek = new Timer(0, it -> {                                      // Timer pro automaticke otaceni karticek na rubovou stranu
                    if (majiSeOtocitKarticky) {
                        otocitDvojici();
                        majiSeOtocitKarticky = false;
                    }
                }
                );
                obracecKarticek.setInitialDelay(2000);
                obracecKarticek.setRepeats(false);
                obracecKarticek.start();

            }
        }
    }

    /**
     * Metoda pro zruseni nastavene hry
     */

    public void znicHru() {
        if (obracecKarticek != null) {
            obracecKarticek.stop();
        }
        if (zesedovacKarticek != null) {
            zesedovacKarticek.stop();
        }
        if (mericCasu != null) {
            mericCasu.zastavitMeric();
        }
        for (Karticka k : karticky) {
            k.setEnabled(true);
            k.setRubKarticky();
            contentPane.remove(k);
        }
        contentPane.remove(labelMericCasu);
        contentPane.remove(labelZprava);
        majiSeOtocitKarticky = false;
        mericCasu = null;
        body = 0;
        otocene.clear();
        karticky.clear();
    }
}
