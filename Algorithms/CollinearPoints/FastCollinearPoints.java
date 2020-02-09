import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private final int numberOfSegments;
    private final Stack<LineSegment> segmentStack = new Stack<LineSegment>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("point array must not be null");
        }
        int n = points.length;
        Point[] sorted = new Point[n];
        for (int i = 0; i < n; i++) {
            Point p = points[i];
            checkNull(p);
            sorted[i] = p;
        }
        Arrays.sort(sorted);
        for (int i = 1; i < n; i++) {
            checkEqual(sorted[i - 1], sorted[i]);
        }
        Point[] copy = new Point[n];
        for (Point p : points) {
            for (int i = 0; i < n; i++) {
                copy[i] = sorted[i];
            }
            Arrays.sort(copy, p.slopeOrder());
            int count = 2;
            Point next = copy[0];
            for (int j = 0; j < n - 1; j++) {
                Point current = next;
                next = copy[j + 1];
                if (Double.compare(p.slopeTo(current), p.slopeTo(next)) != 0) {
                    if (count >= 4 && p.compareTo(copy[j - count + 2]) < 0) {
                        segmentStack.push(new LineSegment(p, current));
                    }
                    count = 2;
                } else {
                    count++;
                }
                if (j == n - 2 && count >= 4 && p.compareTo(copy[j - count + 3]) < 0) {
                    segmentStack.push(new LineSegment(p, next));
                }
            }
        }
        numberOfSegments = segmentStack.size();
    }

    private void checkNull(Point p) {
        if (p == null) throw new IllegalArgumentException("point must not be null");
    }

    private void checkEqual(Point a, Point b) {
        if (a.compareTo(b) == 0) {
            throw new IllegalArgumentException("point array must not have repeated points: " + a.toString());
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return numberOfSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        int n = numberOfSegments;
        LineSegment[] segments = new LineSegment[n];
        for (LineSegment segment : segmentStack) {
            segments[--n] = segment;
        }
        return segments;
    }

    public static void main(String[] args) {
        Point[] points = new Point[17];
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
        points[16] = new Point(4, 0);

        FastCollinearPoints test = new FastCollinearPoints(points);
        StdOut.println("\n" + test.numberOfSegments());
        for (LineSegment s : test.segments())
            StdOut.println(s);
    }

}
