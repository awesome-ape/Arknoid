package gameObjects;

import biuoop.DrawSurface;
import listeners.HitListener;
import listeners.HitNotifier;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import controllers.Game;

/**
 * The gameObjects.Block class represents a block with a rectangular shape and a color.
 * It implements the gameObjects.Collidable and gameObjects.Sprite interfaces, providing methods to handle
 * collisions and draw the block on a surface.
 *
 * @version "19.0.2"
 * @since 2024-07-08
 *
 * @author Almog Salman
 * ID 324079458
 */
public class Block implements Collidable, Sprite, HitNotifier, HitListener {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;
    private boolean removable;

    /**
     * Constructs a gameObjects.Block with the specified rectangle and color.
     *
     * @param rectangle the rectangular shape of the block
     * @param color     the color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.removable = true;
        this.addHitListener(this);
    }

    /**
     * Gets the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notifies the block that a collision occurred at the given point with the specified velocity.
     * Adjusts the velocity after the hit.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity before the collision
     * @param hitter          the ball that hit the block
     * @return the new velocity after the collision
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        if (collisionPoint == null) {
            return currentVelocity;
        }

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (this.rectangle.getUp().isContaining(collisionPoint)
                || this.rectangle.getDown().isContaining(collisionPoint)) {
            dy = -dy;
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
        }
        if (this.rectangle.getLeft().isContaining(collisionPoint)
                || this.rectangle.getRight().isContaining(collisionPoint)) {
            dx = -dx;
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
        }
        return new Velocity(dx, dy, 0);
    }

    /**
     * Draws the block on the provided draw surface.
     *
     * @param surface the surface on which to draw the block
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();

        // Draw the filled rectangle
        surface.setColor(this.color);
        surface.fillRectangle(x, y, width, height);

        // Draw the border of the rectangle
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, width, height);
    }

    /**
     * Returns the width of the block.
     *
     * @return the width of the block
     */
    public double getWidth() {
        return this.rectangle.getWidth();
    }

    /**
     * Returns the height of the block.
     *
     * @return the height of the block
     */
    public double getHeight() {
        return this.rectangle.getHeight();
    }

    /**
     * Returns the upper left point of the block.
     *
     * @return the upper left point of the block
     */
    public Point getUpperLeft() {
        return this.rectangle.getUpperLeft();
    }

    /**
     * Returns the color of the block.
     *
     * @return the color of the block
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This method is called whenever time has passed.
     * Currently, it does nothing but is required by the gameObjects.Sprite interface.
     */
    @Override
    public void timePassed() {
        // No implementation needed for this class
    }

    /**
     * Gets the type of the sprite.
     *
     * @return the type of the sprite, which is "block" for this class
     */
    public String getType() {
        return "block";
    }

    /**
     * Checks if the block is removable.
     *
     * @return true if the block is removable, false otherwise
     */
    public boolean isRemovable() {
        return this.removable;
    }

    /**
     * Checks if the ball's color matches the block's color.
     *
     * @param ball the ball to check against
     * @return true if the colors match, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color.equals(ball.getColor());
    }

    /**
     * Removes the block from the game.
     *
     * @param game the game from which to remove the block
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Sets the block to be non-removable.
     */
    public void setRemovable() {
        this.removable = false;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Currently, it does nothing but is required by the listeners.HitListener interface.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // No implementation needed for this class
    }

    /**
     * Adds a hit listener to the block.
     *
     * @param hl the hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a hit listener from the block.
     *
     * @param hl the hit listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter the ball that hit the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
