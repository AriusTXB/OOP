package GameObjects;

public class ModelBoom 
{
    public ModelBoom(double size, float angle)
    {
        this.size = size;
        this.angle = angle;
    }
    double size;
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    float angle;
    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
    
    
}
