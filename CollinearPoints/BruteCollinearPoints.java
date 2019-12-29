import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    private final int numberOfSegments;
    private final Stack<LineSegment> segmentStack = new Stack<LineSegment>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("point array must not be null");
        }
        int n = points.length;
        Point[] copy = new Point[n];
        for (int i = 0; i < n; i++) {
            Point p = points[i];
            checkNull(p);
            copy[i] = p;
        }
        Arrays.sort(copy);
        for (int i = 1; i < n; i++) {
            checkEqual(copy[i - 1], copy[i]);
        }
        // loop through once to check if null or equals to avoid doing that in later loop
        for (int i = 0; i < n; i++) {
            Point p = copy[i];
            for (int j = i + 1; j < n; j++) {
                Point q = copy[j];
                double pq = p.slopeTo(q);
                for (int k = j + 1; k < n; k++) {
                    Point r = copy[k];
                    double pr = p.slopeTo(r);
                    for (int m = k + 1; m < n; m++) {
                        Point s = copy[m];
                        double ps = p.slopeTo(s);
                        if (Double.compare(pq, pr) == 0 && Double.compare(pr, ps) == 0) {
                            segmentStack.push(new LineSegment(p, s));
                        }
                    }
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

        BruteCollinearPoints test = new BruteCollinearPoints(points);
        StdOut.println("\n" + test.numberOfSegments());
        for (LineSegment s : test.segments())
            StdOut.println(s);
    }
}
