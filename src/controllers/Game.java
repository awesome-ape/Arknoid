package controllers;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.List;
import gameObjects.Ball;
import gameObjects.Block;
import gameObjects.Paddle;
import gameObjects.SpriteCollection;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import gameObjects.Collidable;
import gameObjects.Sprite;
import java.awt.Color;
/**
 * The controllers.Game class represents the Arkanoid game.
 * It initializes the game objects, handles game logic, and runs the game loop.
 *
 * @version 1.0
 * @since 2024-06-02
 *
 * @author Almog Salman
 * id 324079458
 */
public class Game {
    public static final int START_Y = 100; // Adjusted to fit within the window
    public static final int START_X = 730; // Starting X position
    public static final int DELTA_X = -50;
    public static final int DELTA_Y = 20;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Paddle paddle;
    private Counter numOfBlocks;
    private BlockRemover blockRemover;
    private Counter numOfBalls;
    private BallRemover br;
    private ScoreTrackingListener stl;
    private Counter score;

    /**
     * Constructs a new controllers.Game object.
     * Initializes the sprite collection, game environment, and GUI.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", 800, 600);
        this.numOfBlocks = new Counter(57);
        this.numOfBalls = new Counter(3);
        this.score = new Counter(0);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Adds a block to the game as both a collidable and a sprite.
     *
     * @param block the block to add
     */
    public void addToGame(Block block) {
        this.addCollidable(block);
        this.addSprite(block);
    }

    /**
     * Adds a ball to the game as a sprite.
     *
     * @param ball the ball to add
     */
    public void addToGame(Ball ball) {
        this.addSprite(ball);
    }

    /**
     * Adds a paddle to the game as both a collidable and a sprite.
     *
     * @param paddle the paddle to add
     */
    public void addToGame(Paddle paddle) {
        this.addCollidable(paddle);
        this.addSprite(paddle);
    }

    /**
     * Initializes a new game: creates the blocks, balls, and paddle, and adds them to the game.
     */
    public void initialize() {
        Block topWall = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        Block bottomWall = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        Block leftWall = new Block(new Rectangle(new Point(0, 20), 20, 560), Color.GRAY);
        Block rightWall = new Block(new Rectangle(new Point(780, 20), 20, 560), Color.GRAY);
        this.br = new BallRemover(this, numOfBalls);
        bottomWall.addHitListener(br);
        addToGame(topWall);
        addToGame(bottomWall);
        addToGame(leftWall);
        addToGame(rightWall);

        int n = 12;
        Color c = Color.GRAY;

        for (int i = 0; i < 6; i++) {
            switch (n) {
                case 12:
                    c = Color.DARK_GRAY;
                    break;
                case 11:
                    c = Color.RED;
                    break;
                case 10:
                    c = Color.YELLOW;
                    break;
                case 9:
                    c = Color.BLUE;
                    break;
                case 8:
                    c = Color.PINK;
                    break;
                case 7:
                    c = Color.GREEN;
                    break;
                default:
                    c = Color.GRAY;
                    break;
            }
            this.blockRemover = new BlockRemover(this, numOfBlocks);
            this.stl = new ScoreTrackingListener(this.score);
            for (int j = 0; j < n; j++) {
                Block block1 = new Block(new Rectangle(new Point(START_X + DELTA_X * j, START_Y + DELTA_Y * i),
                        50, 20), c);
                block1.addHitListener(blockRemover);
                block1.addHitListener(stl);
                addToGame(block1);
            }
            n--;
        }

        this.paddle = new Paddle(gui, 10,
                new Block(new Rectangle(new Point(350, 560),
                        100, 20), Color.GREEN));
        addToGame(paddle);

        Ball ball = new Ball(new Point(100, 100), 5, Color.CYAN, environment);
        ball.setVelocity(new Velocity(7, 6, 0));
        addToGame(ball);
        Ball ball2 = new Ball(new Point(200, 200), 5, Color.GREEN,
                environment);
        ball2.setVelocity(new Velocity(7, 6, 0));
        addToGame(ball2);
        Ball ball3 = new Ball(new Point(70, 200), 5, Color.MAGENTA,
                environment);
        ball3.setVelocity(new Velocity(8, 6, 0));
        addToGame(ball3);
    }

    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            // Set background color
            d.setColor(Color.BLUE.darker().darker());
            d.fillRectangle(0, 0, 800, 600);

            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (this.numOfBlocks.getValue() == 0 || this.numOfBalls.getValue() == 0) {
                if (this.numOfBlocks.getValue() == 0) {
                    this.score.increase(100);
                }
                d = gui.getDrawSurface();
                d.setColor(Color.BLUE.darker().darker());
                d.fillRectangle(0, 0, 800, 600);
                this.sprites.drawAllOn(d);
                d.setColor(Color.WHITE);
                d.drawText(350, 300, " " + this.score.fullString(), 32);
                gui.show(d);
                sleeper.sleepFor(2000); // Display the final frame for 2 seconds
                gui.close();
                return;
            }
        }
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        List<Collidable> copy = this.environment.getCollidables();
        for (int i = 0; i < copy.size(); i++) {
            Collidable collidable = copy.get(i);
            if (collidable.getCollisionRectangle().getUpperLeft().equals(c.getCollisionRectangle().getUpperLeft())) {
                copy.remove(i);
                break;
            }
        }
        this.environment.setCollidables(copy);
    }

    /**
     * Removes a sprite object from the game.
     *
     * @param s the sprite object to remove
     */
    public void removeSprite(Sprite s) {
        List<Sprite> copy = this.sprites.getSprites();
        String type = s.getType();
        for (int i = 0; i < copy.size(); i++) {
            Sprite sprite = copy.get(i);
            if (sprite.getType().equals("ball") && type.equals("ball")) {
                if (((Ball) sprite).getCenter().equals(((Ball) s).getCenter())) {
                    copy.remove(i);
                    break; // Exit loop once the sprite is removed
                }
            }
            if (sprite.getType().equals("block") && type.equals("block")) {
                if (((Block) sprite).isRemovable()) {
                    if (((Block) sprite).getCollisionRectangle().getUpperLeft().equals(((Block) s)
                            .getCollisionRectangle().getUpperLeft())) {
                        copy.remove(i);
                        break; // Exit loop once the sprite is removed
                    }
                }
            }
        }
        this.sprites.setSprites(copy);
    }
}
