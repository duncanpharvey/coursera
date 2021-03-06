/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        double x0 = this.x;
        double y0 = this.y;
        double x1 = that.x;
        double y1 = that.y;
        if (Double.compare(x0, x1) == 0 && Double.compare(y0, y1) == 0) return Double.NEGATIVE_INFINITY;
        else if (Double.compare(x0, x1) == 0) return Double.POSITIVE_INFINITY;
        else return ((y1 - y0) / (x1 - x0)) + 0.0;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
        else if (this.y > that.y || (this.y == that.y && this.x > that.x)) return 1;
        else return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new BySlope();
    }

    private class BySlope implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (Point.this.slopeTo(a) < Point.this.slopeTo(b)) return -1;
            else if (Point.this.slopeTo(a) > Point.this.slopeTo(b)) return 1;
            else return 0;
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point[] points = new Point[16];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 2);
        points[3] = new Point(0, 3);
        points[4] = new Point(1, 3);
        points[5] = new Point(2, 3);
        points[6] = new Point(3, 3);
        points[7] = new Point(3, 2);
        points[8] = new Point(3, 1);
        points[9] = new Point(3, 0);
        points[10] = new Point(2, 0);
        points[11] = new Point(1, 0);
        points[12] = new Point(1, 1);
        points[13] = new Point(2, 2);
        points[14] = new Point(1, 2);
        points[15] = new Point(2, 1);

        for (Point p : points)
            StdOut.print(p.toString() + " ");
        StdOut.println();

        Arrays.sort(points);

        for (Point p : points)
            StdOut.print(p.toString() + " ");
        StdOut.println();

        Arrays.sort(points, points[1].slopeOrder());

        for (Point p : points)
            StdOut.print(p.toString() + " ");
        StdOut.println();
    }
}
