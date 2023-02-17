import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

/**
 * The test class PawnTest.
 *
 * @author  Maksym Turkot
 * @version 05/13/2021
 */
public class PawnTest {
    Game game;
    Pawn pawn;
    TreeMap<String, City> cities;
    
    /**
     * Default constructor for test class PawnTest
     */
    public PawnTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
        this.game = new Game("map1-example", 1);
        this.pawn = new Pawn(new City("New York", 'Y', 0, 3, 1, 2), game);
        this.cities = MapFileReader.readMap("map1-example");
        
        this.pawn.addCard("Bogota");
        this.pawn.addCard("Cairo");
        this.pawn.addCard("Beijing");
        this.pawn.addCard("Buenos Aires");
        this.pawn.addCard("Delhi");
        this.pawn.addCard("Khartoum");
        this.pawn.addCard("Lagos");
        this.pawn.addCard("Tehran");
        this.pawn.addCard("Tokyo");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
        this.pawn = null;
        this.game = null;
        this.cities = null;
    }
    
    /**
     * Tests if treat() correctly treats the city.
     */
    @Test
    public void treatTest() {
        System.out.println("***PawnTest.treatTest() START***");
        
        assertEquals(0, this.pawn.getCurrentCity().getInfectionY());
        assertEquals(3, this.pawn.getCurrentCity().getInfectionR());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionB());
        assertEquals(2, this.pawn.getCurrentCity().getInfectionG());
        
        this.pawn.treat();
        
        assertEquals(0, this.pawn.getCurrentCity().getInfectionY());
        assertEquals(2, this.pawn.getCurrentCity().getInfectionR());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionB());
        assertEquals(2, this.pawn.getCurrentCity().getInfectionG());
        
        this.pawn.treat();
        
        assertEquals(0, this.pawn.getCurrentCity().getInfectionY());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionR());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionB());
        assertEquals(2, this.pawn.getCurrentCity().getInfectionG());
        
        this.pawn.treat();
        
        assertEquals(0, this.pawn.getCurrentCity().getInfectionY());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionR());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionB());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionG());
        
        this.pawn.treat();
        
        assertEquals(0, this.pawn.getCurrentCity().getInfectionY());
        assertEquals(0, this.pawn.getCurrentCity().getInfectionR());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionB());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionG());
        
        this.pawn.treat();
        
        assertEquals(0, this.pawn.getCurrentCity().getInfectionY());
        assertEquals(0, this.pawn.getCurrentCity().getInfectionR());
        assertEquals(0, this.pawn.getCurrentCity().getInfectionB());
        assertEquals(1, this.pawn.getCurrentCity().getInfectionG());
        
        this.pawn.treat();
        
        assertEquals(0, this.pawn.getCurrentCity().getInfectionY());
        assertEquals(0, this.pawn.getCurrentCity().getInfectionR());
        assertEquals(0, this.pawn.getCurrentCity().getInfectionB());
        assertEquals(0, this.pawn.getCurrentCity().getInfectionG());
        
        this.pawn.treat(); // Throws error
        
        System.out.println("***PawnTest.treatTest()   END***" + "\n");
    }
    
    /**
     * Tests if discardCards() correctly discards cards.
     */
    @Test
    public void discardCardsTest() {
        System.out.println("***PawnTest.discardCardsTest() START***");
        
        System.out.println(this.pawn.getCards().toString());
        this.pawn.discardCards(this.cities, false, false, false, false);
        System.out.println(this.pawn.getCards().toString());
        
        System.out.println("***PawnTest.discardCardsTest()   END***"  + "\n");
    }
    
    
}