package controllers;

import gameObjects.Collidable;
import shapes.Line;
import shapes.Point;
import gameObjects.CollisionInfo;
import java.util.List;
import java.util.ArrayList;

/**
 * The {@code controllers.GameEnvironment} class represents the environment in which the game objects move.
 * It maintains a list of collidable objects and provides methods to manage and check for collisions.
 *
 * @version 19.0.2
 * @since 2024-06-02
 * Author: Almog Salman
 * ID: 324079458
 */
public class GameEnvironment {

    /**
     * The list of collidable objects in the environment.
     */
    private List<Collidable> collidables;

    /**
     * Constructs a {@code controllers.GameEnvironment} with the specified list of collidable objects.
     *
     * @param collidables the list of collidable objects
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Constructs an empty {@code controllers.GameEnvironment}.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }

    /**
     * Gets the list of collidable objects in the environment.
     *
     * @return the list of collidable objects
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Sets the list of collidable objects in the environment.
     *
     * @param collidables the new list of collidable objects
     */
    public void setCollidables(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * Adds the given collidable object to the environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Determines the closest collision that will occur if an object moves along the specified trajectory.
     * If there are no collisions, returns {@code null}. Otherwise, returns information about the closest collision.
     *
     * @param trajectory the trajectory of the moving object
     * @return information about the closest collision, or {@code null} if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollision = null;
        double closestDistance = Double.MAX_VALUE;

        for (Collidable c : collidables) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersection != null) {
                double distance = trajectory.start().distance(intersection);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestCollision = new CollisionInfo(intersection, c);
                }
            }
        }

        return closestCollision;
    }

    /**
     * Sets the list of collidable objects in the environment.
     *
     * @param list the new list of collidable objects
     */
    public void setCollidable(List<Collidable> list) {
        this.collidables = list;
    }
}
