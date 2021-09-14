package com.sweetandsaltystudios;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;

public class Snake extends GameObject {

    private static final Color HEAD_COLOR = Color.GREEN;
    private static final Color BODY_COLOR = new Color(45, 180, 0);
    private final int X[] = new int[GamePanel.GAME_UNIT];
    private final int Y[] = new int[GamePanel.GAME_UNIT];

    private int bodyParts = 6;
    private int foodEaten = 0;
    private DIRECTION direction = DIRECTION.RIGHT;

    private KeyAdapter keyAdapter = null;
    private ArrayList<Food> foods = null;

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION newDirection) {
        direction = newDirection;
    }

    public KeyAdapter getKeyAdapter() {
        return keyAdapter;
    }

    public int getEatenFoodAmount() {
        return foodEaten;
    }

    public Snake(ArrayList<Food> foods) {
        super();

        keyAdapter = new KeyAdapter(this);

        this.foods = foods;
    }

    public void move() {
        for (var i = bodyParts; i > 0; i--) {
            X[i] = X[i - 1];
            Y[i] = Y[i - 1];
        }

        switch (direction) {
            case UP:
                Y[0] = Y[0] - GamePanel.UNIT_SIZE;
                break;
            case RIGHT:
                X[0] = X[0] + GamePanel.UNIT_SIZE;
                break;
            case DOWN:
                Y[0] = Y[0] + GamePanel.UNIT_SIZE;
                break;
            case LEFT:
                X[0] = X[0] - GamePanel.UNIT_SIZE;
                break;
            default:
                break;
        }

        checkCollision();
    }

    public void checkCollision() {

        // if head collides with borders
        if ((X[0] < 0 || X[0] >= GamePanel.SCREEN_WIDTH) || (Y[0] < 0 || Y[0] >= GamePanel.SCREEN_HEIGHT)) {
            GamePanel.isRunning = false;
        }

        // if head collides with body
        for (var i = bodyParts; i > 0; i--) {
            if (X[0] == X[i] && Y[0] == Y[i]) {
                GamePanel.isRunning = false;
            }
        }

        // if head collides with food
        for (var food : foods) {
            if (X[0] == food.getX() && Y[0] == food.getY()) {
                bodyParts++;
                foodEaten++;
                GamePanel.spawnFood();
            }
        }
    }

    public void draw(Graphics graphics) {
        for (var i = 0; i < bodyParts; i++) {
            graphics.setColor(i == 0 ? HEAD_COLOR : BODY_COLOR);
            graphics.fillRect(X[i], Y[i], GamePanel.UNIT_SIZE, GamePanel.UNIT_SIZE);
        }
    }
}