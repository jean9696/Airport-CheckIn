package model.collection;

import model.entity.Booking;
import model.entity.Passenger;

import java.util.HashMap;

public class BookingList extends HashMap<String,Booking>{
    private static BookingList ourInstance = new BookingList();

    public static BookingList getInstance() {
        return ourInstance;
    }

    private BookingList() {
        super();
    }

    public PassengerList getPassengerList() throws Exception {
        PassengerList passengers = new PassengerList();
        for (Booking booking : BookingList.getInstance().values()) {
            passengers.add(new Passenger(booking.getPassenger(), booking.getBookId()));
        }
        return passengers;
    }

}
