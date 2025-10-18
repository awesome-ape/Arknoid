package shapes;

/**
 * The shapes.Point class represents a point in a 2D space with x and y coordinates.
 * It provides methods to calculate the distance to another point, check equality with another point,
 * and get or set the coordinates.
 *  Author: Almog Salman
 *  ID: 324079458
 *  * @version "19.0.2"
 *  * @since 2024-06-02
 */
public class Point {
    private double x;
    private double y;
    private final double threshold = 0.01;

    /**
     * Constructs a shapes.Point object with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance from this point to another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double deltaX = this.x - other.getX();
        double deltaY = this.y - other.getY();
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Checks if this point is equal to another point within a threshold.
     *
     * @param other the other point
     * @return true if the points are equal within the threshold, false otherwise
     */
    public boolean equals(Point other) {
        return (Math.abs(this.x - other.getX()) <= threshold && Math.abs(this.y - other.getY()) <= threshold);
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate of this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate of this point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns a string representation of this point in the format [x, y].
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "[" + this.x + "," + this.y + "]";
    }
}

