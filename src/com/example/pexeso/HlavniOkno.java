package com.example.pexeso;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HlavniOkno extends JFrame {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    JMenuBar menuBar1;
    JMenu menu1;
    JMenuItem mItemNovaHra;
    JMenuItem mItemKonec;
    JLabel labAnimal;
    JLabel labFruit;
    JLabel labInstrukce;
    JButton btnReset;
    JLabel labVecernicek;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JPanel contentPane;
    Hra novaHra;

    public HlavniOkno() {
        initComponents();
    }

    private void priKliknutiNaObrazekVyberu(MouseEvent e) {
        JLabel tlacitko = (JLabel) e.getSource();
        if (tlacitko.equals(labFruit)) {
            novaHra = new Hra(contentPane, "fruits");
        }
        if (tlacitko.equals(labAnimal)) {
            novaHra = new Hra(contentPane, "animals");
        }
        if (tlacitko.equals(labVecernicek)) {
            novaHra = new Hra(contentPane, "vecernicek");
        }
        novaHra.nastavHru();
        contentPane.remove(labAnimal);
        contentPane.remove(labFruit);
        contentPane.remove(labVecernicek);
        contentPane.remove(labInstrukce);
        mItemNovaHra.setEnabled(true);
        btnReset.setEnabled(true);
        contentPane.repaint();
    }

    private void priStiskuBtnNovaHra(ActionEvent e) {
        novaHra.znicHru();
        contentPane.add(labAnimal);
        contentPane.add(labFruit);
        contentPane.add(labVecernicek);
        contentPane.add(labInstrukce);
        mItemNovaHra.setEnabled(false);
        btnReset.setEnabled(false);
        contentPane.repaint();
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        mItemNovaHra = new JMenuItem();
        mItemKonec = new JMenuItem();
        labAnimal = new JLabel();
        labFruit = new JLabel();
        labInstrukce = new JLabel();
        btnReset = new JButton();
        labVecernicek = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pexeso");
        setBackground(new Color(204, 204, 204));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
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

        //---- labAnimal ----
        labAnimal.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/animals/0.jpg")));
        labAnimal.setHorizontalAlignment(SwingConstants.LEFT);
        labAnimal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                priKliknutiNaObrazekVyberu(e);
            }
        });
        contentPane.add(labAnimal);
        labAnimal.setBounds(new Rectangle(new Point(65, 140), labAnimal.getPreferredSize()));

        //---- labFruit ----
        labFruit.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/fruits/0.jpg")));
        labFruit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                priKliknutiNaObrazekVyberu(e);
            }
        });
        contentPane.add(labFruit);
        labFruit.setBounds(new Rectangle(new Point(155, 140), labFruit.getPreferredSize()));

        //---- labInstrukce ----
        labInstrukce.setText("Zvol si motiv pexesa");
        labInstrukce.setHorizontalAlignment(SwingConstants.CENTER);
        labInstrukce.setFont(labInstrukce.getFont().deriveFont(labInstrukce.getFont().getSize() + 7f));
        contentPane.add(labInstrukce);
        labInstrukce.setBounds(60, 80, 240, 50);

        //---- btnReset ----
        btnReset.setText("Reset");
        btnReset.setEnabled(false);
        btnReset.addActionListener(e -> priStiskuBtnReset(e));
        contentPane.add(btnReset);
        btnReset.setBounds(5, 5, 55, 35);

        //---- labVecernicek ----
        labVecernicek.setIcon(new ImageIcon(getClass().getResource("/com/example/pexeso/img/vecernicek/0.jpg")));
        labVecernicek.setHorizontalAlignment(SwingConstants.LEFT);
        labVecernicek.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                priKliknutiNaObrazekVyberu(e);
            }
        });
        contentPane.add(labVecernicek);
        labVecernicek.setBounds(245, 140, 70, 70);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(385, 400);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
}
