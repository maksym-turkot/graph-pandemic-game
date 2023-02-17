import java.util.Random;

/**
 * This class generates random values.
 *
 * @author Maksym Turkot
 * @version 05/13/2021
 */
public class Rand {
    private Random rand;
    
    /**
     * Constructor for objects of class Random
     */
    public Rand(int seed) {
        rand = new Random(seed);
    }
    
    /**
     * Returns a random int value.
     * 
     * @return random int.
     */ 
    public int getRand(int upperbound) {
        return rand.nextInt(upperbound);
    }
}
