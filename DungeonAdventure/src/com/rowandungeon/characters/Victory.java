package com.rowandungeon.characters;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author 1939056
 */
public class Victory 
{
    Vector position;
    Vector displacement;
    
    public Treasure treasure;
    public Door door;
    public Enemy enemy;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    public boolean isVisible = true;
    
    
    public Victory()
    {
        position = new Vector(150, 150);
        displacement = new Vector(0,0);
        init();
    }
    private void init()
    {
        try 
        {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/YouEscaped.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading Victory Message");
        }
        spriteWidth = 244;
        spriteHeight = 50;
    }
    
    public void setPosition(Vector v)
    {
        position = v;
    }
    
    public Vector getPosition()
    {
        return position;
    }
    
    public BufferedImage getSprite()
    {
        return sprite;
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
    
    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }
}
