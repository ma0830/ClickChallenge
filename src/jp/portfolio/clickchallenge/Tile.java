package jp.portfolio.clickchallenge;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
    int x, y, size;
    boolean clicked = false;
    private Color color = Color.GRAY;

    public Tile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void draw(Graphics g) {
        if (clicked) {
            g.setColor(color);
            g.fillRect(x, y, size, size);
        }
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }

    public boolean contains(int px, int py) {
        // 判定範囲を少し広げて、クリックしやすくする
        int margin = 4;
        return px >= x - margin && px <= x + size + margin &&
               py >= y - margin && py <= y + size + margin;
    }
}
