import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class CheckInTest {

    CheckIn checkIn;

    @Before
    public void setUp() throws Exception {
        HashMap<String, Flight> flights = new HashMap<String, Flight>();
        HashMap<String, Booking> bookings = new HashMap<String, Booking>();
        checkIn = new CheckIn(flights, bookings);

    }

    @Test
    public void checkInPassenger() throws Exception {
        Booking booking = BookingTest.createTestBooking();
        BaggageSize baggageSize = BaggageSizeTest.createTestBaggageSize();
        checkIn.checkInPassenger(booking, baggageSize);
        assertEquals(booking.getCheckedIn(), new Boolean(true));
    }

    @Test
    public void addCheckInPassenger() throws Exception {
        Booking booking = BookingTest.createTestBooking();
        BaggageSize baggageSize = BaggageSizeTest.createTestBaggageSize();
        Integer currentCheckedInPassengerNumber = checkIn.getCheckInPassenger();
        checkIn.checkInPassenger(booking, baggageSize);
        assertEquals(checkIn.getCheckInPassenger(), new Integer(currentCheckedInPassengerNumber + 1));
    }

    @Test
    public void addPassengerToFlight() throws Exception {
        Booking booking = BookingTest.createTestBooking();
        BaggageSize baggageSize = BaggageSizeTest.createTestBaggageSize();
        Integer currentRegisterdPassengerNumber = booking.getFlight().getNbPassengersRegistered();
        BaggageSize currentRegisterdBaggageSize = booking.getFlight().getBaggageRegistered();
        checkIn.checkInPassenger(booking, baggageSize);
        currentRegisterdBaggageSize.addBaggage(baggageSize);
        assertEquals(booking.getFlight().getNbPassengersRegistered(), currentRegisterdPassengerNumber + 1);
        assertEquals(booking.getFlight().getBaggageRegistered(), currentRegisterdBaggageSize);
    }


}