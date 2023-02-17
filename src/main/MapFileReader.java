import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * This class reads cofigurations from files.
 *
 * @author Maksym Turkot
 * @version 05/13/2021
 */
public class MapFileReader {
    /**
     * Constructor for objects of class MapFileReader
     */
    public MapFileReader() {
    }

    /**
     * Reads from model file and returns list of strings.
     * 
     * @param filename name of a file to be read
     * @return list of tasks read
     */
    public static TreeMap<String, City> readMap(String filename) {

        // Catch IO Exceptions
        try {
            Scanner sc = new Scanner(new File("data/maps/" + filename +".txt"));
            TreeMap<String, City> cities = new TreeMap<String, City>();
            String[] dataStr = {};
            String[] neighborsStr = {};

            // Store cities
            while(sc.hasNextLine()) {
                City city;
                String line = sc.nextLine();
                String cityName;
                dataStr = line.split(": ");
                cityName = dataStr[0];

                // Check if this city was already created
                if (cities.containsKey(cityName)) {
                    city = cities.get(cityName);
                } else {
                    city = new City(cityName);
                    cities.put(cityName, city);
                    distributeColor(city);
                }
                TreeMap<String, City> neighbors = new TreeMap<String, City>();
                neighborsStr = dataStr[1].split(", ");

                // Store neighbors of this city
                for (String neighbor : neighborsStr) {

                    // Check if this city is already on the map
                    if (!cities.containsKey(neighbor)) {
                        City newCity = new City(neighbor);
                        cities.put(neighbor, newCity);
                        distributeColor(newCity);
                    }
                    neighbors.put(neighbor, cities.get(neighbor));
                }
                city.setNeighbors(neighbors);
            }
            return cities;
        } catch (IOException err) {
            System.out.println(err);
            return null;
        }
    }

    /**
     * Distributes colors between cities.
     */
    private static void distributeColor(City city) {
        switch(city.getId() % 4) {
            case 0:
                city.setColor('Y');
                break;
            case 1:
                city.setColor('R');
                break;
            case 2:
                city.setColor('B');
                break;
            case 3: 
                city.setColor('G');
                break;
            default:
                System.out.println("ERR: Modulo operation failed at MapFileReader.distributeColor()");
                break;
        }
    }
}
