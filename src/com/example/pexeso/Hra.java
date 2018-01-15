package com.example.pexeso;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.Timer;

public class Hra {

    private Timer obracecKarticek;
    private JPanel contentPane;
    private int pocetKarticekNaRadku;
    private int pocetObrazku;
    private int margin;
    private List<Karticka> otocene = new ArrayList<>();
    private int body;
    private String motiv;
    private JLabel labelZprava = new JLabel();
    private JLabel labelMericCasu = new JLabel();
    private List<Karticka> karticky;
    private MericCasu mericCasu;

    /**
     * Konstruktor pro tridu Hra
     * @param contentPane kontejner do ktereho bude hra umistena
     * @param motiv pro volbu tematu pexesa
     */

    Hra(JPanel contentPane, String motiv) {
        this(contentPane, 4, 8, 10, motiv);
    }

    /**
     *
     * @param contentPane kontejner do ktereho bude hra umistena
     * @param pocetKarticekNaRadku kolik karticek bude na jednom radku
     * @param pocetObrazku pro pexeso, ktere nebude obsahovat 8 obrazku
     * @param margin urcuje jake bude odsazeni mezi kartickami
     * @param motiv pro volbu tematu pexesa
     */

    Hra(JPanel contentPane, int pocetKarticekNaRadku, int pocetObrazku, int margin, String motiv) {

        this.contentPane = contentPane;
        this.pocetKarticekNaRadku = pocetKarticekNaRadku;
        this.pocetObrazku = pocetObrazku;
        this.margin = margin;
        this.motiv = motiv;
        karticky = new ArrayList<>();
    }

    /**
     * Vytvori hru
     */

    public void nastavHru() {
        for (int i = 0; i < pocetObrazku * 2; i++) {
            Karticka karticka = new Karticka("/com/example/pexeso/img/" + motiv + "/" + i % pocetObrazku + ".jpg"); // vytvori novou karticku
            karticka.addMouseListener(new MouseAdapter() {   // prida karticce listener pro sledovani zda na ni bylo kliknuto
                @Override
                public void mouseClicked(MouseEvent e) {
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
                            otocene.get(0).setEnabled(false);
                            otocene.get(1).setEnabled(false);
                            body += 1;
                            otocene.clear();
                            if (body == 8) {
                                labelZprava.setVisible(true);
                                mericCasu.zastavitMeric();

                            }
                        } else {                                                                        // pokud otocene karticky nejsou totozne
                            obracecKarticek = new Timer(0, it -> {                                      // Timer pro automaticke otaceni karticek na rubovou stranu
                                    otocene.get(0).setRubKarticky();
                                    otocene.get(1).setRubKarticky();
                                    otocene.clear();
                                }
                            );
                            obracecKarticek.setInitialDelay(1000);
                            obracecKarticek.setRepeats(false);
                            obracecKarticek.start();

                        }
                    }
                }
            });
            karticky.add(karticka);
        }
                                                                                                            // zamicha karticky
        Collections.shuffle(karticky);
        for (int i = 0; i < pocetObrazku * 2; i++) {
            Karticka karticka = karticky.get(i);
            karticka.setLocation(30 + (karticka.getWidth() + this.margin) * (i % this.pocetKarticekNaRadku), 40 + (karticka.getHeight() + this.margin) * (i / this.pocetKarticekNaRadku));   // nastavi pozice jednotlivym kartickam
            this.contentPane.add(karticka);
        }

        // Vytvoreni a nastaveni labelu pro zpravu o konci hry
        labelZprava.setText("KONEC HRY");
        this.contentPane.add(labelZprava);
        labelZprava.setBounds(0, 5, 400, labelZprava.getPreferredSize().height);
        labelZprava.setHorizontalAlignment(SwingConstants.CENTER);
        labelZprava.setFont(new Font(".SF NS Text", Font.PLAIN, 18));
        labelZprava.setVisible(false);

        // Vytvoreni a nastaveni labelu pro meric casu
        labelMericCasu.setText("00:00");
        this.contentPane.add(labelMericCasu);
        labelMericCasu.setBounds(320, 10, 400, labelMericCasu.getPreferredSize().height);
        labelMericCasu.setFont(new Font(".SF NS Text", Font.PLAIN, 18));


        mericCasu = new MericCasu(labelMericCasu);
        mericCasu.spustitMeric();
    }

    /**
     * Metoda pro zruseni nastavene hry
     */

    public void znicHru() {
        if (obracecKarticek != null) {
            obracecKarticek.stop();
        }
        if (mericCasu != null) {
            mericCasu.zastavitMeric();
        }
        for (Karticka k: karticky) {
            k.setEnabled(true);
            k.setRubKarticky();
            contentPane.remove(k);
        }
        contentPane.remove(labelMericCasu);
        contentPane.remove(labelZprava);
        mericCasu = null;
        body = 0;
        otocene.clear();
        karticky.clear();
    }
}
