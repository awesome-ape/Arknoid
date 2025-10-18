package gameObjects;

import java.awt.Color;
import biuoop.DrawSurface;
import shapes.Line;
import shapes.Point;
import shapes.Velocity;
import  controllers.GameEnvironment;
import controllers.Game;;
/**
 * The gameObjects.Ball class represents a ball with a center point, radius, color, and velocity.
 * It provides methods to draw the ball, set and get its velocity, and move it.
 * The ball interacts with the game environment, including collisions.
 *
 * @version "19.0.2"
 * @since 2024-07-09
 *
 * @author Almog Salman
 * ID:  324079458
 */
public class Ball implements Sprite {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a gameObjects.Ball with the specified center point, radius, color, and game environment.
     *
     * @param center          the center point of the ball
     * @param radius          the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment in which the ball moves
     */
    public Ball(Point center, int radius, Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets the size (radius) of the ball.
     *
     * @return the radius of the ball
     */
    public double getSize() {
        return this.radius;
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step, considering collisions with the game environment.
     */
    public void moveOneStep() {
        Line trajectory = this.calculateTrajectory();
        CollisionInfo hit = this.gameEnvironment.getClosestCollision(trajectory);

        if (hit != null) {
            // Calculate the new velocity after hitting an object
            Velocity newVelocity = hit.getCollisionObject().hit(hit.getCollisionPoint(), this.velocity, this);
            // Adjust the position to be slightly away from the collision point
            this.center = this.adjustPosition(hit.getCollisionPoint(), this.velocity);
            this.velocity = newVelocity;
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }

        checkScreenBoundaries();
    }

    /**
     * Adjusts the position of the ball after a collision to prevent sticking.
     *
     * @param collisionPoint the point of collision
     * @param velocity       the current velocity of the ball
     * @return the adjusted position of the ball
     */
    public Point adjustPosition(Point collisionPoint, Velocity velocity) {
        double adjustmentFactor = this.radius; // Adjust slightly more than the radius to prevent sticking

        double adjustedX = collisionPoint.getX();
        double adjustedY = collisionPoint.getY();

        if (velocity.getDx() < 0) {
            adjustedX += adjustmentFactor;
        } else if (velocity.getDx() > 0) {
            adjustedX -= adjustmentFactor;
        }

        if (velocity.getDy() < 0) {
            adjustedY += adjustmentFactor;
        } else if (velocity.getDy() > 0) {
            adjustedY -= adjustmentFactor;
        }
        return new Point(adjustedX, adjustedY);
    }

    /**
     * Checks and handles collisions with the screen boundaries.
     */
    private void checkScreenBoundaries() {
        // Check left boundary
        if (this.center.getX() - this.radius < 0) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy(), 0);
            this.center = new Point(this.radius, this.center.getY());
        }
        // Check right boundary
        if (this.center.getX() + this.radius > SCREEN_WIDTH) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy(), 0);
            this.center = new Point(SCREEN_WIDTH - this.radius, this.center.getY());
        }

        // Check top boundary
        if (this.center.getY() - this.radius < 0) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy(), 0);
            this.center = new Point(this.center.getX(), this.radius);
        }
        // Check bottom boundary
        if (this.center.getY() + this.radius > SCREEN_HEIGHT) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy(), 0);
            this.center = new Point(this.center.getX(), SCREEN_HEIGHT - this.radius);
        }
    }

    /**
     * Calculates the trajectory of the ball based on its current velocity.
     *
     * @return the trajectory of the ball
     */
    public Line calculateTrajectory() {
        Point endPoint = getVelocity().applyToPoint(center);
        return new Line(center, endPoint);
    }

    /**
     * Draws the ball on the provided draw surface.
     *
     * @param surface the surface on which to draw the ball
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * Notifies the ball that time has passed, causing it to move one step.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Gets the type of the sprite.
     *
     * @return the type of the sprite, which is "ball" for this class
     */
    public String getType() {
        return "ball";
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     *
     * @param c the new color of the ball
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Removes the ball from the game.
     *
     * @param game the game from which to remove the ball
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}
