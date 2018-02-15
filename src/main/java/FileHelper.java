import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class containing static function to write and read files
 */
public class FileHelper {
    /**
     * Write the informations in the file provided in parameters.
     * --- > This method comes from F21SF course lab and re-used in F21SF coursework <-----
     * @param filename the file in which to write
     * @param text the text to write in the file
     */
    public static void writeToFile(String filename, String text) {
        FileWriter fw;
        try {
            fw = new FileWriter(filename);
            fw.write(text);
            fw.close();
        }
        //message and stop if file not found
        catch (FileNotFoundException fnf){
            System.out.println(filename + " not found ");
            System.exit(0);
        }
        //stack trace here because we don't expect to come here
        catch (IOException ioe){
            ioe.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Method to make the report that summarizes all the flights informations
     * @return The report
     */
    public static String makeReport(HashMap<String, Flight> flights) {
        String report = "List of flight's statistics after check-in\nFlight code       Nb passengers   Total weight   Total volume  "
                + "\n";
        report += "---------------------------------------------------------------------------------------------\n";
        for (Flight flight : flights.values()) {

            report += String.format("%-8s", flight.getFlightCode());
            report += String.format("%-3d", flight.getNbPassengersRegistered());
            report += String.format("%-5d", flight.getBaggageRegistered().getWeight());
            report += String.format("%-5d", flight.getBaggageRegistered().getVolume());
            report += "\n";

        }
        return report;
    }
}
