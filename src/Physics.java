public final class Physics {

    private Physics() {}

    // Sagt die Y-Position voraus, an der der Ball die rechte Wand trifft (inkl. Bounces an Top/Bottom).
    // Gibt -1 zurück, wenn der Ball nicht nach rechts fliegt.
    public static int predictYAtRightWall(
            int x0, int y0,
            double vx, double vy,
            int ballRadius,
            int fieldWidth, int fieldHeight
    ) {
        if (vx <= 0) {
            return -1; // bewegt sich nicht nach rechts
        }

        int minY = ballRadius;
        int maxY = fieldHeight - ballRadius;
        int span = maxY - minY;
        if (span <= 0) {
            return Math.max(Math.min(y0, maxY), minY);
        }

        int xRight = fieldWidth - ballRadius;               // x-Position der rechten Kante, an der der Ball "berührt"
        double dx = (double) xRight - x0;
        double m = (double) vy / (double) vx;
        double yLinear = y0 + m * dx;                       // ungefaltete Y-Position

        // Reflexion (Sägezahn-Faltung) in [minY, maxY]
        double period = 2.0 * span;                         // Auf-Ab = Periode
        double yPrime = yLinear - minY;                     // nach 0 verschoben
        double mod = yPrime % period;
        if (mod < 0) mod += period;                         // korrektes Modulo für negative Werte
        double folded = (mod <= span) ? mod : (period - mod);
        double yHit = folded + minY;

        return (int) Math.round(yHit);
    }
}