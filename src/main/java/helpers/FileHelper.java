package helpers;

import model.collection.BookingList;
import model.collection.FlightList;
import model.entity.BaggageSize;
import model.entity.Booking;
import model.entity.Flight;
import model.entity.PassengerInformation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Integer.parseInt;


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
        String report = "List of flight's statistics after check-in\nFlight code       Nb passengers   Total weight (exceeded?)" +
                "   Total volume (exceeded?)  Extra fees collected (£)"
                + "\n";
        report += "---------------------------------------------------------------------------------------------\n";
        for (Flight flight : flights.values()) {

            report += String.format("%-18s", flight.getFlightCode());
            report += String.format("%-16d", flight.getNbPassengersRegistered());
            report += String.format("%-4d", flight.getBaggageRegistered().getWeight());
            report += String.format("%-23s", "(" + flight.isFlightOverweighted() + ")");
            report += String.format("%-4d", flight.getBaggageRegistered().getVolume());
            report += String.format("%-22s", "(" + flight.isFlightOverweighted() + ")");
            report += String.format("%-6d", flight.getExtraFees());
            report += "\n";

        }
        return report;
    }

    /**
     * @return flights got from input files
     */
    public static void readFlightsFromInputFiles() {
        JSONParser jsonParser = new JSONParser();
        FlightList flightHashMap = FlightList.getInstance();
        try  {
            JSONArray flightsArray = (JSONArray) jsonParser.parse(new FileReader("./flights.json"));

            for (Object o : flightsArray)
            {
                JSONObject flight = (JSONObject) o;

                String flightCode = (String) flight.get("Flight Code").toString();

                String destinationAirport = (String) flight.get("Destination Airport");

                String carrier = (String) flight.get("Carrier");

                int maximumPassenger = parseInt(flight.get("Maximum Passengers").toString());

                int maximumBaggageWeight = parseInt(flight.get("Maximum Baggage Weight").toString());

                int maximumBaggageVolume = parseInt(flight.get("Maximum Baggage Volume").toString());

                BaggageSize baggage = new BaggageSize(maximumBaggageWeight, maximumBaggageVolume);
                Flight flightObject = new Flight(flightCode, destinationAirport, carrier, maximumPassenger, baggage);

                flightHashMap.put(flightObject.getFlightCode(), flightObject);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e + " File not found");
            System.exit(1);
        } catch (ParseException e) {
            System.out.println(e + " Parse Exception in JSON document");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e + "IOException");
            System.exit(1);
        }
    }

    /**
     * @return bookings got from input file
     */
    public static void readBookingsFromInputFiles() throws Exception {

        // Call the read flights method if the FlightList instance is empty
        FlightList flightList = FlightList.getInstance();
        if (flightList.isEmpty()) {
            readFlightsFromInputFiles();
        }

        JSONParser jsonParser = new JSONParser();
        BookingList bookingHashMap = BookingList.getInstance();

        try  {

            JSONArray bookings = (JSONArray) jsonParser.parse(new FileReader("./bookings.json"));

            for (Object o : bookings)
            {
                JSONObject booking = (JSONObject) o;

                String passengerName = (String) booking.get("Passenger Name");

                String firstName = passengerName.split(" ", 2)[0];
                String lastName = passengerName.split(" ", 2)[1];

                int passengerAge = Integer.parseInt(booking.get("Passenger Age").toString());

                int bookingReferenceCode = parseInt(booking.get("Booking Reference Code").toString());

                int flightCode = parseInt(booking.get("Flight Code").toString());

                Boolean checkedIn = Boolean.valueOf((String) booking.get("Checked In"));

                int baggageWeight = parseInt(booking.get("Baggage Weight").toString());

                int baggageVolume = parseInt(booking.get("Baggage Volume").toString());

                Flight flight = flightList.get(String.valueOf(flightCode));

                BaggageSize baggage = new BaggageSize(baggageWeight, baggageVolume);

                PassengerInformation passenger = new PassengerInformation(firstName, lastName, passengerAge);

                Booking bookingObject = new Booking(bookingReferenceCode, checkedIn, flight, baggage, passenger);
                bookingHashMap.put(bookingReferenceCode, bookingObject);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e + " File not found");
            System.exit(1);
        } catch (ParseException e) {
            System.out.println(e + " Parse Exception in JSON document");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(e + "IOException");
            System.exit(1);
        }

    }
}
