/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rowandungeon.characters;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

public class Enemy 
{
    private Vector position;
    private int spriteWidth;
    private int spriteHeight;
    Vector displacement;
    public int speed = 2;
    
    private BufferedImage sprite;
    
    private int score;
    
    public Enemy(Vector newPosition, int newScore)
    {
        position = new Vector(newPosition);
        score = newScore;
        
        init();
    }
    
    private void init()
    {
      try
        {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/Enemy.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading enemy sprite");
        }
        spriteWidth = sprite.getWidth();
        spriteHeight = sprite.getHeight();  
    }
    
    public BufferedImage getSprite()
    {
        return sprite;
    }
    
    public void doMove(int WINDOW_WIDTH, int WINDOW_HEIGHT)
    {
        Random rand = new Random();
        int randomDirection;
        
        Vector tempPosition = new Vector(position);
        Vector displacement = new Vector();
        
        randomDirection = rand.nextInt(4) + 1;
        
        switch(randomDirection)
        {
            case 1:
                displacement.setY(-speed);
                break;
            case 2:
                displacement.setY(speed);
                break;
            case 3:
                displacement.setX(-speed);
                break;
            case 4:
                displacement.setX(speed);
        } //randomly moves the enemy
        
        if(tempPosition.getX() < (spriteWidth / 2))
        {
            displacement.setX(1);
        }else if(tempPosition.getX() > 500 - (spriteWidth / 2))
        {
            displacement.setX(-1);
        }
        
        if(tempPosition.getY() < (spriteHeight / 2))
        {
            displacement.setY(-1);
        }else if(spriteHeight > 500 - (spriteHeight / 2))
        {
            displacement.setY(1);
        }
        
        tempPosition.add(displacement);
        position.setToVector(tempPosition);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
    }
    
    public void draw(Graphics2D g)
    {
        g.drawImage(sprite, position.getX(), position.getY(), null);
    }
}
