package GameObjects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
public class HpRender 
{
    private final HP hp;
    public HpRender(HP hp)
    {
        this.hp = hp;
    }   
    protected void hpRender(Graphics2D G2, Shape shape, double y)
    {if(hp.getCurrent_HP()!=hp.getMAX_HP())
        {
            double hpY = shape.getBounds().getY()-y-10;
            G2.setColor(new Color(70,70,70));
            G2.fill(new Rectangle2D.Double(0,hpY-5,Player.PLAYER_SIZE,3));
            G2.setColor(new Color(253,91,91));
            double hpsize = hp.getCurrent_HP()/hp.MAX_HP*Player.PLAYER_SIZE;
            G2.fill(new Rectangle2D.Double(0, hpY-5, hpsize, 3));
        }
    }
    protected void hpRender2(Graphics2D G2, Shape shape, double y)
    
    {if(hp.getCurrent_HP()!=hp.getMAX_HP())
        {
            double hpY = shape.getBounds().getY()-y-10;
            G2.setColor(new Color(70,70,70));
            G2.fill(new Rectangle2D.Double(0,hpY-10,Player.PLAYER_SIZE-15,3));
            G2.setColor(new Color(253,91,91));
            double hpsize = hp.getCurrent_HP()/hp.MAX_HP*Player.PLAYER_SIZE-15;
            G2.fill(new Rectangle2D.Double(0, hpY-10, hpsize, 3));
        }
    }


    public boolean updateHP(double cutHP)
    {
        hp.setCurrent_HP(hp.getCurrent_HP()-cutHP);
        return hp.getCurrent_HP()>0;
    }

    public double getHP()
    {
        return hp.getCurrent_HP();
    }
    public void resetHP()
    {
        hp.setCurrent_HP(hp.getMAX_HP());
    }
}
