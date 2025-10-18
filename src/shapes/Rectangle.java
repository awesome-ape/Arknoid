package shapes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Almog Salman
 * ID: 324079458
 * The shapes.Rectangle class represents a rectangle defined by its upper left corner point, width, and height.
 * It provides methods to get the sides of the rectangle, check if a point belongs to the rectangle,
 * and find intersection points with a given line.
 *
 * @version "19.0.2"
 * @since 2024-06-02
 */
public class Rectangle {
    private Point lowerLeft;
    private Point lowerRight;
    private Point upperRight;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a shapes.Rectangle with the specified upper left corner point, width, and height.
     *
     * @param upperLeft the upper left corner point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Gets the left side of the rectangle.
     *
     * @return the left side of the rectangle
     */
    public Line getLeft() {
        return new Line(this.upperLeft, this.lowerLeft);
    }

    /**
     * Gets the right side of the rectangle.
     *
     * @return the right side of the rectangle
     */
    public Line getRight() {
        return new Line(this.upperRight, this.lowerRight);
    }

    /**
     * Gets the top side of the rectangle.
     *
     * @return the top side of the rectangle
     */
    public Line getUp() {
        return new Line(this.upperLeft, this.upperRight);
    }

    /**
     * Gets the bottom side of the rectangle.
     *
     * @return the bottom side of the rectangle
     */
    public Line getDown() {
        return new Line(this.lowerLeft, this.lowerRight);
    }

    /**
     * Checks if a point belongs to any side of the rectangle.
     *
     * @param point the point to check
     * @return true if the point belongs to any side of the rectangle, false otherwise
     */
    public boolean belongsTo(Point point) {
        return this.getUp().isContaining(point)
                || this.getDown().isContaining(point)
                || this.getLeft().isContaining(point)
                || this.getRight().isContaining(point);
    }

    /**
     * Gets the upper right corner point of the rectangle.
     *
     * @return the upper right corner point of the rectangle
     */
    public Point getUpperRight() {
        return upperRight;
    }

    /**
     * Finds the intersection points of the rectangle with a given line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points with the given line
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        List<Line> sides = Arrays.asList(getLeft(), getRight(), getUp(), getDown());

        for (Line side : sides) {
            Point intersection = side.intersectionWith(line);
            if (intersection != null) {
                intersections.add(intersection);
            }
        }
        return intersections;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the upper left corner point of the rectangle.
     *
     * @return the upper left corner point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Checks if a point is contained within the rectangle.
     *
     * @param point the point to check
     * @return true if the point is contained within the rectangle, false otherwise
     */
    public boolean contains(Point point) {
        return this.lowerLeft.getX() <= point.getX() && point.getX() <= this.lowerRight.getX()
                && this.upperLeft.getY() <= point.getY() && point.getY() <= this.lowerLeft.getY();
    }
}
