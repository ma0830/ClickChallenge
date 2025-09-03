package jp.portfolio.clickchallenge;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private StartScreen startScreen;
    private PlayScreen playScreen;
    private ResultScreen resultScreen;

    private List<Integer> highScores = new ArrayList<>();
    private SoundManager soundManager = new SoundManager();

    public GamePanel() {
        setLayout(new BorderLayout());

        startScreen = new StartScreen(this);
        playScreen = new PlayScreen(this);
        resultScreen = new ResultScreen(this);

        mainPanel.add(startScreen, "start");
        mainPanel.add(playScreen, "play");
        mainPanel.add(resultScreen, "result");

        add(mainPanel, BorderLayout.CENTER);
        showStartScreen();
    }

    public void showStartScreen() {
        cardLayout.show(mainPanel, "start");
    }

    public void startGame() {
        soundManager.playBGM("assets/bgm.wav");
        playScreen.startNewGame();
        cardLayout.show(mainPanel, "play");
    }

    public void showResult(int score, String quote) {
        updateHighScores(score);
        resultScreen.updateResult(score, highScores, quote);
        cardLayout.show(mainPanel, "result");
    }

    public void showRankingOnly() {
        resultScreen.updateResult(-1, highScores, "");
        cardLayout.show(mainPanel, "result");
    }

    private void updateHighScores(int score) {
        highScores.add(score);
        highScores.sort(Collections.reverseOrder());
        if (highScores.size() > 5) {
            highScores = highScores.subList(0, 5);
        }
    }

    public List<Integer> getHighScores() {
        return highScores;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }
}