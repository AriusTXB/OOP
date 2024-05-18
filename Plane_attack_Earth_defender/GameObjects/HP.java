package GameObjects;

public class HP
{
    double MAX_HP;
    double current_HP;
    public double getMAX_HP() {
        return MAX_HP;
    }
    public void setMAX_HP(double mAX_HP) {
        MAX_HP = mAX_HP;
    }
    public double getCurrent_HP() {
        return current_HP;
    }
    public void setCurrent_HP(double current_HP) {
        this.current_HP = current_HP;
    }    
    public HP(double MAX_HP,double current_HP)
    {
        this.MAX_HP = MAX_HP;
        this.current_HP = current_HP;
    }
}
