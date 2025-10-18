package listeners;
import gameObjects.Ball;
import gameObjects.Block;
import controllers.Game;
import controllers.Counter;

/**
 * A listeners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @version "19.0.2"
 * @since 2024-07-08
 *
 * @author Almog Salman
 * id 324079458
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a listeners.BlockRemover with the specified game and counter for remaining blocks.
     *
     * @param game            the game from which blocks will be removed
     * @param remainingBlocks the counter tracking the number of remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Blocks that are hit and are removable should be removed from the game.
     * Also removes this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that was hit
     * @param hitter   the ball that hit the block
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.isRemovable()) {
            hitter.setColor(beingHit.getColor());
            beingHit.removeFromGame(game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}

