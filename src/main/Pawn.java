import java.util.LinkedList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Set;

/**
 * This class constructs a pawn.
 *
 * @author Maksym Turkot
 * @version 05/26/2021
 */
public class Pawn {
    private static int pawnCnt = 1;
    protected Game game;
    protected LinkedList<String> cards;
    protected LinkedList<String> cardColors;
    protected LinkedList<String> cityTrace;
    protected City currentCity;
    private int id;

    /**
     * Constructor for objects of class Pawn
     */
    public Pawn() {
        this.id = pawnCnt++;
        this.cards = new LinkedList<String>();
        this.cardColors = new LinkedList<String>();
        this.cityTrace = new LinkedList<String>();
        this.currentCity = null;
        this.cityTrace.add(currentCity.getName());
        this.cardsToColors();
    }

    /**
     * Constructor for objects of class Pawn
     */
    public Pawn(City currentCity, Game game) {
        this.id = pawnCnt++;
        this.game = game;
        this.cards = new LinkedList<String>();
        this.cardColors = new LinkedList<String>();
        this.cityTrace = new LinkedList<String>();
        this.currentCity = currentCity; 
        this.cityTrace.add(currentCity.getName());
        this.cardsToColors();
    }

    /**
     * Gets id of this pawn.
     * 
     * @return id id of this pawn.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets cards of this pawn.
     * 
     * @return cards cards of this pawn.
     */
    public LinkedList<String> getCards() {
        return this.cards;
    }

    /**
     * Gets cards of this pawn.
     * 
     * @return cards cards of this pawn.
     */
    public LinkedList<String> getCityTrace() {
        return this.cityTrace;
    }

    /**
     * Adds a city to the city trace.
     * 
     * @param city to be added
     */
    public void putCityTrace(City city) {
        cityTrace.add(city.getName());
    }

    /**
     * Gets cards of this pawn.
     * 
     * @return cards of this pawn.
     */
    public void addCard(String card) {
        this.cards.add(card);
        this.cardsToColors();
    }

    /**
     * Gets current city of this pawn.
     * 
     * @return currentCity current city of this pawn.
     */
    public City getCurrentCity() {
        return this.currentCity;
    }

