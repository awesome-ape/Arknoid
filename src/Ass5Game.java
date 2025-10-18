import controllers.Game;

/**
 * Author: Almog Salman
 * ID: 324079458
 * The {@code LineTest} class contains the main method
 * to start and run the controllers.Game application.
 */
public class Ass5Game {

    /**
     * The main method to start the controllers.Game application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

