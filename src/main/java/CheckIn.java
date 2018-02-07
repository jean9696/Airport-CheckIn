import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
        return new HashMap<String, Flight>();
    }

    public static HashMap<String, Booking> readBookingsFromInputFiles() {
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
