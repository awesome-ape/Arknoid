package gameObjects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import biuoop.GUI;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import controllers.Game;
import shapes.Velocity;
/**
 * The gameObjects.Paddle class represents the paddle in the game controlled by the player.
 * It implements the gameObjects.Sprite and gameObjects.Collidable interfaces, providing methods to move
 * the paddle, handle collisions, and draw the paddle on a surface.
 *
 * @version "19.0.2"
 * @since 2024-06-02
 *
 * Author: Almog Salman
 * ID: 324079458
 */
public class Paddle implements Sprite, Collidable {
    public static final int SCREEN_WIDTH = 800;
    public static final int WIDTH_OF_BLOCKS_ON_THE_SIDE = 20;
    private biuoop.GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private double speed;
    private Block block;
    private int flag;

    /**
     * Constructs a gameObjects.Paddle with the specified GUI, speed, and block.
     *
     * @param gui   the GUI object for the game
     * @param speed the speed of the paddle
     * @param block the block representing the paddle
     */
    public Paddle(GUI gui, double speed, Block block) {
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = speed;
        this.block = block;
        this.flag = 0;
        this.block.setRemovable();
    }

    /**
     * Moves the paddle to the left.
     * If the paddle moves out of bounds, it wraps around to the right side of the screen.
     */
    public void moveLeft() {
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            double newX = this.block.getCollisionRectangle().getUpperLeft().getX() - this.speed;
            if (newX < WIDTH_OF_BLOCKS_ON_THE_SIDE) {
                newX = SCREEN_WIDTH - WIDTH_OF_BLOCKS_ON_THE_SIDE - this.block.getWidth();
            }
            Rectangle newRec = new Rectangle(new Point(newX, this.block.getCollisionRectangle().getUpperLeft().getY()),
                    this.block.getWidth(), this.block.getHeight());
            Color c = this.block.getColor();
            Block block = new Block(newRec, c);
            this.block = block;
        }
    }

    /**
     * Moves the paddle to the right.
     * If the paddle moves out of bounds, it wraps around to the left side of the screen.
     */
    public void moveRight() {
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            double newX = this.block.getCollisionRectangle().getUpperLeft().getX() + speed;
            if (newX + this.block.getWidth() > SCREEN_WIDTH - WIDTH_OF_BLOCKS_ON_THE_SIDE) {
                newX = WIDTH_OF_BLOCKS_ON_THE_SIDE;
            }
            Rectangle newRec = new Rectangle(new Point(newX, this.block.getCollisionRectangle().getUpperLeft().getY()),
                    this.block.getWidth(), this.block.getHeight());
            Color c = this.block.getColor();
            Block block = new Block(newRec, c);
            this.block = block;
        }
    }

    /**
     * Notifies the paddle that time has passed, causing it to move left or right based on keyboard input.
     */
    @Override
    public void timePassed() {
        this.moveLeft();
        this.moveRight();
    }

    /**
     * Draws the paddle on the provided draw surface.
     *
     * @param d the surface on which to draw the paddle
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.block.drawOn(d);
    }

    /**
     * Gets the collision rectangle of the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
    }

    /**
     * Notifies the paddle that a collision occurred at the given point with the specified velocity.
     * Adjusts the velocity after the hit.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the velocity before the collision
     * @param hitter          the ball that hit the paddle
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        double section = this.block.getCollisionRectangle().getWidth() * 0.2;
        double speed = currentVelocity.getSpeed();
        // Divide the upper side of the paddle into five sections
        Line part1 = new Line(this.block.getUpperLeft(),
                new Point(this.block.getUpperLeft().getX() + section, this.block.getUpperLeft().getY()));
        Line part2 = new Line(part1.end(), new Point(part1.end().getX() + section, part1.end().getY()));
        Line part3 = new Line(part2.end(), new Point(part2.end().getX() + section, part2.end().getY()));
        Line part4 = new Line(part3.end(), new Point(part3.end().getX() + section, part3.end().getY()));
        Line part5 = new Line(part4.end(), this.block.getCollisionRectangle().getUpperRight());

        // Calculate the new velocity based on the collision point
        if (this.block.getCollisionRectangle().getLeft().isContaining(collisionPoint)) {
            this.flag = 1;
            return new Velocity(300, currentVelocity.getSpeed());
        }
        if (part1.belongsTo(collisionPoint)) {
            return new Velocity(300, currentVelocity.getSpeed());
        }
        if (part2.belongsTo(collisionPoint)) {
            return new Velocity(330, currentVelocity.getSpeed());
        }
        if (part3.belongsTo(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy(), 0);
        }
        if (part4.belongsTo(collisionPoint)) {
            return new Velocity(30, currentVelocity.getSpeed());
        }
        if (this.block.getCollisionRectangle().getRight().isContaining(collisionPoint)) {
            this.flag = 1;
            return new Velocity(60, currentVelocity.getSpeed());
        }
        if (part5.belongsTo(collisionPoint)) {
            return new Velocity(60, currentVelocity.getSpeed());
        }

        // Default return statement to avoid a bug where the ball gets a null velocity and crashes the game
        return currentVelocity;
    }

    /**
     * Adds this paddle to the game.
     *
     * @param g the game to which the paddle will be added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Gets the type of the sprite.
     *
     * @return the type of the sprite, which is "paddle" for this class
     */
    public String getType() {
        return "paddle";
    }
}
