package com.example.pexeso;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * Meri cas a zapisuje ho do labelu
 */

public class MericCasu {
    private Calendar cal = Calendar.getInstance();
    private long vychoziCas = cal.getTimeInMillis();
    private Timer casovac;

    /**
     * Konstruktor pro MericCasu
     * @param labelCasovace Jlabel, ktery bude zobrazovat aktualni cas merice
     */
    MericCasu(JLabel labelCasovace) {
        casovac = new Timer(500, e -> {
                Calendar cal = Calendar.getInstance();
                long novyCas = cal.getTimeInMillis();
                int pocetSekund = (int)(novyCas - vychoziCas)/1000;
                labelCasovace.setText(String.format("%02d:%02d\n", pocetSekund/60, pocetSekund%60));
            }
        );
    }

    /**
     * Spusti meric casu
     */
    public void spustitMeric() {
        casovac.start();
    }

    /**
     * Vypne meric casu
     */
    public void zastavitMeric() {
        casovac.stop();
    }
}
