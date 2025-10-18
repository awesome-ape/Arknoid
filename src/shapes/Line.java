package shapes;

import java.util.List;

/**
 *  Author: Almog Salman
 *  ID: 324079458
 * The shapes.Line class represents a line segment defined by two points.
 * It includes methods to calculate the length, midpoint, incline,
 * and other properties of the line, as well as methods to check
 * for intersections with other lines.
 *
 * @version "19.0.2"
 * @since 2024-06-02
 */
public class Line {
    private Point start;
    private Point end;
    private static final double THRESHOLD = 0.001;

    /**
     * Constructs a shapes.Line with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a shapes.Line with the specified coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the midpoint of the line.
     *
     * @return the midpoint of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Returns the incline (slope) of the line.
     *
     * @return the incline of the line
     */
    public double incline() {
        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY; // Vertical line
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }

    /**
     * Calculates the y-intercept (b) of the line.
     *
     * @return the y-intercept of the line
     */
    public double findB() {
        double incline = this.incline();
        if (incline == Double.POSITIVE_INFINITY) {
            return Double.NaN;
        }
        return this.start.getY() - incline * this.start.getX();
    }

    /**
     * Checks if the line is vertical to the X axis.
     *
     * @return true if the line is vertical to the X axis, false otherwise
     */
    public boolean isVerticalToAxisX() {
        return Math.abs(this.start.getX() - this.end.getX()) < THRESHOLD;
    }

    /**
     * Checks if the line is vertical to the Y axis.
     *
     * @return true if the line is vertical to the Y axis, false otherwise
     */
    public boolean isVerticalToAxisY() {
        return Math.abs(this.start.getY() - this.end.getY()) < THRESHOLD;
    }

