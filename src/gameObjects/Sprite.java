package gameObjects;

import biuoop.DrawSurface;

/**
 * The {@code gameObjects.Sprite} interface represents a drawable object in the game.
 * Implementing classes must provide methods to draw the sprite on the screen
 * and to update the sprite as time progresses.
 * Author: Almog Salman
 * ID: 324079458
 */
public interface Sprite {

    /**
     * Draws the sprite on the given {@code DrawSurface}.
     *
     * @param d the {@code DrawSurface} to draw the sprite on
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method is called to update the sprite's state as time progresses.
     */
    void timePassed();

    /**
     * Gets the type of the sprite.
     *
     * @return the type of the sprite as a {@code String}
     */
    String getType();
}
