package jp.portfolio.clickchallenge;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PlayScreen extends JPanel implements ActionListener, MouseListener {

    private GamePanel parent;
    private java.util.List<Tile> tiles = new ArrayList<>();
    private java.util.List<Confetti> confettis = new ArrayList<>();
    private java.util.List<String> quotes = Arrays.asList(
        "成功とは、情熱を失わずに失敗を重ねることである - ウィンストン・チャーチル",
        "最も遠くまで行く者は、最初の一歩を踏み出した者である - 老子",
        "努力は必ず報われる。ただし報われるまで努力が必要だ - イチロー",
        "夢を持ち続けることが、人生を輝かせる - 松岡修造",
        "挑戦しないことが、最大の失敗である - トーマス・エジソン"
    );

    private Timer gameTimer;
    private Timer confettiTimer;
    private int timeLeft = 60;
    private int score = 0;
    private boolean gameOver = false;

    private JButton backButton;
    private final int TILE_COUNT = 250;

    public PlayScreen(GamePanel parent) {
        this.parent = parent;
        setLayout(null);
        setBackground(Color.decode("#e6f2fa")); // 淡い水色背景
        setFocusable(true);
        addMouseListener(this);

        backButton = new JButton("スタートに戻る");
        styleButton(backButton);
        backButton.setBounds(700, 620, 160, 30);
        backButton.addActionListener(e -> {
            gameTimer.stop();
            confettiTimer.stop();
            parent.showStartScreen();
        });
        add(backButton);
    }

    public void startNewGame() {
        tiles.clear();
        confettis.clear();
        score = 0;
        timeLeft = 60;
        gameOver = false;

        Random rand = new Random();
        for (int i = 0; i < TILE_COUNT; i++) {
            int x = rand.nextInt(800 - 40);
            int y = rand.nextInt(600 - 100);
            tiles.add(new Tile(x, y, 40));
        }

        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            if (timeLeft <= 0) {
                endGame(false);
            }
            repaint();
        });
        gameTimer.start();

        confettiTimer = new Timer(50, this);
        confettiTimer.start();
    }

    private void endGame(boolean cleared) {
        gameOver = true;
        gameTimer.stop();
        confettiTimer.stop();

        String quote = quotes.get(new Random().nextInt(quotes.size()));

        if (cleared) {
            JOptionPane.showMessageDialog(this, "ゲームクリア！おめでとうございます 🎉", "達成", JOptionPane.INFORMATION_MESSAGE);
        }

        parent.showResult(score, quote);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Tile tile : tiles) {
            tile.draw(g);
        }

        if (score >= 80) {
            for (Confetti c : confettis) {
                c.draw(g);
            }
        }

        g.setColor(Color.decode("#007bbb"));
        g.setFont(new Font("Meiryo", Font.BOLD, 20));
        g.drawString("スコア: " + score, 20, 620);
        g.drawString("残り時間: " + timeLeft + "秒", 180, 620);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (gameOver) return;

        for (Tile tile : tiles) {
            if (!tile.clicked && tile.contains(e.getX(), e.getY())) {
                tile.clicked = true;
                tile.setColor(getColorByClick(score));
                score++;

                // 効果音再生
                parent.getSoundManager().playSE("assets/click.wav");

                if (score >= 80) {
                    confettis.add(new Confetti(getWidth(), getHeight()));
                }
                if (score >= TILE_COUNT) {
                    endGame(true);
                }
                repaint();
                break;
            }
        }
    }


    private Color getColorByClick(int count) {
        if (count < 20) return Color.GRAY;
        else if (count < 40) return Color.decode("#3399cc");
        else if (count < 60) return Color.decode("#33ffcc");
        else if (count < 80) return Color.decode("#ffcce5");
        else if (count < 100) return Color.decode("#fff462");
        else if (count < 120) return Color.decode("#cc3366");
        else return Color.decode("#007bbb");
    }

    private void styleButton(JButton btn) {
        btn.setBackground(Color.decode("#007bbb"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Meiryo", Font.PLAIN, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
    }

    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Confetti c : confettis) {
            c.update();
        }
        repaint();
    }
}
