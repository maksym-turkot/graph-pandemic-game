import java.util.TreeMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * This class controls the flow of the game.
 *
 * @author Maksym Turkot
 * @version 05/26/2021
 */
public class Game {
    private TreeMap<String, City> cities = new TreeMap<String, City>();
    private LinkedList<String> baseDeck;
    private LinkedList<String> shuffledCityDeck;
    private LinkedList<String> shuffledInfectionDeck;
    private LinkedList<Pawn> pawns;
    private Rand rand;
    private String mapFile;
    private int cureCnt;
    private int seed;
    private int turnCnt;
    private int infectCnt;
    private int infectRate;
    private int yel;
    private int red;
    private int blu;
    private int grn;
    private boolean isYCured;
    private boolean isRCured;
    private boolean isBCured;
    private boolean isGCured;
    private boolean isYEradicated;
    private boolean isREradicated;
    private boolean isBEradicated;
    private boolean isGEradicated;

    /**
     * Constructor for objects of class Game
     * 
     * @param cities a tree map of cities on the map
     */
    public Game(String mapFile, int seed) {
        this.seed = seed;
        this.rand = new Rand(seed);
        this.mapFile = mapFile;
        this.cities = MapFileReader.readMap(mapFile);

        this.baseDeck = 
        this.toLinkedList(cities.keySet());
        this.shuffledCityDeck = this.shuffleDeck(baseDeck, false);
        this.shuffledInfectionDeck = this.shuffleDeck(baseDeck, true);
        this.pawns = this.createPawns();

        this.cureCnt = 0;
        this.turnCnt = 0;
        this.infectCnt = 0;
        this.setInfectionRate();
        this.yel = 24;
        this.red = 24;
        this.blu = 24;
        this.grn = 24;
        this.isYCured = false;
        this.isRCured = false;
        this.isBCured = false;
        this.isGCured = false;
        this.isYEradicated = false;
        this.isREradicated = false;
        this.isBEradicated = false;
        this.isGEradicated = false;
    }
    
    /**
     * Increments the cure counter.
     */
    public void incrCureCnt() {
        this.cureCnt++;
    }

    /**
     * Gets base deck of this controller.
     * 
     * @return id id of this city.
     */
    public LinkedList<String> getBaseDeck() {
        return this.baseDeck;
    }

    /**
     * Gets shuffled player deck of this controller.
     * 
     * @return shuffled player deck
     */
    public LinkedList<String> getShuffledCityDeck() {
        return this.shuffledCityDeck;
    }

    /**
     * Gets shuffled infection deck of this controller.
     * 
     * @return shuffled pinfection deck
     */
    public LinkedList<String> getShuffledInfectionDeck() {
        return this.shuffledInfectionDeck;
    }
    
    /**
     * Gets random for this game.
     * 
     * @return random for this game.
     */
    public Rand getRand() {
        return this.rand;
    }
    
    /**
     * Returns TreeMap of cities.
     */
    public TreeMap<String, City> getCities() {
        return this.cities;
    }
    
    /**
     * Returns Y infection.
     */
    public int getYel() {
        return this.yel;
    }
    
    /**
     * Returns R infection.
     */
    public int getRed() {
        return this.red;
    }
    
    /**
     * Returns B infection.
     */
    public int getBlu() {
        return this.blu;
    }
    
    /**
     * Returns G infection.
     */
    public int getGrn() {
        return this.grn;
    }
    
    /**
     * Returns true if Y has been cured.
     */
    public boolean getIsYCured() {
        return this.isYCured;
    }
    
    /**
     * Returns true if R has been cured.
     */
    public boolean getIsRCured() {
        return this.isRCured;
    }
    
    /**
     * Returns true if B has been cured.
     */
    public boolean getIsBCured() {
        return this.isBCured;
    }
    
    /**
     * Returns true if G has been cured.
     */
    public boolean getIsGCured() {
        return this.isGCured;
    }
    
    /**
     * Cure infection Y.
     */
    public void cureY() {
        this.isYCured = true;
    }
    
    /**
     * Cure infection R.
     */
    public void cureR() {
        this.isRCured = true;
    }
    
    /**
     * Cure infection B.
     */
    public void cureB() {
        this.isBCured = true;
    }
    
    /**
     * Cure infection G.
     */
    public void cureG() {
        this.isGCured = true;
    }
    
    /**
     * Eradicates disease Y.
     */
    public void eradicateY() {
        this.isYEradicated = true;
    }
    
    /**
     * Eradicates disease R.
     */
    public void eradicateR() {
        this.isREradicated = true;
    }
    
    /**
     * Eradicates disease B.
     */
    public void eradicateB() {
        this.isBEradicated = true;
    }
    
