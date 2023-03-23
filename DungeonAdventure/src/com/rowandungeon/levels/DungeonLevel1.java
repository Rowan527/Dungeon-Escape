package com.rowandungeon.levels;

/**
 *
 * @author 1939056
 */

import com.rowandungeon.characters.Door;
import com.rowandungeon.characters.Enemy;
import com.rowandungeon.characters.Loss;
import com.rowandungeon.game.DungeonGame;
import com.rowandungeon.characters.Player;
import com.rowandungeon.characters.Treasure;
import com.rowandungeon.characters.Vector;
import com.rowandungeon.game.DungeonSound;
import com.rowandungeon.characters.Victory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;


public class DungeonLevel1 extends JPanel implements ActionListener
{
    public DungeonGame game;
    private BufferedImage background;
    
    private Player player;
    public Door door;
    public Loss loss;
    private Victory victory;
    public Treasure[] treasure;
    public Enemy[] enemy;
    
    private final int NUMBER_OF_ENEMIES = 4;
    private final int NUMBER_OF_TREASURES = 5;
    
    public Timer timer;
    
    int milicounter = 0; //keeps track of miliseconds
    int constantTimer = 0; //keeps track of seconds
    
    public DungeonLevel1(DungeonGame game)
    {
        this.game = game;
        player = new Player();
        door = new Door();
        loss = new Loss();
        victory = new Victory();
        treasure = new Treasure[NUMBER_OF_TREASURES];
        enemy = new Enemy[NUMBER_OF_ENEMIES];
        
        init();
        resetLevel();
    }
    
    public void resetLevel()
    {
        Random rand = new Random();
        for(int i = 0; i < NUMBER_OF_ENEMIES; i ++)
        {
            Vector v = new Vector();
            v.setX(rand.nextInt(500)); //randomly sets the enemies X axis between 0 and 500
            v.setY(rand.nextInt(500)); //randomly sets the enemies Y axis between 0 and 500
            
            enemy[i] = new Enemy(v, 10); //adds the number of enemies set out by NUMBER_OF_ENEMIES
        }
        
        for(int j = 0; j < NUMBER_OF_TREASURES; j ++)
        {
            Vector v = new Vector();
            v.setX(rand.nextInt(500)); //randomly sets the treasure X axis between 0 and 500
            v.setY(rand.nextInt(500)); //randomly sets the treasure Y axis between 0 and 500
            
            treasure[j] = new Treasure(v, 100); //adds the number of enemies set out by NUMBER_OF_TREASURES
        }
    }
    
    private void init()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
    
        try
        {
            background = ImageIO.read(getClass().getResourceAsStream("/Images/Dungeon.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading background image");
        }
        
        timer = new Timer(10, this);
    
        DungeonSound.play(getClass().getResourceAsStream("/Sounds/music.wav"), true);
    }



    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background, 0, 0, null);
    
        String str = String.format("Score: " + player.score);
        String str1 = String.format("Time: " + constantTimer);
        String str2 = String.format("Health: " + player.health);
        String str3 = String.format("The door is now unlocked");
        String str4 = String.format("Press P to Return to Menu");
        g2d.drawString(str, 10, 15);
        g2d.drawString(str1, 10, 29);
        g2d.drawString(str2, 10, 43);
        if(player.treasureRemaining <= 0)
        {
            g2d.drawString(str3, 10, 57); //adds message once all treasures are collected
        }
        if(door.playerAtDoor == true && player.treasureRemaining <= 0)
        {
            g2d.drawString(str4, 10, 71); //adds message telling player how to return to menu if player escapes
            victory.draw(g2d); //brings up win text
        }else if (player.health <= 0)
        {
            g2d.drawString(str4, 10, 71);//adds message telling player how to return to menu if player dies
            loss.draw(g2d); //brings up lose text
        }
        
        player.draw(g2d);
        door.draw(g2d);
        for(Treasure t: treasure)
        {
            t.draw(g2d);
        }
        for(Enemy e: enemy)
        {
            e.draw(g2d);
        }
        
        
        g.dispose();
    }

    public void collisions()
    {
        player.checkCollisionTreasure(treasure);
        player.checkCollisionEnemy(enemy);
        door.checkCollisionPlayer(player);
    }

    public void movement()
    {
        player.doMove();
        
        for(Enemy e: enemy)
            e.doMove(600,600);
    }

    public void start()
    {
        timer.start();
    }
    public void stop()
    {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        collisions();
        movement();
        repaint();
        
        Vector v = new Vector();
        Random rand = new Random();
        
        milicounter++;
        
        if(milicounter == 100)
        {
            constantTimer = constantTimer + 1; //turns miliseconds into seconds
            milicounter = 0; //resets miliseconds
        }
        
        if(door.playerAtDoor == true && player.treasureRemaining <= 0)
        {
            stop(); //stops DungeonLevel1 when the player collects all treasure and is at door
        }
        else if (player.health <= 0)
        {
            stop(); //stops DungeonLevel1 when the player dies
        }
    }

    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            int move = 0;
            
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                move = 1;
                break;
                case KeyEvent.VK_DOWN:
                move = 2;
                break;
                case KeyEvent.VK_LEFT:
                move = 3;
                break;
                case KeyEvent.VK_RIGHT:
                move = 4;
                break;
            }
                player.move(move);
                
                
        }
        @Override
        public void keyReleased(KeyEvent e)
        {
            player.stop();
            if(e.getKeyCode() == KeyEvent.VK_P && player.health <= 0)
            {
                game.CloseWindow();
                game.initWindow();
                game.initScreens();
            } 
            else if(e.getKeyCode() == KeyEvent.VK_P && door.playerAtDoor == true && player.treasureRemaining <= 0)
            {
                game.CloseWindow();
                game.initWindow();
                game.initScreens();
                //closes DungeonLevel1 window and opens main menu
            }
        }
    }
}
            


