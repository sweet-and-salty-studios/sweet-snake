package com.sweetandsaltystudios;

import java.awt.Graphics;
import java.awt.Color;

public class Food {

    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private Color color = Color.WHITE;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Food(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics graphics) {

        graphics.setColor(color);
        graphics.fillOval(x, y, width, height);
    }
}