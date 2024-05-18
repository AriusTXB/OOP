package GameObjects;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
public class Player extends HpRender
{
    public Player()
    {
        super(new HP(75,75));
        this.image = new ImageIcon(getClass().getResource("/GameImages/Plane.png")).getImage();
        this.image_speed = new ImageIcon(getClass().getResource("/GameImages/Plane_speed.png")).getImage();
        Path2D P = new Path2D.Double();
        P.moveTo(2,16);
        P.lineTo(0, 31);
        P.lineTo(2,47);
        P.lineTo(17, 54);
        P.lineTo(27, 54);
        P.lineTo(33, 41);
        P.lineTo(PLAYER_SIZE, PLAYER_SIZE/2);
        P.lineTo(33, 22);
        P.lineTo(27, 9);
        P.lineTo(17, 9);
        player_shape = new Area(P);
    }
    public static final double PLAYER_SIZE = 64;
    private double x=50,y=50;
    private float angle = 0f;
    private final Image image;
    private final Image image_speed;
    private final float Max_speed = 1f;
    private float speed = 0f;
    private boolean speedUp;
    private boolean speedNeg;
    private final Area player_shape;
    private boolean alive = true;
    public void changeLocation(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
    public void update() {
        x += Math.cos(Math.toRadians(angle-90)) * speed;
        y += Math.sin(Math.toRadians(angle-90)) * speed;

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
        tran.rotate(Math.toRadians(angle),PLAYER_SIZE/2,PLAYER_SIZE/2);
        G2.drawImage(speedUp? image_speed:image, tran, null);
        hpRender(G2, getShape(), y);
        G2.setTransform(oldTransform);
    }

    public Area getShape()
    {
        AffineTransform afx = new AffineTransform();
        afx.translate(x, y);
        afx.rotate(Math.toRadians(angle-90),PLAYER_SIZE/2,PLAYER_SIZE/2);
        return new Area(afx.createTransformedShape(player_shape));
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

    public void speedUp()
    {
        speedUp = true;
        if(speed>Max_speed)
            {
                speed = Max_speed;
            }
        else
            {
                speed+=0.01f;
            }
    }
    public void speedNeg()
    {
        speedNeg = false;
        if(speed<-Max_speed)
            {
                speed = -Max_speed;
            }
        else
            {
                speed-=0.01f;
            }
    }
    public void speedDown()
    {
        speedUp = false;
        if (speed<=0)
            {
                speed = 0;
            }
        else if (speed>0)
            {
                speed-=0.005f;
            }
    }
    public void speedPos()
    {
        speedNeg = true;
        if (speed<=-Max_speed)
            {
                speed = -Max_speed;
            }
        else
            {
                speed+=0.005f;
            }
    }
    public boolean isAlive()
    {
        return alive;
    }
    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
    public void reset()
    {
        alive = true;
        resetHP();
        angle = 0;
        speed = 0;
    }
}
