import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

/**
 * Contains flights and bookings available in database
 * Has the main function that displays the GUI to checkIn passengers
 */
public class CheckIn {
    private static HashMap<String, Flight> flights;

    private static HashMap<String, Booking> bookings;

    private Integer checkInPassenger;

    /**
     * @param flights
     * @param bookings
     */
    public CheckIn(HashMap<String, Flight> flights, HashMap<String, Booking> bookings) {
        CheckIn.flights = flights;
        CheckIn.bookings = bookings;
        this.checkInPassenger = 0;
    }

    /**
     * @return all flights
     */
    public HashMap<String, Flight> getFlights() {
        return flights;
    }

    /**
     * @return all bookings
     */
    public HashMap<String, Booking> getBookings() {
        return bookings;
    }

    /**
     * @param key
     * @return a specific flight found by key
     */
    public Flight getFlight(String key) {
        return flights.get(key);
    }

    /**
     * @param key
     * @return a specific flight found by key
     */
    public Booking getBooking(String key) {
        return bookings.get(key);
    }

    /**
     * @return how many passenger has checked in
     */
    public Integer getCheckInPassenger() {
        return checkInPassenger;
    }

    /**
     * Check in the passenger by incrementing the total of passengers who have checkedIn
     * and set add this passenger baggage to the corresponding flight
     * @param booking
     * @param passengerBaggage
     */
    public void checkInPassenger(Booking booking, BaggageSize passengerBaggage) {
        booking.setCheckedIn(true);
        Flight passengerFlight = booking.getFlight();
        passengerFlight.addOnePassenger();
        passengerFlight.addBaggageRegistered(passengerBaggage);
        checkInPassenger++;
    }

    /**
     * @return flights got from input files
     */
    // TODO: move this function in a fileHelper class
    public static HashMap<String, Flight> readFlightsFromInputFiles() {
        JSONParser jsonParser = new JSONParser();
        HashMap<String, Flight> flightHashMap = new HashMap<String, Flight>();
        try  {
            JSONArray flightsArray = (JSONArray) jsonParser.parse(new FileReader("./flights.json"));

            for (Object o : flightsArray)
            {
                JSONObject flight = (JSONObject) o;

                String flightCode = (String) flight.get("Flight Code");

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
        return flightHashMap;
    }

    /**
     * @param flightList
     * @return bookings got from input file
     */
    // TODO: move this function in a fileHelper class
    public static HashMap<String, Booking> readBookingsFromInputFiles(HashMap<String, Flight> flightList) {

        JSONParser jsonParser = new JSONParser();
        HashMap<String, Booking> bookingHashMap = new HashMap<String, Booking>();

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

                Passenger passenger = new Passenger(firstName, lastName, passengerAge);

                BaggageSize baggage = new BaggageSize(baggageWeight, baggageVolume);
                Booking bookingObject = new Booking(bookingReferenceCode, checkedIn, flight, baggage, passenger);
                bookingHashMap.put(String.valueOf(bookingReferenceCode), bookingObject);
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
        return bookingHashMap;

    }

    /**
     * @param args
     * Starting point of the program, initialize variables and launch the GUI with the listener
     */
    public static void main (String[] args) {

        // should come from files
        final HashMap<String, Flight> flights = readFlightsFromInputFiles();
        HashMap<String, Booking> bookings = readBookingsFromInputFiles(flights);

        final Integer bookingNumber = bookings.size();
        final CheckIn checkIn = new CheckIn(flights, bookings);
        final GUI GUI = new GUI();
        ActionListener handleConfirm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking booking = checkIn.getBooking(GUI.getBookingReferenceInput());
                if (booking != null && booking.canPassengerAccess(GUI.getSurnameInput(), GUI.getLastNameInput())) {
                    if (!booking.getCheckedIn()) {
                        BaggageSize passengerBaggage = new BaggageSize(GUI.getBaggageWeightInput(), GUI.getBaggageVolumeInput());
                        if (passengerBaggage.isValidSize()) {
                            // if passenger baggage are over capacity, the GUI shows a dialog and the user has to
                            // to be able to check in
                            if (passengerBaggage.isOverCapacity(booking.getBaggageSize())) {
                                if (GUI.printOverCapacityConfirmDialog(passengerBaggage.calculateOverCapacityPrice(booking.getBaggageSize()))) {
                                    checkIn.checkInPassenger(booking, passengerBaggage);
                                    GUI.clear();
                                }
                            } else {
                                checkIn.checkInPassenger(booking, passengerBaggage);
                                GUI.clear();
                            }
                            // when every passenger have checked in the program print the report
                            if (bookingNumber.equals(checkIn.getCheckInPassenger())) {
                                GUI.close();
                                FileHelper.writeToFile("Report.txt", FileHelper.makeReport(CheckIn.flights));
                                System.exit(0);
                            }
                        } else {
                            GUI.setMessage("Invalid baggage size inputs");
                        }
                    } else {
                        GUI.setMessage("This passenger has already checkedIn");
                    }
                } else {
                    GUI.setMessage("Invalid passenger inputs");
                }
            }
        };
        GUI.setOnConfirm(handleConfirm);
        
    }

}