    /**
     * Sets current city of this pawn.
     * 
     * @param currentCity - new current location of the pawn.
     */
    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
        this.cityTrace.add(currentCity.getName());
    }

    /**
     * Performs a specific action based on the situation.
     */
    public void doAction(TreeMap<String, City> cities) {
        LinkedList<String> newCards = new LinkedList<String>();
        int yel = 0;
        int red = 0;
        int blu = 0;
        int grn = 0;

        // Count number of cards of each color in hand.
        for (String card : this.cards) {
            switch(cities.get(card).getColor()) {
                case 'Y':
                yel++;
                break;
                case 'R':
                red++;
                break;
                case 'B':
                blu++;
                break;
                case 'G':
                grn++;
                break;
                default:
                System.out.println("ERR: Invalid city color \"" + cities.get(card).getColor() + 
                    "\" detected in Pawn.discardCards() for " + cities.get(card));
                break;
            }
        }

        // Check if pawn can cure a disease.
        if (yel == 5) {
            game.cureY();
            game.incrCureCnt();

            // Go though cards.
            for (String card : cards) {

                // Remove card if color matches.
                if (cities.get(card).getColor() != 'Y') {
                    newCards.add(card);
                }
            }
            this.cards = newCards;
        } else if (red == 5) {
            game.cureR();
            game.incrCureCnt();

            // Go though cards.
            for (String card : cards) {

                // Remove card if color matches.
                if (cities.get(card).getColor() != 'R') {
                    newCards.add(card);
                }
            }
            this.cards = newCards;
        } else if (blu == 5) {
            game.cureB();
            game.incrCureCnt();

            // Go though cards.
            for (String card : cards) {

                // Remove card if color matches.
                if (cities.get(card).getColor() != 'B') {
                    newCards.add(card);
                }
            }
            this.cards = newCards;
        } else if (grn == 5) {
            game.cureG();
            game.incrCureCnt();

            // Go though cards.
            for (String card : cards) {

                // Remove card if color matches.
                if (cities.get(card).getColor() != 'G') {
                    newCards.add(card);
                }
            }
            this.cards = newCards;
        }

        // Check if current city has infections in it.
        if (currentCity.getInfectionY() > 0 || currentCity.getInfectionR() > 0 ||
        currentCity.getInfectionB() > 0 || currentCity.getInfectionG() > 0) {
            this.treat();
        } else {
            LinkedList<String> neighborKeys = this.toLinkedList(this.currentCity.getNeighbors().keySet());
            currentCity = game.getCities().get(neighborKeys.get(game.getRand().getRand(neighborKeys.size())));
            cityTrace.add(currentCity.getName());
        }

        this.cardsToColors();
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
     * Generates list of colors of cards in hand.
     */
    private void cardsToColors() {
        cardColors = new LinkedList<String>();

        // Get color of each card
        for (String card : cards) {
            cardColors.add(String.valueOf(game
                    .getCities()
                    .get(card)
                    .getColor()));
        }
    }

    /**
     * Treats disease in the current city. Removes one infection level, 
     * or all levels if disease has been cured. Diseases with highest 
     * infection level at current city are prioritized.
     */
    public void treat() {
        int[] infections = {this.currentCity.getInfectionY(), this.currentCity.getInfectionR(), 
                this.currentCity.getInfectionB(), this.currentCity.getInfectionG()};
        Arrays.sort(infections);

        // Check if there are infections in this city
        if (infections[3] == 0) {
            System.out.println("ERR: No infections in " + this.currentCity.getName() + ". Can't cure.");
        } else if (infections[3] == this.currentCity.getInfectionY()) {

            // Ceck if disease has been cured
            if (game.getIsYCured()) {
                this.currentCity.eliminateInfectionY();
            } else {
                this.currentCity.decrementInfectionY();
            }

            // Check if all cubes were cured.
            if (game.getYel() == 24) {
                game.eradicateY();
            }
        } else if (infections[3] == this.currentCity.getInfectionR()) {

            // Ceck if disease has been cured
            if (game.getIsRCured()) {
                this.currentCity.eliminateInfectionR();
            } else {
                this.currentCity.decrementInfectionR();
            }

            // Check if all cubes were cured.
            if (game.getRed() == 24) {
                game.eradicateR();
            }
        } else if (infections[3] == this.currentCity.getInfectionB()) {

            // Ceck if disease has been cured
            if (game.getIsBCured()) {
                this.currentCity.eliminateInfectionB();
            } else {
                this.currentCity.decrementInfectionB();
            }

            // Check if all cubes were cured.
            if (game.getBlu() == 24) {
                game.eradicateB();
            }
        } else if (infections[3] == this.currentCity.getInfectionG()) {

            // Ceck if disease has been cured
            if (game.getIsGCured()) {
                this.currentCity.eliminateInfectionG();
            } else {
                this.currentCity.decrementInfectionG();
            }

            // Check if all cubes were cured.
            if (game.getGrn() == 24) {
                game.eradicateG();
            }
        } else {
            System.out.println("ERR: treat() at " + this.currentCity.getName());
        }
    }

    /**
     * Discards cards from this pawn's hand. Cards of cured diseases are discarded first. Cards colors of which have the smallest
     * count in this hand are prioritized for discarding.
     * 
     * @param cities - list of sities on the map
     * @param isYcured - true of yellow disease has been cured
     * @param isRcured - true of red disease has been cured
     * @param isBcured - true of blue disease has been cured
     * @param isGcured - true of green disease has been cured
     */
    public void discardCards(TreeMap<String, City> cities, boolean isYcured, boolean isRcured, boolean isBcured, boolean isGcured) {
        int yel = 0;
        int red = 0;
        int blu = 0;
        int grn = 0;

        // Count number of cards of each color in hand.
        for (String card : this.cards) {
            switch(cities.get(card).getColor()) {
                case 'Y':
                yel++;
                break;
                case 'R':
                red++;
                break;
                case 'B':
                blu++;
                break;
                case 'G':
                grn++;
                break;
                default:
                System.out.println("ERR: Invalid city color \"" + cities.get(card).getColor() + 
                    "\" detected in Pawn.discardCards() for " + cities.get(card));
                break;
            }
        }

        // Prioritise discarding of already cured disease colors.
        if (isYcured && yel > 0) {
            this.discardCard(cities, 'Y');
        } else if (isRcured && red > 0) {
            this.discardCard(cities, 'R');
        } else if (isBcured && blu > 0) {
            this.discardCard(cities, 'B');
        } else if (isGcured && grn > 0) {
            this.discardCard(cities, 'G');
        } else {
            this.searchMinColorCnt(cities, yel, red, blu, grn);
        }

        // Check if there are still more than 7 cards in hand
        if (this.cards.size() > 7) {
            this.discardCards(cities, isYcured, isRcured, isBcured, isGcured);
        }
    }

    /**
     * Discards a card of a cured disease color.
     * 
     * @param cities - list of sities on the map
     * @param infection - type of infection color to be discarded
     */
    private void discardCard(TreeMap<String, City> cities, char infection) {

        // Look for and remove card of specified color
        for (String card : this.cards) {

            // Look for a city with seeked color
            if (cities.get(card).getColor() == infection) {
                this.cards.remove(card);
                break;
            }
        }
    }

    /**
     * Discards a card of the smallest color count.
     * 
     * @param cities - list of sities on the map
     * @param yel - count of Y disease
     * @param red - count of R disease
     * @param blu - count of B disease
     * @param grn - count of G disease
     */
    private void searchMinColorCnt(TreeMap<String, City> cities, int yel, int red, int blu, int grn) {
        int[] cardNums = {yel, red, blu, grn};
        Arrays.sort(cardNums);

        // Find min cnt
        for (int cardNum : cardNums) {

            // Match color to cnt
            if (cardNum != 0) {
                if (cardNum == yel) {
                    this.discardCard(cities, 'Y');
                    yel--;
                    break;
                } else if (cardNum == red) {
                    this.discardCard(cities, 'R');
                    red--;
                    break;
                } else if (cardNum == blu) {
                    this.discardCard(cities, 'B');
                    blu--;
                    break;
                } else if (cardNum == grn) {
                    this.discardCard(cities, 'G');
                    grn--;
                    break;
                } else {
                    System.out.println("ERR: couldn't match cardNum cnt to color in Pawn.searchMinColorCnt()");
                    break;
                }
            }
        }
    }

    /**
     * Prints out information about the current state of the pawn.
     * 
     * @return ret string of this city
     */
    public String toString() {
        String ret = "";
        ret += "ID: " + this.id + "\n" +
        "CityCards:   " + this.cards.toString() + "\n" + 
        "CardColors:  " + this.cardColors + "\n" +
        "CurrentCity: " + this.currentCity.getName() + "\n" +
        "CityTrace:   " + this.cityTrace.toString() + "\n";

        return ret;
    }
}