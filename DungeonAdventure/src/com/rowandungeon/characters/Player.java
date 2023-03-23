package com.rowandungeon.characters;

import com.rowandungeon.game.DungeonGame;
import com.rowandungeon.game.DungeonSound;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author 1939056
 */
public class Player 
{
    Vector position;
    Vector displacement;
    
    public Treasure treasure;
    public DungeonGame game;
    public Door door;
    
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    public int score;
    public int treasureRemaining = 5;
    
    
    
    public int health = 1000;
    
    public Player()
    {
        position = new Vector(100, 100);
        displacement = new Vector(0,0);
        score = 0;
        init();
    }
    private void init()
    {
        try 
        {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/Player.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading player sprite");
        }
        spriteWidth = 16;
        spriteHeight = 16;
    }
    
    public void setPosition(Vector v)
    {
        position = v;
    }
    public Vector getPosition()
    {
        return position;
    }
    public void setScore(int newScore)
    {
        score = newScore;
    }
    public int getScore()
    {
        return score;
    }
    public BufferedImage getSprite()
    {
        return sprite;
    }
    
    public void move(int direction)
    {
        switch (direction)
        {
            case 1:
                displacement.setY(-1);
                break;
            case 2:
                displacement.setY(1);
                break;
            case 3:
                displacement.setX(-1);
                break;
            case 4:
                displacement.setX(1);
                break;
            default:
                break;             
        }
    }
    
    public void doMove()
    {
        position.add(displacement);
    }
    public void stop()
    {
        displacement.setX(0);
        displacement.setY(0);
    }
    public void draw(Graphics2D g)
    {
        g.drawImage(sprite, position.getX(), position.getY(), null);
    }
    
    public Rectangle getBounds()
    {
        Rectangle characterRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
        return characterRect;
    }
    
    public boolean checkCollisionTreasure(Treasure[] treasure)
    {
        for(Treasure t: treasure)
        {
            if(t.getBounds().intersects(getBounds()))
            {
                if(t.getVisible() == true)
                {
                    score += t.getScore(); //adds score
                    treasureRemaining = treasureRemaining - 1; //keeps track of how many treasures remain
                    
                    DungeonSound.play(getClass().getResourceAsStream("/Sounds/collision.wav"), false);
                    t.setVisible(false);//removes treasure player hit
                    
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCollisionEnemy(Enemy[] enemy)
    {
        for(Enemy e: enemy)
        {
            if(e.getBounds().intersects(getBounds()))
            {
                DungeonSound.play(getClass().getResourceAsStream("/Sounds/collision.wav"), false);
                score = score - 1; //reduces current score by 1
                health = health - 10; // reduces current health by 10
                return true;
            }
        }
        return false;
    }
    
}