    /**
     * Eradicates disease G.
     */
    public void eradicateG() {
        this.isGEradicated = true;
    }
    
    /**
     * Returns a list of pawns 
     */
    public LinkedList<Pawn> getPawns() {
        return this.pawns;
    }
    
    /**
     * Sets an infection rate based on infecton count.
     */
    private void setInfectionRate() {

        // Set infection rate.
        if (this.infectCnt < 3) {
            this.infectRate = 2;
        } else if (this.infectCnt < 6) {
            this.infectRate = 3;
        } else {
            this.infectRate = 4;
        }
    }

    /**
     * Runs the game.
     */
    public void runGame() {     
        String logString = "";

        this.infectCities(3, 3);
        this.infectCities(3, 2);
        this.infectCities(3, 1);

        // Run each turn until the game is over.
        while (true) {
            this.turnCnt++;

            // Every pawn takes a turn.
            for (Pawn pawn : pawns) {

                // Take 4 actions
                for (int i = 1; i < 4; i++) {
                    pawn.doAction(this.cities);
                }

                // Check if pawns ran out of city cards.
                if (this.shuffledCityDeck.size() < 2) {
                    logString += this.logString();
                    this.endGame("cards", logString);
                    return;
                }
                this.dealCards(pawn);

                this.infectCities(this.infectRate, 1);

                // Check if game ran out of disease cubes.
                if (this.yel < 0 || this.red < 0 || this.blu < 0 || this.grn < 0) {
                    logString += this.logString();
                    this.endGame("cubes", logString);
                    return;
                }
            }

            // Check if pawns cured all diseases.
            if (isRCured && isYCured && isBCured && isGCured) {
                logString += this.logString();
                this.endGame("cured", logString);
                return;
            }

            logString += this.logString();
        }
    }

    /**
     * Summarizes outcomes of the game.
     */
    private void endGame(String msg, String logString) {
        OutputManager.writeLog("log_" + this.mapFile + "_seed" + this.seed, logString);
        String outcome = "";
        // Check how the game finished.
        switch (msg) {
            case "cards":
                outcome = this.outcomeString() + "\n" +"LOSS: Ran out of player cards.";
                break;
            case "cubes":
                outcome = "LOSS: Too many infecton cubes.";
                break;
            case "cured":
                outcome = "VICTORY: All infections were cured.";
                break;
            default:
                System.out.println("ERR: invalid status value passed to endGame().");
                break;
        }
        
        OutputManager.writeStatistics("outcome_" + this.mapFile + "_seed" + this.seed, outcome);
    }

    /** 
     * Converst key set to a Linked list.
     * 
     * @param keySet set of keys.
     * @return list of city names.
     */
    private LinkedList<String> toLinkedList(Set keySet) {
        Object[] keys = keySet.toArray();
        LinkedList<String> keyList = new LinkedList<String>();

        // Cinvert array to a list by element.
        for (Object key : keys) {
            keyList.add(String.valueOf(key));
        }
        return keyList;
    }

    /**
     * Shuffles the deck of cards.
     */
    private LinkedList<String> shuffleDeck(LinkedList<String> base, boolean infectionDeck) {
        // int shift = 0;
        String newCard;
        LinkedList<String> shuffled = new LinkedList<String>();

        // Check if infection deck is being shuffled.
        if (infectionDeck) {
            // shift = baseDeck.size() / 2;
        }

        // Fill a shuffled deck.
        for (int i = 0; i < base.size(); i++) {

            // Add a unique card from base deck to the shuffled one.
            do {
                newCard = base.get(rand.getRand(baseDeck.size()));
            } while (shuffled.contains(newCard));
            shuffled.add(newCard);
        }
        return shuffled;
    }

    /**
     * Creates pawns and places the in random city.
     * 
     * @return pawnList of created pawns
     */
    private LinkedList<Pawn> createPawns() {
        LinkedList<Pawn> pawnList = new LinkedList<Pawn>();
        
        // Generate 7 pawns.
        for (int i = 0; i < 4; i++) {
            Pawn newPawn = new Pawn(cities.get(this.baseDeck.get(rand.getRand(this.baseDeck.size()))), this);
            pawnList.add(newPawn);
            this.dealCards(newPawn);
        }
        return pawnList;
    }

    /**
     * Deals pawn 2 cards.
     * 
     * @param pawn pawn to be dealt cards
     */
    public void dealCards(Pawn pawn) {
        pawn.addCard(this.shuffledCityDeck.remove());
        pawn.addCard(this.shuffledCityDeck.remove());

        // If pawn has more than 7 cards, discard cards.
        if (pawn.getCards().size() > 7) {
            pawn.discardCards(this.cities, this.isYCured, this.isRCured, this.isBCured, this.isGCured);
        }
    }

