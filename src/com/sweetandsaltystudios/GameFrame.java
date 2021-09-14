package com.sweetandsaltystudios;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

    private final String TITLE = "SWEET SNAKE";
    private final boolean IS_RESIZABLE = false;
    private final boolean IS_VISIBLE = true;
    
    //private final Component PARENT = null;
    
    public GameFrame() {
        this.add(new GamePanel());
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(IS_RESIZABLE);
        this.pack();
        this.setVisible(IS_VISIBLE);
        this.setLocationRelativeTo(/*PARENT*/null);
    }
}