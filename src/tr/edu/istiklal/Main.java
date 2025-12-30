package tr.edu.istiklal;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator <3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //burda tasarım sınıfını ekliyorum.
        frame.setContentPane(new HesapMakinesi());

        frame.setSize(380, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}