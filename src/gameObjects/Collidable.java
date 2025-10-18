package gameObjects;

import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
/**
 * Author: Almog Salman
 *  ID: 324079458
 * The {@code gameObjects.Collidable} interface represents an object that can be collided with.
 * Implementing classes must provide methods to get the collision shape and
 * to handle the collision event.
 */

public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     *
     * @return the collision shape as a {@code shapes.Rectangle}
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that it has been collided with at the specified collision point
     * with the given velocity. The method calculates the new velocity expected after the hit,
     * based on the force the object inflicted.
     *
     * @param collisionPoint   the point at which the collision occurred
     * @param currentVelocity  the velocity at the time of collision
     * @param hitter  the velocity at the time of collision
     * @return the new velocity expected after the hit
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}
