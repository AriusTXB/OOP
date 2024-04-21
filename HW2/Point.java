package HW2;

public class Point 
{
    private final double x;
    private final double y;
    public Point(double x, double y) 
    {
        this.x = x;
        this.y = y;
    }
    public double distanceTo(Point that) 
    {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
        public static void main(String[] args) {
        Point p = new Point(1, 2);
        System.out.println("p  = " + p.toString());
        Point q = new Point(3, 4);
        System.out.println("q  = " + q.toString());
        System.out.println("dist(p, q) = " + p.distanceTo(q));
    }
}
