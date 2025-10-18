package listeners;

import gameObjects.Ball;
import controllers.Counter;
import gameObjects.Block;
/**
 * The {@code listeners.ScoreTrackingListener} class implements the {@code listeners.HitListener} interface
 * to update the game score when a block is hit by a ball.
 *
 * @version "19.0.2"
 * @since 2024-06-02
 * Author: Almog Salman
 * ID: 324079458
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a {@code listeners.ScoreTrackingListener} with the specified score counter.
     *
     * @param scoreCounter the counter that tracks the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It increases the score by 5 points and removes the listener from the block.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}

