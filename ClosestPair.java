

import java.util.*;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class ClosestPair {

    // Calculate Euclidean distance
    public static double distance(Point p1, Point p2) {
        return Math.sqrt(
            (p1.x - p2.x) * (p1.x - p2.x) +
            (p1.y - p2.y) * (p1.y - p2.y)
        );
    }

    // Brute force for small sets
    public static double bruteForce(Point[] points, int n) {
        double min = Double.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double d = distance(points[i], points[j]);

                if (d < min) {
                    min = d;
                }
            }
        }

        return min;
    }

    // Find closest distance in the strip
    public static double stripClosest(
            Point[] strip, int size, double d) {

        Arrays.sort(strip, 0, size,
                    Comparator.comparingDouble(p -> p.y));

        double min = d;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1;
                 j < size && strip[j].y - strip[i].y < min;
                 j++) {

                double dist = distance(strip[i], strip[j]);

                if (dist < min) {
                    min = dist;
                }
            }
        }

        return min;
    }

    // Divide and conquer algorithm
    public static double closestPairRec(Point[] points, int n) {
        if (n <= 3) {
            return bruteForce(points, n);
        }

        int mid = n / 2;
        Point midPoint = points[mid];

        Point[] left = Arrays.copyOfRange(points, 0, mid);
        Point[] right = Arrays.copyOfRange(points, mid, n);

        double dl = closestPairRec(left, left.length);
        double dr = closestPairRec(right, right.length);

        double d = Math.min(dl, dr);

        // Create strip near the middle line
        Point[] strip = new Point[n];
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (Math.abs(points[i].x - midPoint.x) < d) {
                strip[j++] = points[i];
            }
        }

        return Math.min(d, stripClosest(strip, j, d));
    }

    public static void main(String[] args) {
        final int MAX_POINTS = 10000;
        Random rand = new Random();

        Point[] points = new Point[MAX_POINTS];

        // Generate random points in the unit square
        for (int i = 0; i < MAX_POINTS; i++) {
            points[i] = new Point(
                rand.nextDouble(),
                rand.nextDouble()
            );
        }

        // Sort points according to x-coordinate
        Arrays.sort(points,
                    Comparator.comparingDouble(p -> p.x));

        double minDistance =
            closestPairRec(points, MAX_POINTS);

        System.out.printf(
            "The closest pair of points has a distance of: %f%n",
            minDistance
        );
    }
}