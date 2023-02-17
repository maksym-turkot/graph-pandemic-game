import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class MapFileReaderTest.
 *
 * @author  Maksym Turkot
 * @version 05/22/2021
 */
public class MapFileReaderTest {
    /**
     * Default constructor for test class MapFileReaderTest
     */
    public MapFileReaderTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
    }
    
    /**
     * Tests if map is correctly read from a file.
     */
    @Test
    public void readMapTest() {
        System.out.println("***MapFileReaderTest.readMapTest() START***");
        
        System.out.println(MapFileReader.readMap("map1-example"));
        
        System.out.println("***MapFileReaderTest.readMapTest()   END***" + "\n");
    }
}
