package com.rowandungeon.game;
import com.rowandungeon.levels.DungeonLevel1;
import com.rowandungeon.screens.DungeonGamePanel;
import com.rowandungeon.characters.Player;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author 1939056
 */
public class DungeonGame
{
    JFrame gameWindow;
    JFrame deathScreen;
    JFrame victoryScreen;
    DungeonGamePanel startScreen;
    DungeonLevel1 lvl1;
    JLabel counterLabel;
    Timer Timer;
    
    private Player player;
    
    public DungeonGame()
    {
        initWindow();
        initScreens();
    }
    
    public static void main(String[] args)
    {
        DungeonGame window = new DungeonGame();
        window.showStartScreen();
    }
    
    public void initWindow()
    {
        gameWindow = new JFrame();
        gameWindow.setSize(600,600);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.getContentPane().setLayout(new CardLayout());
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null); // This centres the game on screen
        gameWindow.setTitle("Dungeon Adventure");
        gameWindow.setVisible(true); // This shows the game window
    }
    
    public void deathScreen()
    {
        deathScreen = new JFrame();
        deathScreen.setSize(600,600);
        deathScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deathScreen.getContentPane().setLayout(new CardLayout());
        deathScreen.setResizable(false);
        deathScreen.setLocationRelativeTo(null); 
        deathScreen.setTitle("You Died");
        gameWindow.setVisible(true);
    }
    
    public void CloseWindow()
    {
        gameWindow.dispose();
    }
    
    public void initScreens()
    {
        startScreen = new DungeonGamePanel(this);
        startScreen.setPreferredSize(new Dimension(600,600));
        lvl1 = new DungeonLevel1(this);
        lvl1.setPreferredSize(new Dimension(600,600));
        gameWindow.getContentPane().add(startScreen, "INTRO");
        gameWindow.getContentPane().add(lvl1, "LVL1");
    }
    
    public void startGame()
    {
        CardLayout cl = (CardLayout)gameWindow.getContentPane().getLayout();
        cl.next(gameWindow.getContentPane());
        lvl1.requestFocus();
        lvl1.start();
        lvl1.timer.start();
    }
    
    public void showStartScreen()
    {
        gameWindow.setVisible(true);
        startScreen.requestFocus();
    }
    
    
}