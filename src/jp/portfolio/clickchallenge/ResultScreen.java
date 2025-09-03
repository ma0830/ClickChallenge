package jp.portfolio.clickchallenge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class ResultScreen extends JPanel {
    private JLabel scoreLabel;
    private JLabel quoteLabel;
    private JTextArea rankingArea;

    public ResultScreen(GamePanel parent) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // スコア表示（上部）
        scoreLabel = new JLabel("", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Meiryo", Font.BOLD, 28));
        scoreLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 0, 10, 0));
        add(scoreLabel, BorderLayout.NORTH);

        // 格言表示（中央）
        quoteLabel = new JLabel("", SwingConstants.CENTER);
        quoteLabel.setFont(new Font("Meiryo", Font.ITALIC, 20));
        quoteLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(quoteLabel, BorderLayout.CENTER);

        // ランキングとボタンをまとめるパネル（下部）
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.setBackground(new Color(245, 245, 245));

        // ランキング表示
        rankingArea = new JTextArea();
        rankingArea.setEditable(false);
        rankingArea.setFont(new Font("Meiryo", Font.PLAIN, 16));
        rankingArea.setBackground(new Color(250, 250, 250));
        JScrollPane scrollPane = new JScrollPane(rankingArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setAlignmentX(CENTER_ALIGNMENT);
        southPanel.add(scrollPane);

        // 「スタート画面に戻る」ボタン
        JButton restartButton = new JButton("スタート画面に戻る");
        restartButton.setFont(new Font("Meiryo", Font.PLAIN, 20));
        restartButton.setPreferredSize(new Dimension(220, 40));
        restartButton.setAlignmentX(CENTER_ALIGNMENT);
        restartButton.addActionListener(e -> parent.showStartScreen());
        southPanel.add(restartButton);

        add(southPanel, BorderLayout.SOUTH);
    }

    public void updateResult(int score, List<Integer> highScores, String quote) {
        if (score >= 0) {
            scoreLabel.setText("あなたのスコア: " + score);
            quoteLabel.setText("格言: " + quote);
        } else {
            scoreLabel.setText("ハイスコアランキング");
            quoteLabel.setText(""); // 格言は非表示
        }

        // ランキングは常に表示
        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (int s : highScores) {
            sb.append(String.format("%2d位: %3d 点\n", rank++, s));
        }
        rankingArea.setText(sb.toString());
    }
}