    /**
     * Checks if a point is within the range of the line segment.
     *
     * @param point the point to check
     * @return true if the point is within the range, false otherwise
     */
    public boolean inRange(Point point) {
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());
        return ((point.getX() - minX >= -THRESHOLD && maxX - point.getX() >= -THRESHOLD)
                && (point.getY() - minY >= -THRESHOLD && maxY - point.getY() >= -THRESHOLD));
    }

    /**
     * Checks if a point lies on the line.
     *
     * @param p the point to check
     * @return true if the point lies on the line, false otherwise
     */
    public boolean isThePointOnTheLine(Point p) {
        double incline = this.incline();
        double b = this.findB();
        if (Math.abs(incline * p.getX() + b - p.getY()) <= THRESHOLD) {
            if (this.inRange(p)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the intersection point with another line.
     *
     * @param other the other line
     * @return the intersection point, or null if there is no intersection
     */
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        // shapes.Line AB represented as a1x + b1y = c1
        double a1 = y2 - y1;
        double b1 = x1 - x2;
        double c1 = a1 * x1 + b1 * y1;

        // shapes.Line CD represented as a2x + b2y = c2
        double a2 = y4 - y3;
        double b2 = x3 - x4;
        double c2 = a2 * x3 + b2 * y3;

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            // Lines are parallel
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point intersection = new Point(x, y);

            // Check if the intersection point is on both line segments
            if (this.isContaining(intersection) && other.isContaining(intersection)) {
                return intersection;
            } else {
                return null;
            }
        }
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (other.isThePointOnTheLine(this.start) || other.isThePointOnTheLine(this.end)) {
            return true;
        }
        if (this.isThePointOnTheLine(other.start) || this.isThePointOnTheLine(other.end)) {
            return true;
        }
        if (!this.isVerticalToAxisX() && !this.isVerticalToAxisY()
                && !other.isVerticalToAxisX() && !other.isVerticalToAxisY()) {
            if (Math.abs(this.incline() - other.incline()) > THRESHOLD) {
                Point intersection = this.intersectionPointForRegularLines(other);
                if (intersection != null) {
                    return true;
                }
            }
        }
        if (this.isVerticalToAxisX() || this.isVerticalToAxisY()) {
            Point p1 = this.verticalLines(other);
            if (p1 != null) {
                return true;
            }
            return this.isContaining(other);
        }
        if (other.isVerticalToAxisX() || other.isVerticalToAxisY()) {
            Point p2 = other.verticalLines(this);
            if (p2 != null) {
                return true;
            }
            return this.isContaining(other);
        }
        return false;
    }

    /**
     * Checks if a line segment is within the range of another line segment.
     *
     * @param other the other line segment
     * @return true if the other line segment is within the range, false otherwise
     */
    public boolean isContaining(Line other) {
        if (Math.abs(this.incline() - other.incline()) >= THRESHOLD
                || Math.abs(this.findB() - other.findB()) >= THRESHOLD) {
            return false;
        }
        return ((this.inRange(other.start()) || this.inRange(other.end()))
                || (other.inRange(this.start()) || other.inRange(this.end())));
    }

    /**
     * Handles special cases where one or both lines are vertical or horizontal.
     *
     * @param other the other line
     * @return the intersection point, or null if there is no intersection
     */
    public Point verticalLines(Line other) {
        Line vertical = this;
        if (vertical.isVerticalToAxisX()) {
            if (other.isVerticalToAxisX()) {
                if (Math.abs(this.start.getX() - other.start().getX()) > THRESHOLD) {
                    return null;
                }
                return this.intersectionSameIncline(other);
            }
            if (other.isVerticalToAxisY()) {
                Point p = new Point(vertical.start().getX(), other.start().getY());
                if (other.inRange(p)) {
                    return p;
                }
                return null;
            }
            Point intersection2 = new Point(vertical.start().getX(),
                    other.incline() * vertical.start().getX() + other.findB());
            if (other.inRange(intersection2) && vertical.inRange(intersection2)) {
                return intersection2;
            }
            return null;
        }
        if (vertical.isVerticalToAxisY()) {
            if (other.isVerticalToAxisY()) {
                if (Math.abs(this.start.getY() - other.start().getY()) > THRESHOLD) {
                    return null;
                }
                return this.intersectionSameIncline(other);
            }
            if (other.isVerticalToAxisX()) {
                Point intersection3 = new Point(other.start().getX(), vertical.start().getY());
                if (other.inRange(intersection3) && vertical.inRange(intersection3)) {
                    return intersection3;
                }
                return null;
            }
            Point intersection4 = new Point((vertical.start().getY()
                    - other.findB()) / other.incline(), vertical.start().getY());
            if (vertical.inRange(intersection4) && other.inRange(intersection4)) {
                return intersection4;
            }
            return null;
        }
        return null;
    }

    /**
     * Finds the closest intersection point to the start of the line with a given rectangle.
     *
     * @param rec the rectangle to check for intersections
     * @return the closest intersection point, or null if there are no intersections
     */
    public Point closestIntersectionToStartOfLine(Rectangle rec) {
        List<Point> intersections = rec.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        double closestDistance = intersections.get(0).distance(this.start);
        Point closest = intersections.get(0);
        for (Point point : intersections) {
            if (point.distance(this.start) < closestDistance) {
                closestDistance = point.distance(this.start);
                closest = point;
            }
        }
        return closest;
    }

    /**
     * Finds the intersection point for lines with the same incline.
     *
     * @param other the other line
     * @return the intersection point, or null if there is no intersection
     */
    public Point intersectionSameIncline(Line other) {
        if (this.start.equals(other.start()) && !(this.inRange(other.end()) && !(other.inRange(this.end())))) {
            return this.start;
        }
        if (this.end.equals(other.end()) && !(this.inRange(other.start()) || other.inRange(this.start()))) {
            return this.end;
        }
        if (this.start.equals(other.end()) && !(this.inRange(other.start()) || other.inRange(this.end()))) {
            return this.start;
        }
        if (this.end.equals(other.start()) && !(this.inRange(other.end()) || other.inRange(this.start()))) {
            return this.end;
        }
        return null;
    }

    /**
     * Finds the intersection point for regular (non-vertical/non-horizontal) lines.
     *
     * @param other the other line
     * @return the intersection point, or null if there is no intersection
     */
    public Point intersectionPointForRegularLines(Line other) {
        if (this.start.equals(other.start())) {
            return this.start;
        }
        if (this.start.equals(other.end())) {
            return this.start;
        }
        if (this.end.equals(other.end())) {
            return this.end;
        }
        if (this.end.equals(other.start())) {
            return this.end;
        }
        double incline1 = this.incline();
        double incline2 = other.incline();
        double b1 = this.findB();
        double b2 = other.findB();
        double intersectionX = (b2 - b1) / (incline1 - incline2);
        double intersectionY = incline1 * intersectionX + b1;
        Point intersection = new Point(intersectionX, intersectionY);
        if (this.inRange(intersection) && other.inRange(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * Checks if a point lies on the line segment.
     *
     * @param p the point to check
     * @return true if the point lies on the line segment, false otherwise
     */
    public boolean isContaining(Point p) {
        if (this.isVerticalToAxisX()) {
            if (Math.abs(p.getX() - this.start.getX()) > THRESHOLD) {
                return false;
            }
            double maxY = Math.max(this.start.getY(), this.end.getY());
            double minY = Math.min(this.start.getY(), this.end.getY());
            return p.getY() >= minY - THRESHOLD && p.getY() <= maxY + THRESHOLD;
        }

        if (this.isVerticalToAxisY()) {
            if (Math.abs(p.getY() - this.start.getY()) > THRESHOLD) {
                return false;
            }
            double maxX = Math.max(this.start.getX(), this.end.getX());
            double minX = Math.min(this.start.getX(), this.end.getX());
            return p.getX() >= minX - THRESHOLD && p.getX() <= maxX + THRESHOLD;
        }

        double slope = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
        double intercept = this.start.getY() - slope * this.start.getX();
        double expectedY = slope * p.getX() + intercept;

        return Math.abs(p.getY() - expectedY) <= THRESHOLD
                && p.getX() >= Math.min(this.start.getX(), this.end.getX()) - THRESHOLD
                && p.getX() <= Math.max(this.start.getX(), this.end.getX()) + THRESHOLD
                && p.getY() >= Math.min(this.start.getY(), this.end.getY()) - THRESHOLD
                && p.getY() <= Math.max(this.start.getY(), this.end.getY()) + THRESHOLD;
    }

    /**
     * Checks if a point lies on the line.
     *
     * @param point the point to check
     * @return true if the point lies on the line, false otherwise
     */
    public boolean belongsTo(Point point) {
        double d1 = point.distance(this.start);
        double d2 = point.distance(this.end);
        double lineLength = this.start.distance(this.end);
        return Math.abs((d1 + d2) - lineLength) < THRESHOLD;
    }
}
