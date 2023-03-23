package com.rowandungeon.characters;

/**
 *
 * @author 1939056
 */
public class Vector
{
    private int x;
    private int y;
    
    public Vector()
    {
        x = 0;
        y = 0;
    }
    public Vector(int newX, int newY)
    {
        x = newX;
        y = newY;
    }
    public Vector (Vector v)
    {
        x = v.getX();
        y = v.getY();
    }
    public void add(Vector v)
    {
        x += v.getX();
        y += v.getY();
    }
    public void setToVector(Vector v)
    {
        x = v.getX();
        y = v.getY();
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getX()
    {
        return x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getY()
    {
        return y;
    }
}
