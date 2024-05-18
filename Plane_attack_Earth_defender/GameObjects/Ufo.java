package GameObjects;

import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.Shape;

public class Ufo extends HpRender
{
    public Ufo()
    {
        super(new HP(50,50));
        this.image = new ImageIcon(getClass().getResource("/GameImages/UFO.png")).getImage();
        Path2D p = new Path2D.Double();
        p.moveTo(UFO_SIZE/2,UFO_SIZE);
        p.lineTo(43, 32);
        p.lineTo(50, 14);
        p.lineTo(0, 14);
        p.lineTo(6, 32);
        UFO_SHAPE = new Area(p);
    }
    public static final double UFO_SIZE=50;
    private double x,y;
    private float speed = 0.3f;
    private float angle = 0;
    private final Image image;
    private final Area UFO_SHAPE;

    public void changeLocation(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
    public void update() {
        x -= Math.cos(Math.toRadians(angle-90)) * speed;
        y -= Math.sin(Math.toRadians(angle-90)) * speed;

    }
    public void changeAngle(float angle)
    {
        if(angle < 0)
            {
                angle = 359;

            }
        else if(angle > 359 )
            {
                angle = 0;
            }
        this.angle = angle ;
    }
    public void draw(Graphics2D G2)
    {
        AffineTransform oldTransform = G2.getTransform();
        G2.translate(x,y);
        AffineTransform tran = new AffineTransform();
        tran.rotate(Math.toRadians(angle),UFO_SIZE/2,UFO_SIZE/2);
        G2.drawImage(image, tran, null);
        Shape shape = getShape();
        hpRender2(G2, shape, y);;
        G2.setTransform(oldTransform);
    }


    public double getX()
    {
        return x;
    }

    public  double getY()
    {
        return y;
    }

    public float getAngle()
    {
        return angle;
    }

    public Area getShape()
    {
        AffineTransform afx = new AffineTransform();
        afx.translate(x, y);
        afx.rotate(Math.toRadians(angle),UFO_SIZE/2,UFO_SIZE/2);
        return new Area(afx.createTransformedShape(UFO_SHAPE));
    }

    public boolean check(int width, int height)
        {
            Rectangle size = getShape().getBounds();
            if(x<=-size.getWidth()||y<-size.getHeight()||x>width||y>height)
                {
                    return false;
                }
            else
                return true;
        }
}
