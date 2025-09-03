package jp.portfolio.clickchallenge;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Confetti {
    int x, y, size, dy;
    Color color;
    Random rand = new Random();

    public Confetti(int panelWidth, int panelHeight) {
        this.x = rand.nextInt(panelWidth);
        this.y = 0;
        this.size = rand.nextInt(10) + 5;
        this.dy = rand.nextInt(5) + 2;
        this.color = rand.nextBoolean() ? Color.YELLOW : Color.LIGHT_GRAY; // 金銀
    }

    public void update() {
        y += dy;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        if (rand.nextBoolean()) {
            g.fillRect(x, y, size, size);
        } else {
            g.fillOval(x, y, size, size);
        }
    }
}
