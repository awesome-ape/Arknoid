package listeners;

import gameObjects.Ball;
import gameObjects.Block;

/**
 * The HitListener interface represents an object that listens for hit events.
 * Classes that implement this interface can be notified when a block is hit by a ball.
 *
 * @version 1.0
 * @since 2024-06-02
 *
 * Author: Almog Salman
 * ID: 324079458
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the block that is being hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}
