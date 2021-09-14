package com.sweetandsaltystudios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    // GAME
    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;
    public static final int UNIT_SIZE = 25;

    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final boolean IS_FOCUSABLE = true;

    public static final int GAME_UNIT = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    public static final int DELAY = 75;
    private static final String GAME_OVER_TEXT = "GAME OVER!";
    private static final String SCORE_TEXT = "SCORE: ";

    private Timer timer = null;
    private static Random random = null;

    public static boolean isRunning = false;

    private Snake snake = null;
    private ArrayList<Food> foods = null;

    public GamePanel() {
        super();

        random = new Random();
        foods = new ArrayList<Food>();

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(BACKGROUND_COLOR);
        this.setFocusable(IS_FOCUSABLE);

        snake = spawnSnake(foods);
        foods.add(spawnFood());

        this.addKeyListener(snake.getKeyAdapter());

        startGame();
    }

    private void startGame() {

        isRunning = true;
        timer = new Timer(DELAY, this);
        timer.start();

    }

    private void gameOver(Graphics graphics) {
        graphics.setFont(new Font("Ink Free", Font.BOLD, 75));
        graphics.setColor(Color.RED);
        var metrics = getFontMetrics(graphics.getFont());
        graphics.drawString(GAME_OVER_TEXT, (SCREEN_WIDTH - metrics.stringWidth(GAME_OVER_TEXT)) / 2,
                SCREEN_HEIGHT / 2);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        draw(graphics);
    }

    private void draw(Graphics graphics) {
        for (var i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            graphics.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }

        for (var food : foods) {
            food.draw(graphics);
        }

        snake.draw(graphics);

        drawScore(graphics, snake.getEatenFoodAmount());

        if (isRunning == false) {
            gameOver(graphics);
        }
    }

    private void drawScore(Graphics graphics, int eatenFoodAmount) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
        var metrics = getFontMetrics(graphics.getFont());
        graphics.drawString(SCORE_TEXT + eatenFoodAmount, (SCREEN_WIDTH - metrics.stringWidth(SCORE_TEXT)) / 2,
                graphics.getFont().getSize());
    }

    private Snake spawnSnake(ArrayList<Food> foods) {
        snake = new Snake(foods);

        return snake;
    }

    public static Food spawnFood() {
        var x = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        var y = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        var food = new Food(x, y, UNIT_SIZE, UNIT_SIZE, Color.YELLOW);
        return food;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (isRunning) {
            snake.move();
            repaint();
        } else {
            timer.stop();
        }
    }
}