    /**
     * Picks cities from the deck to infect.
     */
    private void infectCities(int cnt, int lvl) {

        // Infects given number of cities with given level of infection.
        for (int i = 0; i < cnt; i++) {
            
            // If infection deck is empty, reshuffle it.
            if (this.shuffledInfectionDeck.size() < 1) {
                this.shuffledInfectionDeck = this.shuffleDeck(baseDeck, true);
            }
            City city = this.cities.get(this.shuffledInfectionDeck.remove());
            this.infect(city, city.getColor(), lvl);
        }
    }

    /**
     * Increments level of infection of a passed disease 
     * by a passed amount.
     * 
     * @param city city to be infected
     * @param infection type of disease infecting
     * @param lvl level of increased infection
     */
    public void infect(City city, char infection, int lvl) {

        // Check if valid level of infection was passed
        if (lvl == 1 || lvl == 2 || lvl == 3) {

            // Check which disease is infecting
            switch (infection) {
                case 'Y':
                
                    // Check if this disease is already eradicated.
                    if (!this.isYEradicated) {
                        city.increaseInfectionY(lvl);
                        this.yel--;
                    }
                    break;
                case 'R':
                
                    // Check if this disease is already eradicated.
                    if (!this.isREradicated) {
                        city.increaseInfectionR(lvl);
                        this.red--;
                        
                        // Check if all cubes were cured.
                        if (this.red == 0) {
                            this.isREradicated = true;
                        }
                    }
                    break;
                case 'B':
                
                    // Check if this disease is already eradicated.
                    if (!this.isBEradicated) {
                        city.increaseInfectionB(lvl);
                        this.blu--;
                        
                        // Check if all cubes were cured.
                        if (this.blu == 0) {
                            this.isBEradicated = true;
                        }
                    }
                    break;
                case 'G':
                
                    // Check if this disease is already eradicated.
                    if (!this.isGEradicated) {
                        city.increaseInfectionG(lvl);
                        this.grn--;
                        
                        // Check if all cubes were cured.
                        if (this.grn == 0) {
                            this.isGEradicated = true;
                        }
                    }
                    break;
                default:
                    System.out.println("ERR: Invalid infection char " + "\"" + infection + "\"" + " passed to infect() for " + city.getName());
                    break;
            }
        } else {
            System.out.println("ERR: Invalid infection Y lvl " + "\"" + lvl + "\"" + " passed to infect() for " + city.getName());
        }
    }

    /**
     * Returns a string with data about the program.
     */
    public String logString() {
        String ret = ""; 

        ret += "**********************************************" + "\n";
        ret += "Map: " + this.mapFile + " | " + "Seed: " + this.seed + " | " + "Turn: " + this.turnCnt + " | " + "InfectRate: " + this.infectRate + "\n";
        ret += "==============================================" + "\n";
        ret += "CityCards: " + this.shuffledCityDeck.size() + " | " + "InfectionCards: " + this.shuffledInfectionDeck.size() + "\n";
        ret += "==============================================" + "\n";
        ret += "Diseases: " + "\n";
        ret += "         Active    isCured    isEradicated" + "\n";
        ret += "Yellow:    " + (24 - this.yel) + "        " + this.isYCured + "        " + this.isYEradicated + "\n";
        ret += "Red:       " + (24 - this.red) + "        " + this.isRCured + "        " + this.isREradicated + "\n";
        ret += "Blue:      " + (24 - this.blu) + "        " + this.isBCured + "        " + this.isBEradicated + "\n";
        ret += "Green:     " + (24 - this.grn) + "        " + this.isGCured + "        " + this.isGEradicated + "\n";
        ret += "==============================================" + "\n";
        ret += "Pawns:" + "\n\n";

        // Get info about each pawn.
        for (Pawn pawn: pawns) {
            ret += pawn.toString() + "\n";
        }
        ret += "==============================================" + "\n";
        ret += "Cities:" + "\n\n";

        // Get info about each city.
        for (String city : this.baseDeck) {
            ret += this.cities.get(city).toString() + "\n";
        }
        return ret;
    }
    
    /**
     * Compiles an output string.
     * 
     * @return output string
     */
    private String outcomeString() {
        String ret = "";
        ret += "Map: " + this.mapFile + "\n";
        ret += "InfectionsCured: " + this.cureCnt + "\n";
        ret += "InfectionsLeft:  " + this.yel +"Y," + this.red + "R," + this.blu + "B," + this.grn + "G" + "\n";
        
        return ret;
    }
}