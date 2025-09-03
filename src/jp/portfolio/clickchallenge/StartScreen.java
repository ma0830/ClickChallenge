package jp.portfolio.clickchallenge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StartScreen extends JPanel {
    public StartScreen(GamePanel parent) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#e6f2fa"));

        JLabel title = new JLabel("60秒クリックチャレンジ", SwingConstants.CENTER);
        title.setFont(new Font("Meiryo", Font.BOLD, 36));
        title.setForeground(Color.decode("#007bbb"));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBackground(Color.decode("#e6f2fa"));

        JButton startButton = new JButton("ゲーム開始");
        styleModernButton(startButton);
        startButton.addActionListener(e -> parent.startGame());
        centerPanel.add(startButton);

        JButton rankingButton = new JButton("ランキングを見る");
        styleModernButton(rankingButton);
        rankingButton.addActionListener(e -> parent.showRankingOnly());
        centerPanel.add(rankingButton);

        // 音関連チェックボックスを中央揃えで縦並びにするパネル
        JPanel soundPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        soundPanel.setBackground(Color.decode("#e6f2fa"));

        JCheckBox bgmToggle = new JCheckBox("BGMを再生");
        bgmToggle.setSelected(true);
        bgmToggle.setFont(new Font("Meiryo", Font.PLAIN, 16));
        bgmToggle.setBackground(Color.decode("#e6f2fa"));
        bgmToggle.setHorizontalAlignment(SwingConstants.CENTER);
        bgmToggle.addActionListener(e -> {
            parent.getSoundManager().setBgmEnabled(bgmToggle.isSelected());
            if (bgmToggle.isSelected()) {
                parent.getSoundManager().playBgm(); // 再生
            } else {
                parent.getSoundManager().stopBgm(); // 停止 ← これが追加ポイント！
            }
        });
        soundPanel.add(bgmToggle);

        JCheckBox seToggle = new JCheckBox("効果音を再生");
        seToggle.setSelected(true);
        seToggle.setFont(new Font("Meiryo", Font.PLAIN, 16));
        seToggle.setBackground(Color.decode("#e6f2fa"));
        seToggle.setHorizontalAlignment(SwingConstants.CENTER);
        seToggle.addActionListener(e -> parent.getSoundManager().setSeEnabled(seToggle.isSelected()));
        soundPanel.add(seToggle);

        centerPanel.add(soundPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void styleModernButton(JButton btn) {
        btn.setBackground(Color.decode("#007bbb"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Meiryo", Font.PLAIN, 18));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 24, 12, 24));
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(Color.decode("#007bbb"), 2, true));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.decode("#005f99"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(Color.decode("#007bbb"));
            }
        });
    }
}
