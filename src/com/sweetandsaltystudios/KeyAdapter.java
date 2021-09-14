package com.sweetandsaltystudios;

import java.awt.event.KeyEvent;

public class KeyAdapter extends java.awt.event.KeyAdapter {

    private Snake snake = null;

    public KeyAdapter(Snake snake) {
        super();

        this.snake = snake;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (snake.getDirection() != DIRECTION.DOWN) {
                    snake.setDirection(DIRECTION.UP);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (snake.getDirection() != DIRECTION.LEFT) {
                    snake.setDirection(DIRECTION.RIGHT);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (snake.getDirection() != DIRECTION.UP) {
                    snake.setDirection(DIRECTION.DOWN);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (snake.getDirection() != DIRECTION.RIGHT) {
                    snake.setDirection(DIRECTION.LEFT);
                }
                break;
        }
    }
}