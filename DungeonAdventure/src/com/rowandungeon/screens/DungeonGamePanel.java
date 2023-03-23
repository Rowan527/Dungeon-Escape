package com.rowandungeon.screens;

import com.rowandungeon.game.DungeonGame;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DungeonGamePanel  extends JPanel
{
    private DungeonGame game; 
    private BufferedImage backgroundImage = null;
    
    public DungeonGamePanel(DungeonGame theGame)
    {
        game = theGame;
        init();
    }
    private void init()
    {
        try
        {
            backgroundImage = ImageIO.read(getClass().getResource("/Images/MainMenu.png"));
        }catch(Exception ex){
            System.err.println("Error Loading Image");
        }
        
        addKeyListener(new TAdapter());
        setFocusable(true);
    } 
    
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e)
        {
            if(e.getKeyCode() == KeyEvent.VK_P)
            {
                game.startGame();
            }
        }
    } 
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(backgroundImage, 0, 0, null);
        
        String str = String.format("Press P to Start");
        g2d.drawString(str, 20, 150);
    } 
}


