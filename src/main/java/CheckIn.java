import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static java.lang.Integer.parseInt;

public class CheckIn {
    private HashMap<String, Flight> flights;

    private HashMap<String, Booking> bookings;

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
            JSONArray flights = (JSONArray) jsonParser.parse(new FileReader("../../../flights.json"));

            for (Object o : flights)
            {
                JSONObject flight = (JSONObject) o;

                String flightCode = (String) flight.get("Flight Code");

                String destinationAirport = (String) flight.get("Destination Airport");
                System.out.println(destinationAirport);

                String carrier = (String) flight.get("Carrier");
                System.out.println(carrier);

                int maximumPassenger = parseInt((String) flight.get("Maximum Passengers"));
                int maximumBaggageWeight = parseInt((String) flight.get("Maximum Baggage Weight"));
                int maximumBaggageVolume = parseInt((String) flight.get("Maximum Baggage Volume"));

                BaggageSize baggage = new BaggageSize(maximumBaggageWeight, maximumBaggageVolume);
                Flight flightObject = new Flight(flightCode, destinationAirport, carrier, maximumPassenger, baggage);

                flightHashMap.put(flightObject.getFlightCode(), flightObject);
        }
        } catch (Exception e) {

        }
        return flightHashMap;
    }

    public static HashMap<String, Booking> readBookingsFromInputFiles() {

        JSONParser jsonParser = new JSONParser();
        HashMap<String, Booking> bookingHashMap = new HashMap<String, Booking>();

        try  {
            JSONArray bookings = (JSONArray) jsonParser.parse(new FileReader("../../../bookings.json"));

            for (Object o : bookings)
            {
                JSONObject booking = (JSONObject) o;

                String passengerName = (String) booking.get("Passenger Name");
                System.out.println(passengerName);

                String bookingReferenceCode = (String) booking.get("Booking Reference Code");
                System.out.println(bookingReferenceCode);

                int flightCode = parseInt((String) booking.get("Flight Code"));
                Boolean checkedIn = Boolean.valueOf((String) booking.get("Checked In"));

                int baggageWeight = parseInt((String) booking.get("Baggage Weight"));
                int baggageVolume = parseInt((String) booking.get("Baggage Volume"));

                Flight flight = flights.get();

                Passenger passenger = new Passenger("Bla", "Bla", 13);

                BaggageSize baggage = new BaggageSize(baggageWeight, baggageVolume);
                Booking bookingObject = new Booking(1, checkedIn, flightCode, baggage, passenger);
                bookingHashMap.put(bookingReferenceCode, );
            }
        } catch (Exception e) {

        }
        return new HashMap<String, Booking>();

    }

    public static void main (String[] args) {

        // should come from files
        HashMap<String, Flight> flights = readFlightsFromInputFiles();
        HashMap<String, Booking> bookings = readBookingsFromInputFiles();

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
