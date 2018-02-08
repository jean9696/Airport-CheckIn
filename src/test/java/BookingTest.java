import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/** 
* GUI Tester. 
* 
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0 
*/ 
public class BookingTest {

    static Booking createTestBooking() {
        return new Booking(10, false, FlightTest.createTestFlight(), BaggageSizeTest.createTestBaggageSize(), PassengerTest.createTestPassenger());
    }

    @Test
    public void convertJTextStringToInt() {
        GUI GUI = new GUI();
        assertEquals(GUI.convertJTextStringToInt(""),  null);
        assertEquals(GUI.convertJTextStringToInt("0"),  new Integer(0));
        assertEquals(GUI.convertJTextStringToInt("test"),  null);
    }

    @Test
    public void canPassengerAccess() {
        Booking booking = createTestBooking();
        // we know that in a test booking the passenger is Jon Doe
        assertEquals(booking.canPassengerAccess("Jon", "Doe"), new Boolean(true));
        assertEquals(booking.canPassengerAccess("jon", "doe"), new Boolean(true));
        assertEquals(booking.canPassengerAccess("JON", "DOE"), new Boolean(true));
        assertEquals(booking.canPassengerAccess("Jerem", "Star"), new Boolean(false));
    }

} 
