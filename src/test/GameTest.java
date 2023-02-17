import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class GameTest.
 *
 * @author  Maksym Turkot
 * @version 05/22/2021
 */
public class GameTest {
    Game game;
    City city;
    
    /**
     * Default constructor for test class GameTest
     */
    public GameTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        this.game = new Game("map1-example", 1);
        this.city = new City("New York", 'Y', 0, 3, 1, 2);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        this.game = null;
        this.city = null;
    }
    
    /**
     * Checks if deck is being properly shuffled.
     */
    @Test
    public void shuffleDeckTest() {
        System.out.println("***GameTest.shuffleDeckTest() START***");
        
        System.out.println("// Base Deck:");
        System.out.println(this.game.getBaseDeck().toString());
        System.out.println("// Player Deck:");
        System.out.println(this.game.getShuffledCityDeck().toString());
        System.out.println("// Infection Deck:");
        System.out.println(this.game.getShuffledInfectionDeck().toString());
        
        System.out.println("***GameTest.shuffleDeckTest()   END***" + "\n");
    }
    
    /**
     * Tests if infect(char, int) correctly infects the city.
     */
    @Test
    public void infectTest() {
        System.out.println("***GameTest.infectTest() START***");
        
        assertEquals(0, city.getInfectionY());
        assertEquals(3, city.getInfectionR());
        assertEquals(1, city.getInfectionB());
        assertEquals(2, city.getInfectionG());
        
        this.game.infect(city, 'G', 1);
        
        assertEquals(0, city.getInfectionY());
        assertEquals(3, city.getInfectionR());
        assertEquals(1, city.getInfectionB());
        assertEquals(3, city.getInfectionG());
        
        this.game.infect(city, 'R', 2);
        
        assertEquals(0, city.getInfectionY());
        assertEquals(3, city.getInfectionR());
        assertEquals(1, city.getInfectionB());
        assertEquals(3, city.getInfectionG());
        
        this.game.infect(city, 'H', 1);  // Thorws error
        this.game.infect(city, 'B', 10); // Throws error
        
        System.out.println("***GameTest.infectTest()   END***" + "\n");
    }
    
    /**
     * Tests if program correctly deals cards.
     */
    @Test
    public void dealCardsTest() {
        System.out.println("***GameTest.dealCardsTest() START***");
        
        System.out.println(game.getPawns().get(0).getCards().toString());
        game.dealCards(game.getPawns().get(0));
        System.out.println(game.getPawns().get(0).getCards().toString());
        
        System.out.println("***GameTest.dealCardsTest()   END***"  + "\n");
    }
}
