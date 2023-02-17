/**
 * This class runs games on loaded maps,
 * as well as randomly generated maps.
 *
 * @author Maksym Turkot
 * @version 05/26/2021
 */
public class Controller {
    /**
     * Constructor for objects of class Controller
     */
    public Controller() {
    }

    /**
     * Runs games with different seeds and map files.
     */
    public static void main() {
        String[] mapFiles = {"map1-example", "map2-sparse", "map3-sparse", "map4-dense", "map5-dense"};
        Integer[] seeds = {1, 2, 3, 4, 5};

        // Run each loaded map
        for (String mapFile : mapFiles) {

            // Run each random seed
            for (int seed : seeds) {
                Game game = new Game(mapFile, seed);
                game.runGame();
            }
        }
    }
}
