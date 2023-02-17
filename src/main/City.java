import java.util.TreeMap;

/**
 * This class constructs a city on the map (vertex in a tree).
 *
 * @author Maksym Turkot
 * @version 05/26/2021
 */
public class City {
    private static int cityCnt = 1;
    private TreeMap<String, City> neighbors;
    private String name;
    private char color;
    private int id;
    private int infectionY;
    private int infectionR;
    private int infectionB;
    private int infectionG;

    /**
     * Constructor for objects of class City
     */
    public City() {
        this.id = cityCnt++;
        this.neighbors = new TreeMap<String, City>();
        this.name = "no_name";
        this.color = '\u0000';
        this.infectionY = 0;
        this.infectionR = 0;
        this.infectionB = 0;
        this.infectionG = 0;
    }
    
    /**
     * Constructor for objects of class City
     * with name.
     * 
     * @param name name of this city
     */
    public City(String name) {
        this.id = cityCnt++;
        this.neighbors = new TreeMap<String, City>();
        this.name = name;
        this.color = '\u0000';
        this.infectionY = 0;
        this.infectionR = 0;
        this.infectionB = 0;
        this.infectionG = 0;
    }
    
    /**
     * Constructor for objects of class City
     * with name and color.
     * 
     * @param name name of this city
     * @param neighbors neighbors of this city
     */
    public City(String name, TreeMap<String, City> neighbors) {
        this.id = cityCnt++;
        this.neighbors = neighbors;
        this.name = name;
        this.infectionY = 0;
        this.infectionR = 0;
        this.infectionB = 0;
        this.infectionG = 0;
    }
    
    /**
     * Constructor for objects of class City
     * with name, color, and infection rates.
     * 
     * @param name name of this city
     * @param color color of this city
     * @param infectionY level of infection with Y
     * @param infectionR level of infection with R
     * @param infectionB level of infection with B
     * @param infectionG level of infection with G
     */
    public City(String name, char color, int infectionY, int infectionR, int infectionB, int infectionG) {
        this.id = cityCnt++;
        this.neighbors = new TreeMap<String, City>();
        this.name = name;
        this.color = color;
        this.infectionY = infectionY;
        this.infectionR = infectionR;
        this.infectionB = infectionB;
        this.infectionG = infectionG;
    }
    
    /**
     * Gets id of this city.
     * 
     * @return id id of this city.
     */
    public int getId() {
        return this.id;
    }
    
    /**k
     * Gets id of this city.
     * 
     * @return id id of this city.
     */
    public TreeMap<String, City> getNeighbors() {
        return this.neighbors;
    }
    
    /**
     * Sets neighbors of this city.
     * 
     * @param neighbors neoghbors of this city
     */
    public void setNeighbors(TreeMap<String, City> neighbors) {
        this.neighbors = neighbors;
    }
    
    /**
     * Gets name of this city.
     * 
     * @return name name of this city.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets color of this city.
     * 
     * @return color name of this city.
     */
    public char getColor() {
        return this.color;
    }
    
    /**
     * Sets color of this city.
     * 
     * @param color color of this city
     */
    public void setColor(char color) {
        this.color = color;
    }
    
    /**
     * Gets level of infection with Y disease
     * for this city.
     * 
     * @return level of infectionY of this city.
     */
    public int getInfectionY() {
        return this.infectionY;
    }
    
    /**
     * Increases level of infection with Y by lvl.
     */
    public void increaseInfectionY(int lvl) {
        this.infectionY += lvl;
        
        // Reset infection level if greatr than 3
        if (this.infectionY > 3) {
            this.infectionY = 3;
        }
    }
    
    /**
     * Lewers level of infection with Y by 1.
     */
    public void decrementInfectionY() {
        this.infectionY--;
    }
    
    /**
     * Sets level of infection with Y to zero.
     */
    public void eliminateInfectionY() {
        this.infectionY = 0;
    }
    
    /**
     * Gets level of infection with R disease
     * for this city.
     * 
     * @return level of infectionR of this city.
     */
    public int getInfectionR() {
        return this.infectionR;
    }
    
    /**
     * Increases level of infection with R by lvl.
     */
    public void increaseInfectionR(int lvl) {
        this.infectionR += lvl;
        
        // Reset infection level if greatr than 3
        if (this.infectionR > 3) {
            this.infectionR = 3;
        }
    }
    
    /**
     * Lewers level of infection with R by 1.
     */
    public void decrementInfectionR() {
        this.infectionR--;
    }
    
    /**
     * Sets level of infection with R to zero.
     */
    public void eliminateInfectionR() {
        this.infectionR = 0;
    }
    
    /**
     * Gets level of infection with B disease
     * for this city.
     * 
     * @return level of infectionB of this city.
     */
    public int getInfectionB() {
        return this.infectionB;
    }
    
    /**
     * Increases level of infection with B by lvl.
     */
    public void increaseInfectionB(int lvl) {
        this.infectionB += lvl;
        
        // Reset infection level if greatr than 3
        if (this.infectionB > 3) {
            this.infectionB = 3;
        }
    }
    
    /**
     * Lewers level of infection with B by 1.
     */
    public void decrementInfectionB() {
        this.infectionB--;
    }
    
    /**
     * Sets level of infection with B to zero.
     */
    public void eliminateInfectionB() {
        this.infectionB = 0;
    }
    
    /**
     * Gets level of infection with G disease
     * for this city.
     * 
     * @return level of infectionG of this city.
     */
    public int getInfectionG() {
        return this.infectionG;
    }
    
    /**
     * Increases level of infection with G by lvl.
     */
    public void increaseInfectionG(int lvl) {
        this.infectionG += lvl;
        
        // Reset infection level if greatr than 3
        if (this.infectionG > 3) {
            this.infectionG = 3;
        }
    }
    
    /**
     * Lewers level of infection with G by 1.
     */
    public void decrementInfectionG() {
        this.infectionG--;
    }
    
    /**
     * Sets level of infection with G to zero.
     */
    public void eliminateInfectionG() {
        this.infectionG = 0;
    }
    
    /**
     * Returns information about the current state of the city.
     * 
     * @return ret string of this city
     */
    public String toString() {
        String ret = "";
        ret += this.name + "\n" +
            "Color: " + this.color + "\n" +
            "Neighbors:  " + this.neighbors.keySet().toString() + "\n" + 
            "Infections: " + infectionY + "Y," + infectionR + "R," + infectionB + "B," + infectionG + "G" + "\n";
        return ret;
    }
}