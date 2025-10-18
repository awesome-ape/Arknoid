package listeners;
import gameObjects.Block;
 import gameObjects.Ball;
import controllers.Counter;
import controllers.Game;

/**
 * The listeners.BallRemover class is a listeners.HitListener that removes balls from the game when they hit a block.
 * It also updates the ball counter.
 *
 * @version "19.0.2"
 * @since 2024-07-09
 *
 * @author Almog Salman
 * ID: 324079458
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter ballsCounter;

    /**
     * Constructs a listeners.BallRemover with the specified game and ball counter.
     *
     * @param game     the game from which balls will be removed
     * @param counter  the counter tracking the number of balls
     */
    public BallRemover(Game game, Counter counter) {
        this.ballsCounter = counter;
        this.game = game;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It removes the ball from the game and decreases the ball counter.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        this.ballsCounter.decrease(1);
    }
}
