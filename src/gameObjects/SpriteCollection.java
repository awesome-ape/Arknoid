package gameObjects;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code gameObjects.SpriteCollection} class manages a collection of gameObjects.Sprite objects.
 * It provides methods to add sprites to the collection, notify all sprites
 * that time has passed, and draw all sprites on a given DrawSurface.
 * Author: Almog Salman
 * ID: 324079458
 *
 * @version "19.0.2"
 * @since 2024-06-02
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a {@code gameObjects.SpriteCollection} object and initializes the list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param sprite the sprite to add to the collection
     */
    public void addSprite(Sprite sprite) {
        this.sprites.add(sprite);
    }

    /**
     * Calls the {@code timePassed} method on all sprites in the collection.
     * This method is typically used to update the state of all sprites as time progresses.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the {@code drawOn} method on all sprites in the collection.
     * This method is typically used to draw all sprites on a given {@code DrawSurface}.
     *
     * @param d the {@code DrawSurface} on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * Sets the list of sprites in the collection.
     *
     * @param list the new list of sprites
     */
    public void setSprites(List<Sprite> list) {
        this.sprites = list;
    }

    /**
     * Gets the list of sprites in the collection.
     *
     * @return the list of sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
}
