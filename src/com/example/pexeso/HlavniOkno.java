package com.example.pexeso;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JMenuBar menuBar1;
    JMenu menu1;
    JMenuItem mItemNovaHra;
    JMenuItem mItemKonec;
    JLabel labZelenina;
    JLabel labOvoce;
    JLabel labMotiv;
    JButton btnReset;
    JLabel labVecernicek;
    JRadioButton rBtnZelenina;
    JRadioButton rBtnOvoce;
    JRadioButton rBtnVecernicek;
    JLabel labPocetDvojic;
    JLabel lab2;
    JLabel lab8;
    JLabel lab18;
    JRadioButton rBtn2;
    JRadioButton rBtn8;
    JRadioButton rBtn18;
    JButton btnVybrat;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    Hra novaHra;

    public HlavniOkno() {
        initComponents();
        
    }

    private void priStiskuBtnNovaHra(ActionEvent e) {
        novaHra.znicHru();
        contentPane.add(labZelenina);
        contentPane.add(labOvoce);
        contentPane.add(labVecernicek);
        contentPane.add(labMotiv);
        mItemNovaHra.setEnabled(false);
        btnReset.setEnabled(false);
        contentPane.repaint();
        pack();
    }

    private void priStiskuBtnKonec(ActionEvent e) {
        if (novaHra != null) {
            novaHra.znicHru();
        }
        this.dispose();
    }

    private void thisWindowClosed(WindowEvent e) {
        if (novaHra != null) {
            novaHra.znicHru();
        }
    }

    private void priStiskuBtnReset(ActionEvent e) {
        novaHra.znicHru();
        novaHra.nastavHru();
        contentPane.repaint();
        
    }

    private void priKliknutibtnVybrat(ActionEvent e) {
    String motiv = rBtnZelenina.isSelected() ? "zelenina" : rBtnOvoce.isSelected() ? "ovoce" : "vecernicek";
    int pocetDvojic = rBtn2.isSelected() ? 2 : rBtn8.isSelected() ? 8 : 18;
        novaHra = new Hra(contentPane, pocetDvojic, motiv);
        novaHra.nastavHru();
        contentPane.remove(rBtnZelenina);
        contentPane.remove(rBtnOvoce);
        contentPane.remove(rBtnVecernicek);
        contentPane.remove(rBtn2);
        contentPane.remove(rBtn8);
        contentPane.remove(rBtn18);
        contentPane.remove(btnVybrat);
        contentPane.remove(labZelenina);
        contentPane.remove(labOvoce);
        contentPane.remove(lab2);
        contentPane.remove(lab8);
        contentPane.remove(lab18);
        contentPane.remove(labVecernicek);
        contentPane.remove(labPocetDvojic);
        contentPane.remove(labMotiv);
        mItemNovaHra.setEnabled(true);
        btnReset.setEnabled(true);
        contentPane.repaint();
        pack();
    }

    private void priKliknuti(MouseEvent e) {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        mItemNovaHra = new JMenuItem();
        mItemKonec = new JMenuItem();
        labZelenina = new JLabel();
        labOvoce = new JLabel();
        labMotiv = new JLabel();
        btnReset = new JButton();
        labVecernicek = new JLabel();
        rBtnZelenina = new JRadioButton();
        rBtnOvoce = new JRadioButton();
        rBtnVecernicek = new JRadioButton();
        labPocetDvojic = new JLabel();
        lab2 = new JLabel();
        lab8 = new JLabel();
        lab18 = new JLabel();
        rBtn2 = new JRadioButton();
        rBtn8 = new JRadioButton();
        rBtn18 = new JRadioButton();
        btnVybrat = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pexeso");
        setBackground(Color.white);
        setIconImage(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 0,hidemode 3,gap 5 5",
            // columns
            "[grow,sizegroup 2,fill]" +
            "[sizegroup 1,fill]" +
            "[sizegroup 1,fill]" +
            "[sizegroup 1,fill]" +
            "[grow,sizegroup 2,fill]",
            // rows
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));
        this.contentPane = (JPanel) this.getContentPane();
        this.contentPane.setBackground(this.getBackground());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("Menu");

                //---- mItemNovaHra ----
                mItemNovaHra.setText("Nov\u00e1 hra");
                mItemNovaHra.setEnabled(false);
                mItemNovaHra.addActionListener(e -> priStiskuBtnNovaHra(e));
                menu1.add(mItemNovaHra);

                //---- mItemKonec ----
                mItemKonec.setText("Konec");
                mItemKonec.addActionListener(e -> priStiskuBtnKonec(e));
                menu1.add(mItemKonec);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- labZelenina ----
        labZelenina.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/zelenina/0.jpg")));
        labZelenina.setHorizontalAlignment(SwingConstants.LEFT);
        contentPane.add(labZelenina, "cell 1 2");

        //---- labOvoce ----
        labOvoce.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/ovoce/0.jpg")));
        contentPane.add(labOvoce, "cell 2 2");

        //---- labMotiv ----
        labMotiv.setText("Motiv pexesa");
        labMotiv.setHorizontalAlignment(SwingConstants.CENTER);
        labMotiv.setFont(labMotiv.getFont().deriveFont(labMotiv.getFont().getSize() + 7f));
        contentPane.add(labMotiv, "cell 1 1 3 1,alignx center,growx 0");

        //---- btnReset ----
        btnReset.setText("Reset");
        btnReset.setEnabled(false);
        btnReset.setVisible(false);
        btnReset.addActionListener(e -> priStiskuBtnReset(e));
        contentPane.add(btnReset, "cell 0 0");

        //---- labVecernicek ----
        labVecernicek.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/vecernicek/0.jpg")));
        labVecernicek.setHorizontalAlignment(SwingConstants.LEFT);
        contentPane.add(labVecernicek, "cell 3 2");

        //---- rBtnZelenina ----
        rBtnZelenina.setSelected(true);
        contentPane.add(rBtnZelenina, "cell 1 3,alignx center,growx 0");
        contentPane.add(rBtnOvoce, "cell 2 3,alignx center,growx 0");
        contentPane.add(rBtnVecernicek, "cell 3 3,alignx center,growx 0");

        //---- labPocetDvojic ----
        labPocetDvojic.setText("Po\u010det dvojic");
        labPocetDvojic.setHorizontalAlignment(SwingConstants.CENTER);
        labPocetDvojic.setFont(labPocetDvojic.getFont().deriveFont(labPocetDvojic.getFont().getSize() + 7f));
        contentPane.add(labPocetDvojic, "cell 1 4 3 1");

        //---- lab2 ----
        lab2.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/2.jpg")));
        lab2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                priKliknuti(e);
            }
        });
        contentPane.add(lab2, "cell 1 5");

        //---- lab8 ----
        lab8.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/8.jpg")));
        contentPane.add(lab8, "cell 2 5");

        //---- lab18 ----
        lab18.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/18.jpg")));
        contentPane.add(lab18, "cell 3 5");

        //---- rBtn2 ----
        rBtn2.setSelected(true);
        contentPane.add(rBtn2, "cell 1 6,alignx center,growx 0");
        contentPane.add(rBtn8, "cell 2 6,alignx center,growx 0");
        contentPane.add(rBtn18, "cell 3 6,alignx center,growx 0");

        //---- btnVybrat ----
        btnVybrat.setText("Vybrat");
        btnVybrat.addActionListener(e -> priKliknutibtnVybrat(e));
        contentPane.add(btnVybrat, "cell 2 8");
        pack();
        setLocationRelativeTo(null);

        //---- btnGrMotiv ----
        ButtonGroup btnGrMotiv = new ButtonGroup();
        btnGrMotiv.add(rBtnZelenina);
        btnGrMotiv.add(rBtnOvoce);
        btnGrMotiv.add(rBtnVecernicek);

        //---- btnGrPocet ----
        ButtonGroup btnGrPocet = new ButtonGroup();
        btnGrPocet.add(rBtn2);
        btnGrPocet.add(rBtn8);
        btnGrPocet.add(rBtn18);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
