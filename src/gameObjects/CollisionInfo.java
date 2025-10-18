package gameObjects;

import shapes.Point;

/**
 * Author: Almog Salman
 * ID: 324079458
 * The {@code gameObjects.CollisionInfo} class holds information about a collision event.
 * It stores the collision point and the collidable object involved in the collision.
 *
 * @version 19.0.2
 * @since 2024-06-02
 */

public class CollisionInfo {

    /**
     * The point at which the collision occurred.
     */
    private Point collisionPoint;

    /**
     * The collidable object involved in the collision.
     */
    private Collidable collisionObject;

    /**
     * Constructs a {@code gameObjects.CollisionInfo} with the specified collision point and collidable object.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurred.
     *
     * @return the collision point
     */
    public Point getCollisionPoint() {
        return collisionPoint;
    }

    /**
     * Sets the point at which the collision occurred.
     *
     * @param collisionPoint the new collision point
     */
    public void setCollisionPoint(Point collisionPoint) {
        this.collisionPoint = collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }

    /**
     * Sets the collidable object involved in the collision.
     *
     * @param collisionObject the new collidable object
     */
    public void setCollisionObject(Collidable collisionObject) {
        this.collisionObject = collisionObject;
    }
}
