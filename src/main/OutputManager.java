import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

/**
 * This class manages output file creation and writing.
 *
 * @author Maksym Turkot
 * @version 05/13/2021
 */
public class OutputManager {
    private static PrintWriter fileWriter;
    private static File outputFile;
    
    /**
     * Constructor for objects of class Random
     */
    public OutputManager() {

    }
    
    /**
     * Writes the log file.
     * 
     * @param name name of the specific log
     * @param data data to output
     */
    public static void writeLog(String name, String data) {
        try {
            outputFile = new File("data/logs/" + name +".txt");
            outputFile.createNewFile();
            fileWriter = new PrintWriter(outputFile);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();
        } catch(IOException e) {  
            System.out.println(e);
        }
    }
    
    /**
     * Writes program output file.
     * 
     * @param data to output.
     */
    public static void writeStatistics(String name, String data) {
        try {
            outputFile = new File("data/outcomes/" + name +".txt");
            outputFile.createNewFile();
            fileWriter = new PrintWriter(outputFile);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();
        } catch(IOException e) { 
            System.out.println(e);
        } catch (Exception e) { 
            System.out.println(e);
        }
    }
}
