package jp.portfolio.clickchallenge;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("60秒クリックチャレンジ");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 700);
            frame.setLocationRelativeTo(null);

            GamePanel gamePanel = new GamePanel();
            frame.setContentPane(gamePanel);
            frame.setVisible(true);
        });
    }
}


