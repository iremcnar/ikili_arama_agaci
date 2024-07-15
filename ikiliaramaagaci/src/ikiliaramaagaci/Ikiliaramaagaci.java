/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ikiliaramaagaci;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ikiliaramaagaci extends JFrame {
    //NODE SINIFI
    static class Node {
        int value;  
        Node sol, sag;
        JLabel label;

        Node(int deger) {
            value = deger;
            sol = sag = null;
            label = new JLabel(String.valueOf(deger));
            label.setOpaque(true);
            label.setBackground(Color.LIGHT_GRAY);
        }
    }
    Node kok; 
   //FİNALP SINIFI
    public Ikiliaramaagaci() {
        kok = null;
        setTitle("Proje Ödevi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        //AGAC PANELİ
        JPanel agacPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                cizim(g, kok, getWidth() / 2, 30, getWidth() / 4);
            }
        };
        agacPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(new JScrollPane(agacPanel), BorderLayout.CENTER);
        //SAG PANEL
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 250, 10));
        add(rightPanel, BorderLayout.EAST);
        //EKLEME PANELİ
        JPanel eklePanel = new JPanel(new FlowLayout());
        JTextField sayiEkleField = new JTextField(10);
        JButton ekleButton = new JButton("Ekleme");
        eklePanel.add(ekleButton);
        eklePanel.add(sayiEkleField);
        rightPanel.add(eklePanel);
        //ARAMAPANELİ
        JPanel araPanel = new JPanel(new FlowLayout());
        JTextField sayiAraField = new JTextField(10);
        JButton araButton = new JButton("Arama");
        araPanel.add(araButton);
        araPanel.add(sayiAraField);
        rightPanel.add(araPanel);
        //MIN VE MAX PANELLER
        //MİN
        JPanel minPanel = new JPanel(new FlowLayout());
        JTextField minSonucField = new JTextField(10);
        minSonucField.setEditable(false); //METİN ALANI DUZENLENEMEZ
        JButton minButton = new JButton("Min Bul"); 
        minPanel.add(minButton);
        minPanel.add(minSonucField);
        rightPanel.add(minPanel);
        //MAX
        JPanel maxPanel = new JPanel(new FlowLayout());
        JTextField maxSonucField = new JTextField(10);
        maxSonucField.setEditable(false); //METİN ALANI DUZENLENEMEZ
        JButton maxButton = new JButton("Max Bul");
        maxPanel.add(maxButton);
        maxPanel.add(maxSonucField);
        rightPanel.add(maxPanel);
        //ALT PANEL
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel dolasmaPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JTextField dolasmaSonucField = new JTextField("Aralarında boşluk ile dolaşma sonucunu yazdırın", 30);
        dolasmaSonucField.setEditable(false); //DUZENLENEMEZ
        JButton preOrderButton = new JButton("PreOrder");
        JButton inOrderButton = new JButton("InOrder");
        JButton postOrderButton = new JButton("PostOrder");
        dolasmaPanel.add(preOrderButton); 
        dolasmaPanel.add(inOrderButton);
        dolasmaPanel.add(postOrderButton);
        bottomPanel.add(dolasmaPanel);
        JPanel bosPanel = new JPanel();
        bottomPanel.add(bosPanel); 
        bottomPanel.add(dolasmaSonucField);
        add(bottomPanel, BorderLayout.SOUTH);
        //EKLEME BUTONU
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sayi = Integer.parseInt(sayiEkleField.getText());
                    ekle(sayi);
                    agacPanel.repaint();
                    sayiEkleField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Ikiliaramaagaci.this, "Geçersiz sayı!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //ARAMA BUTONU
        araButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int sayi = Integer.parseInt(sayiAraField.getText());
                    boolean bulundu = agacAra(kok, sayi);
                    agacPanel.repaint();
                    if (bulundu) {
                        JOptionPane.showMessageDialog(Ikiliaramaagaci.this, sayi + " bulundu.");
                    } else {
                        JOptionPane.showMessageDialog(Ikiliaramaagaci.this, sayi + " bulunamadı.");
                    }
                    sayiAraField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Ikiliaramaagaci.this, "Geçersiz sayı", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // MIN MAX BUTONLARI
        minButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Node minNode = MinDeger(kok);
                if (minNode != null) {
                    minSonucField.setText(String.valueOf(minNode.value));
                }
            }
        });
        maxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Node maxNode = MaxDeger(kok);
                if (maxNode != null) {
                    maxSonucField.setText(String.valueOf(maxNode.value));
                }
            }
        });
        //preorder-postorder-inorder butonları
        preOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sonuc = preOrder(kok);
                dolasmaSonucField.setText(sonuc);
            }
        });
        inOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sonuc = inOrder(kok);
                dolasmaSonucField.setText(sonuc);
            }
        });
        postOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sonuc = postOrder(kok);
                dolasmaSonucField.setText(sonuc);
            }
        });
    }
    //EKLEME İŞLEMİ
    void ekle(int veri) {
        kok = ekleKok(kok, veri);
    }

    Node ekleKok(Node kok, int veri) {
        if (kok == null) {
            kok = new Node(veri);
            return kok;
        }

        if (veri < kok.value)
            kok.sol = ekleKok(kok.sol, veri);
        else if (veri > kok.value)
            kok.sag = ekleKok(kok.sag, veri);

        return kok;
    }   
    //ARAMA İŞLEMİ
    boolean agacAra(Node kok, int deger) {
        while (kok != null) {
            if (deger == kok.value) {
                return true;
            } else if (deger < kok.value) {
                kok = kok.sol;
            } else {
                kok = kok.sag;
            }
        }
        return false;
    }
    //MİN MAX İŞLEMİ
    Node MinDeger(Node kok) {
        while (kok.sol != null) {
            kok = kok.sol;
        }
        return kok;
    }

    Node MaxDeger(Node kok) {
        while (kok.sag != null) {
            kok = kok.sag;
        }
        return kok;
    }
    //PREORDER-POSTORDER-İNORDER İŞLEMLERİ
    String preOrder(Node kok) {
        if (kok == null)
            return "";
        return kok.value + " " 
            + preOrder(kok.sol) 
            + preOrder(kok.sag);
    }

    String inOrder(Node kok) {
        if (kok == null)
            return "";
        return inOrder(kok.sol) 
             + kok.value + " " 
             + inOrder(kok.sag);
    }

    String postOrder(Node kok) {
        if (kok == null)
            return "";
        return postOrder(kok.sol) 
            + postOrder(kok.sag) 
            + kok.value + " ";
    }
    //ÇİZİM
    void cizim(Graphics g, Node kok, int x, int y, int yatay) {
        if (kok != null) {
            g.drawRect(x, y, 50, 30);
            g.drawString(Integer.toString(kok.value), x + 10, y + 20);

            if (kok.sol != null) {
                int xsolDugum = x - yatay;
                int ysolDugum = y + 60;
                cizim(g, kok.sol, xsolDugum, ysolDugum, yatay / 2);
            }
            if (kok.sag != null) {
                int xsagDugum = x + yatay;
                int ysagDugum = y + 60;
                cizim(g, kok.sag, xsagDugum, ysagDugum, yatay / 2);
            }
        }
    }
    //MAİN KISMIMIZ
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ikiliaramaagaci frame = new Ikiliaramaagaci();
            frame.setVisible(true);
        });
    }
}

