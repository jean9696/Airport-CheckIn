import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class CheckIn {
    private static HashMap<String, Flight> flights;

    private static HashMap<String, Booking> bookings;

    private Integer checkInPassenger;

    public CheckIn(HashMap<String, Flight> flights, HashMap<String, Booking> bookings) {
        this.flights = flights;
        this.bookings = bookings;
        this.checkInPassenger = 0;
    }

    public HashMap<String, Flight> getFlights() {
        return flights;
    }

    public HashMap<String, Booking> getBookings() {
        return bookings;
    }

    public Flight getFlight(String key) {
        return flights.get(key);
    }

    public Booking getBooking(String key) {
        return bookings.get(key);
    }

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

    public static void main (String[] args) {

        // should come from files
        HashMap<String, Flight> flights = readFlightsFromInputFiles();
        HashMap<String, Booking> bookings = readBookingsFromInputFiles(flights);

        final Integer bookingNumber = bookings.size();
        final CheckIn checkIn = new CheckIn(flights, bookings);
        final GUI GUI = new GUI();
        ActionListener handleConfirm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking booking = checkIn.getBooking(GUI.getBookingReferenceInput());
                if (booking != null && booking.canPassengerAccess(GUI.getSurnameInput(), GUI.getLastNameInput())) {
                    BaggageSize passengerBaggage = new BaggageSize(GUI.getBaggageWeightInput(), GUI.getBaggageVolumeInput());
                    if (passengerBaggage.isValidSize()) {
                        checkIn.checkInPassenger(booking, passengerBaggage);
                        // if passenger baggage are over capacity, the GUI shows a dialog and the user has to
                        // to be able to check in
                        if (passengerBaggage.isOverCapacity(booking.getBaggageSize())) {
                            if (GUI.printOverCapacityConfirmDialog(passengerBaggage.calculateOverCapacityPrice(booking.getBaggageSize()))) {
                                GUI.clear();
                            }
                        } else {
                            GUI.clear();
                        }
                        // when every passenger have checked in the program print the report
                        if (bookingNumber.equals(checkIn.getCheckInPassenger())) {
                            GUI.close();
                            checkIn.writeToFile("Report.txt", checkIn.makeReport());
                        }
                    } else {
                        GUI.setMessage("Invalid baggage size inputs");
                    }

                } else {
                    GUI.setMessage("Invalid passenger inputs");
                }
            }
        };
        GUI.setOnConfirm(handleConfirm);
        
    }
    
    /**
	 * Write the informations in the file provided in parameters.
	 * --- > This method comes from F21SF course lab and re-used in F21SF coursework <-----
	 * @param filename the file in which to write
	 * @param text the text to write in the file
	 */
    public void writeToFile(String filename, String text) {
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
	public String makeReport() {
		String report = "List of flight's statistics after check-in\nFlight code       Nb passengers   Total weight   Total volume  "
				+ "Excess baggage fees\n";
		report += "---------------------------------------------------------------------------------------------";
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
