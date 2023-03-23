package com.rowandungeon.characters;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
 *
 * @author 1939056
 */
public class Treasure 
{
    private Vector position;
    private int spriteWidth;
    private int spriteHeight;
    private boolean isVisible;
    
    private BufferedImage sprite;
    
    private int score;
    
    public Treasure(Vector newPosition, int newScore)
    {
        position = new Vector(newPosition);
        score = newScore;
        isVisible = true;
        
        try
        {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/Treasure.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading treasure sprite");
        }
        spriteWidth = 32;
        spriteHeight = 32;
    }
    
    public BufferedImage getSprite()
    {
        return sprite;
    }
    
    public Rectangle getBounds()
    {
        Rectangle objectRect = new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
        return objectRect;
    }
    
    public void setPosition(Vector v)
    {
        position.setToVector(v);
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
    
    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }
    
    public boolean getVisible()
    {
        return isVisible;
    }
    
    public void draw(Graphics2D g)
    {
        if(isVisible == true)
            g.drawImage(sprite, position.getX(), position.getY(), null);
    }
}

