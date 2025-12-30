package tr.edu.istiklal;

import javax.swing.*; // swing kütüphanesindeki tüm sınıfları projeye ekler.
// JFrame (yani pencere), JButton (buton) JTextField (yazı alanı) ve JPanel (panel) çalışmasını sağlar.
import java.awt.*; // tasarım ve düzenlemek için --> layout managers
import java.awt.event.ActionListener; // butonlara basıldığında bir şeyler olmasını sağlar.

public class HesapMakinesi extends JPanel {
    private JTextField txtEkran = new JTextField("0");
    private JLabel lblGecmis = new JLabel(" ");
    private double ilkSayi = 0;
    private String operator = "";
    private boolean islemSecildi = false;
    private HesapIslemleri hesaplayici = new HesapIslemleri();

    public HesapMakinesi() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 230, 235)); //arka planı pembe yapmak istedim.
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel ustPanel = new JPanel(new BorderLayout());
        ustPanel.setOpaque(false);

        lblGecmis.setHorizontalAlignment(SwingConstants.RIGHT);
        lblGecmis.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblGecmis.setForeground(new Color(150, 100, 110));

        txtEkran.setEditable(false);
        txtEkran.setHorizontalAlignment(JTextField.RIGHT);
        txtEkran.setFont(new Font("Segoe UI Semibold", Font.BOLD, 36));
        txtEkran.setBorder(null);
        txtEkran.setOpaque(false);
        txtEkran.setForeground(new Color(80, 50, 60));

        ustPanel.add(lblGecmis, BorderLayout.NORTH);
        ustPanel.add(txtEkran, BorderLayout.CENTER);
        add(ustPanel, BorderLayout.NORTH);

        JPanel butonPaneli = new JPanel(new GridLayout(5, 4, 8, 8));
        butonPaneli.setOpaque(false);


        String[] butonlar = {
                "C", "CE", "%", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "00", "="
        };

        for (String m : butonlar) {
            JButton btn = new JButton(m);
            stilVer(btn);
            btn.addActionListener(tıkla);
            butonPaneli.add(btn);
        }
        add(butonPaneli, BorderLayout.CENTER);
    }

    private void stilVer(JButton b) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 18));
        b.setBackground(Color.WHITE);
        b.setForeground(new Color(80, 50, 60));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(new Color(245, 215, 220)));

        if (b.getText().equals("=")) { // = kısmını mavi yapar.
            b.setBackground(new Color(225, 240, 255));
            b.setForeground(new Color(100, 150, 200));
        }

        else if (b.getText().matches("[C|CE|x|/|\\-|\\+|%]")) {
            b.setForeground(new Color(255, 150, 170));
        }
    }

    private ActionListener tıkla = e -> {
        String s = e.getActionCommand();

        if (s.matches("[0-9]") || s.equals("00") || s.equals(".")) {
            if (islemSecildi || txtEkran.getText().equals("0") || txtEkran.getText().contains("hata")) {
                txtEkran.setText("");
                islemSecildi = false;
            }
            txtEkran.setText(txtEkran.getText() + s);
        }
        else if (s.matches("[+\\-x/]")) {
            ilkSayi = Double.parseDouble(txtEkran.getText());
            operator = s;
            lblGecmis.setText(ilkSayi + " " + operator);
            islemSecildi = true;
        }
        else if (s.equals("=")) {
            if (operator.isEmpty()) return;
            double ikinci = Double.parseDouble(txtEkran.getText());
            double sonuc = hesaplayici.islemYap(ilkSayi, ikinci, operator);

            if (Double.isNaN(sonuc)) {
                txtEkran.setText("hata verdi :( ");
                lblGecmis.setText("0/0");
            } else {
                txtEkran.setText(String.valueOf(sonuc));
                lblGecmis.setText(lblGecmis.getText() + " " + ikinci + " =");
            }
            operator = "";
            islemSecildi = true;
        }
        else if (s.equals("C") || s.equals("CE")) {
            txtEkran.setText("0");
            lblGecmis.setText(" ");
            ilkSayi = 0;
            operator = "";
        }
    };
}