package jp.portfolio.clickchallenge;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
    private Clip bgmClip;
    private boolean bgmEnabled = true;
    private boolean seEnabled = true;

    /**
     * BGMを指定パスから再生（ループ）
     */
    public void playBGM(String path) {
        if (!bgmEnabled) return;
        try {
            if (bgmClip != null && bgmClip.isRunning()) {
                bgmClip.stop();
                bgmClip.close();
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
            bgmClip = AudioSystem.getClip();
            bgmClip.open(ais);
            bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgmClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * BGMを再生（固定パス使用）
     */
    public void playBgm() {
        playBGM("assets/bgm.wav");
    }

    /**
     * BGMを停止
     */
    public void stopBGM() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
        }
    }

    /**
     * 効果音を再生
     */
    public void playSE(String path) {
        if (!seEnabled) return;
        try {
            Clip seClip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
            seClip.open(ais);
            seClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * BGMの有効・無効を設定
     */
    public void setBgmEnabled(boolean enabled) {
        this.bgmEnabled = enabled;
        if (!enabled) stopBGM();
    }

    /**
     * 効果音の有効・無効を設定
     */
    public void setSeEnabled(boolean enabled) {
        this.seEnabled = enabled;
    }

    /**
     * BGMを完全に停止（別名）
     */
    public void stopBgm() {
        stopBGM();
    }
}

