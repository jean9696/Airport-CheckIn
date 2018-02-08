import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import java.io.*;

import com.sun.corba.se.impl.logging.InterceptorsSystemException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public void checkInPassenger() {
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
                System.out.println(flightCode);

                String destinationAirport = (String) flight.get("Destination Airport");
                System.out.println(destinationAirport);

                String carrier = (String) flight.get("Carrier");
                System.out.println(carrier);

                int maximumPassenger = parseInt(flight.get("Maximum Passengers").toString());
                System.out.println(maximumPassenger);

                int maximumBaggageWeight = parseInt(flight.get("Maximum Baggage Weight").toString());
                System.out.println(maximumBaggageWeight);

                int maximumBaggageVolume = parseInt(flight.get("Maximum Baggage Volume").toString());
                System.out.println(maximumBaggageVolume);

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
                System.out.println(passengerName);

                String firstName = passengerName.split(" ", 2)[0];
                String lastName = passengerName.split(" ", 2)[1];
                System.out.println(firstName);
                System.out.println(lastName);

                int passengerAge = Integer.parseInt(booking.get("Passenger Age").toString());
                System.out.println(passengerAge);

                int bookingReferenceCode = parseInt(booking.get("Booking Reference Code").toString());
                System.out.println(bookingReferenceCode);

                int flightCode = parseInt(booking.get("Flight Code").toString());
                System.out.println(flightCode);

                Boolean checkedIn = Boolean.valueOf((String) booking.get("Checked In"));
                System.out.println(checkedIn);

                int baggageWeight = parseInt(booking.get("Baggage Weight").toString());
                System.out.println(baggageWeight);

                int baggageVolume = parseInt(booking.get("Baggage Volume").toString());
                System.out.println(baggageVolume);

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
                    checkIn.checkInPassenger();
                    // TODO: Check baggage sizes and print an error message if necessary
                    if (bookingNumber.equals(checkIn.getCheckInPassenger())) {
                        GUI.close();
                        // TODO: Print report
                    }
                    GUI.clear();
                } else {
                    GUI.setMessage("Something went wrong");
                }
            }
        };
        GUI.setOnConfirm(handleConfirm);
    }
}
