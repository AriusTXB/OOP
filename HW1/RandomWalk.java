public class RandomWalk {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        StdDraw.setScale(-n - 0.5, n + 0.5);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        int x = 0, y = 0, dx = 0, dy = -1;
        int steps = 0;
        while (Math.abs(x) < n && Math.abs(y) < n) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(x, y, 0.45);
            int sidelength = (int) Math.sqrt(steps + 1) / 2;
            if ((x == y) || ((x < 0) && (x == -y)) || ((x > 0) && (x == 1 - y))) {
                sidelength = dx;
                dx = -dy;
                dy = sidelength;
            }
            steps++;
            x += dx;
            y += dy;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x, y, 0.45);
            StdDraw.show();
            StdDraw.pause(40);
        }
        StdOut.println("Total steps = " + steps);
    }
}
