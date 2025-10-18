package listeners;

/**
 * The HitNotifier interface represents an object that can notify listeners about hit events.
 * Classes that implement this interface can add or remove listeners for hit events.
 *
 * @version 1.0
 * @since 2024-06-02
 *
 * Author: Almog Salman
 * ID: 324079458
 */
public interface HitNotifier {

    /**
     * Adds a HitListener to the list of listeners for hit events.
     *
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners for hit events.
     *
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);
}

