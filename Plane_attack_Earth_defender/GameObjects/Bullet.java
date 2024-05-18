package GameObjects;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
public class Bullet 
{
    private double x,y;
    private final Shape shape;    
    private final Color color = new Color(255,255,255);
    private final float angle;
    private double size;
    private float speed = 1f;

    public Bullet (double x, double y, float angle, double size, float speed)
    {
        x+=Player.PLAYER_SIZE/2-size/2;
        y+=Player.PLAYER_SIZE/2-size/2;
        this.x=x;
        this.y=y;
        this.angle = angle;
        this.size = size;
        this.speed = speed;
        shape = new Ellipse2D.Double(0,0,size,size);
    }

    public void update()
    {
        x+=Math.cos(Math.toRadians(angle-90))*speed;
        y+=Math.sin(Math.toRadians(angle-90))*speed;
    }
    public void draw(Graphics2D G2)
    {
        AffineTransform oldTransform = G2.getTransform();
        G2.setColor(color);
        G2.translate(x, y);
        G2.fill(shape);
        G2.setTransform(oldTransform);
    }

    public Shape getShape()
    {
        return new Area(new Ellipse2D.Double(x,y,size,size));
    }
    public boolean check (int width,int height)
    {
        if(x<=size||y<=size||x>=width||y>=height)
            {
                return false;
            }
        else
            {
                return true;
            }
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getSize()
    {
        return size;
    }
}
