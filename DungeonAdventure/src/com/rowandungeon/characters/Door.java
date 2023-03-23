/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rowandungeon.characters;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Door 
{
    public boolean doorLocked = true;
    public boolean playerAtDoor = false;
    private BufferedImage sprite;
    private int spriteWidth;
    private int spriteHeight;
    Vector position;
    Vector displacement;
    public Player player;
    
    

    public Door()
    {
        player = new Player();
        position = new Vector(300, 300);
        displacement = new Vector(0,0);
        
        init();
    }
    
    private void init()
    {
        try
        {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Images/DoorClosed.png"));
        }catch(Exception ex)
        {
            System.err.println("Error loading door sprite");
        }
        
        
        
        spriteWidth = 32;
        spriteHeight = 32;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(position.getX(), position.getY(), spriteWidth, spriteHeight);
    }

    public void draw(Graphics2D g)
    {
        g.drawImage(sprite, position.getX(), position.getY(), null);
    }
    
    public boolean checkCollisionPlayer(Player p)
    {
        if(p.getBounds().intersects(getBounds()))   
        {
            playerAtDoor = true; //detects when player is touching door
        }
        else
        {
            playerAtDoor = false;
        }
        return false;
    }
    
    
}
