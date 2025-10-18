package shapes;

/**
 * Author: Almog Salman, Dor Darmon
 *  ID: 324079458, 207934902
 * The shapes.Velocity class represents a velocity with components dx and dy.
 * It provides methods to retrieve the components, calculate the speed,
 * create a velocity from angle and speed, and apply the velocity to a point.
 * It also provides a method to update the angle and speed when the components change.
 *
 * @version "19.0.2"
 * @since 2024-06-02
 */
public class Velocity {
    private double dx;
    private double dy;
    private double speed;
    private double angle;
    private int flag;

    /**
     * Constructs a shapes.Velocity object with the given dx and dy components.
     * It calculates the speed and angle based on the dx and dy components.
     *
     * @param dx the x component of the velocity
     * @param dy the y component of the velocity
     * @param flag an additional parameter, purpose not defined in this context
     */
    public Velocity(double dx, double dy, int flag) {
        this.dx = dx;
        this.dy = dy;
        this.flag = flag;
        this.updateAngleAndSpeed();
    }

    /**
     * Constructs a shapes.Velocity object with the given angle and speed.
     * It calculates the dx and dy components based on the angle and speed.
     *
     * @param angle the angle of the velocity in degrees
     * @param speed the speed of the velocity
     */
    public Velocity(double angle, double speed) {
        this.angle = angle;
        this.speed = speed;
        this.flag = 0; // Default value for flag
        this.updateDxAndDy();
    }

    /**
     * Returns the flag.
     *
     * @return the flag
     */
    public int getFlag() {
        return this.flag;
    }

    /**
     * Sets the flag.
     *
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * Returns the x component of the velocity.
     *
     * @return the dx component
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Returns the y component of the velocity.
     *
     * @return the dy component
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Returns the speed of the velocity.
     *
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Returns the angle of the velocity in degrees.
     *
     * @return the angle of the velocity
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Creates a new shapes.Velocity object from the given angle and speed.
     * The angle should be provided in degrees.
     *
     * @param angle the angle in degrees
     * @param speed the speed of the velocity
     * @return a new shapes.Velocity object with the calculated dx and dy components
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRadians = Math.toRadians(angle);
        double dx = speed * Math.sin(angleRadians);
        double dy = -speed * Math.cos(angleRadians);
        return new Velocity(dx, dy, 0);
    }

    /**
     * Applies the velocity to a given point and returns a new point
     * with the updated position.
     *
     * @param p the point to which the velocity will be applied
     * @return a new shapes.Point object with the updated position
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + dx;
        double newY = p.getY() + dy;
        return new Point(newX, newY);
    }

    /**
     * Sets the x component of the velocity.
     * It also updates the speed and angle based on the new component values.
     *
     * @param dx the new x component of the velocity
     */
    public void setDx(double dx) {
        this.dx = dx;
        this.updateAngleAndSpeed();
    }

    /**
     * Sets the y component of the velocity.
     * It also updates the speed and angle based on the new component values.
     *
     * @param dy the new y component of the velocity
     */
    public void setDy(double dy) {
        this.dy = dy;
        this.updateAngleAndSpeed();
    }

    /**
     * Sets the speed of the velocity.
     * It also updates the dx and dy components based on the new speed and current angle.
     *
     * @param speed the new speed of the velocity
     */
    public void setSpeed(double speed) {
        this.speed = speed;
        this.updateDxAndDy();
    }

    /**
     * Sets the angle of the velocity.
     * It also updates the dx and dy components based on the new angle and current speed.
     *
     * @param angle the new angle of the velocity
     */
    public void setAngle(double angle) {
        this.angle = angle;
        this.updateDxAndDy();
    }

    /**
     * Updates the dx and dy components based on the current speed and angle.
     */
    private void updateDxAndDy() {
        double angleRadians = Math.toRadians(this.angle);
        this.dx = this.speed * Math.sin(angleRadians);
        this.dy = -this.speed * Math.cos(angleRadians);
    }

    /**
     * Updates the speed and angle based on the current dx and dy components.
     */
    private void updateAngleAndSpeed() {
        this.speed = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        this.angle = Math.toDegrees(Math.atan2(this.dy, this.dx));
    }

    /**
     * Returns a string representation of the velocity.
     *
     * @return a string representation of the velocity
     */
    @Override
    public String toString() {
        return "dx: " + this.dx + ", dy: " + this.dy + ", speed: " + this.speed + ", angle: " + this.angle;
    }
}
