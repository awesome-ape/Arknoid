package controllers;

/**
 * The controllers.Counter class provides a simple counter with methods to increase, decrease,
 * get, and set the counter's value. It also provides a method to get a string representation
 * of the counter's value with a prefix "Score:".
 *
 * @version "19.0.2"
 * @since 2024-07-08
 *
 * @author Almog Salman
 * id 324079458
 */
public class Counter {
    private int value;

    /**
     * Creates a new controllers.Counter with the specified starting value.
     *
     * @param startingVal the starting value of the counter
     */
    public Counter(int startingVal) {
        this.value = startingVal;
    }

    /**
     * Increases the counter by the specified number.
     *
     * @param number the number to increase the counter by
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * Decreases the counter by the specified number.
     *
     * @param number the number to decrease the counter by
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * Returns the current value of the counter.
     *
     * @return the current value of the counter
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the value of the counter to the specified value.
     *
     * @param val the value to set the counter to
     */
    public void setValue(int val) {
        this.value = val;
    }

    /**
     * Returns a string representation of the counter's value with the prefix "Score:".
     *
     * @return a string representing the counter's value with the prefix "Score:"
     */
    public String fullString() {
        return "Score: " + this.getValue();
    }
}
