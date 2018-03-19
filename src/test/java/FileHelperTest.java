import helpers.FileHelper;
import model.collection.BookingList;
import model.collection.FlightList;
import model.entity.BaggageSize;
import model.entity.Booking;
import model.entity.Flight;
import model.entity.PassengerInformation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileHelperTest {

    @Test
    public void readFlightsFromInputFiles() {
        FileHelper.readFlightsFromInputFiles();
        BaggageSize testBaggage = new BaggageSize(500, 15000);
        Flight testFlight = new Flight("789", "Paris","AirFrance", 100, testBaggage);

        // Equal Tests
        assertEquals("Error in testFlight: model.entity.Flight Code",
                testFlight.getFlightCode(),
                FlightList.getInstance().get("789").getFlightCode()
        );
        assertEquals("Error in testFlight: Destination Airport",
                testFlight.getDestinationAirport(),
                FlightList.getInstance().get("789").getDestinationAirport()
        );
        assertEquals("Error in testFlight: Carrier",
                testFlight.getCarrier(),
                FlightList.getInstance().get("789").getCarrier()
        );
        assertEquals("Error in testFlight: model.entity.PassengerInformation Capacity",
                testFlight.getPassengerCapacity(),
                FlightList.getInstance().get("789").getPassengerCapacity()
        );
        assertEquals("Error in testFlight: Baggage Volume",
                testFlight.getBaggageCapacity().getVolume(),
                FlightList.getInstance().get("789").getBaggageCapacity().getVolume()
        );
        assertEquals("Error in testFlight: Baggage Weight",
                testFlight.getBaggageCapacity().getWeight(),
                FlightList.getInstance().get("789").getBaggageCapacity().getWeight()
        );

        // Not Equal Tests
        assertNotEquals("Error in testFlight: model.entity.Flight Code",
                testFlight.getFlightCode(),
                FlightList.getInstance().get("123").getFlightCode()
        );
        assertNotEquals("Error in testFlight: Destination Airport",
                testFlight.getDestinationAirport(),
                FlightList.getInstance().get("123").getDestinationAirport()
        );
        assertNotEquals("Error in testFlight: Carrier",
                testFlight.getCarrier(),
                FlightList.getInstance().get("123").getCarrier()
        );
        assertNotEquals("Error in testFlight: model.entity.PassengerInformation Capacity",
                testFlight.getPassengerCapacity(),
                FlightList.getInstance().get("123").getPassengerCapacity()
        );
        assertNotEquals("Error in testFlight: Baggage Volume",
                testFlight.getBaggageCapacity().getVolume(),
                FlightList.getInstance().get("123").getBaggageCapacity().getVolume()
        );
        assertNotEquals("Error in testFlight: Baggage Weight",
                testFlight.getBaggageCapacity().getWeight(),
                FlightList.getInstance().get("123").getBaggageCapacity().getWeight()
        );

    }

    @Test
    public void readBookingsFromInputFiles() throws Exception {
        FileHelper.readFlightsFromInputFiles();
        FileHelper.readBookingsFromInputFiles();
        Flight testFlight = FlightList.getInstance().get("789");
        BaggageSize testBaggage = new BaggageSize(2, 1);
        PassengerInformation testPassenger = new PassengerInformation("Bob", "Bobson", 13);
        Booking testBooking = new Booking(123, false, testFlight, testBaggage, testPassenger);

        // Equals Tests
        assertEquals("Error in readBookings: Booking ID",
                testBooking.getBookId(),
                BookingList.getInstance().get("123").getBookId()
        );
        assertEquals("Error in readBookings: PassengerInformation Last Name",
                testBooking.getPassenger().getLastname(),
                BookingList.getInstance().get("123").getPassenger().getLastname()
        );
        assertEquals("Error in readBookings: PassengerInformation First Name",
                testBooking.getPassenger().getFirstname(),
                BookingList.getInstance().get("123").getPassenger().getFirstname()
        );
        assertEquals("Error in readBookings: PassengerInformation Age",
                testBooking.getPassenger().getAge(),
                BookingList.getInstance().get("123").getPassenger().getAge()
        );
        assertEquals("Error in readBookings: Baggage Weight",
                testBooking.getBaggageSize().getWeight(),
                BookingList.getInstance().get("123").getBaggageSize().getWeight()
        );
        assertEquals("Error in readBookings: Baggage Volume",
                testBooking.getBaggageSize().getVolume(),
                BookingList.getInstance().get("123").getBaggageSize().getVolume()
        );
        assertEquals("Error in readBookings: Checked In",
                testBooking.getCheckedIn(),
                BookingList.getInstance().get("123").getCheckedIn()
        );
        assertEquals("Error in readBookings: Flight Code",
                testBooking.getFlight().getFlightCode(),
                BookingList.getInstance().get("123").getFlight().getFlightCode()
        );

        // Not Equals Tests
        assertNotEquals("Error in readBookings: Booking ID",
                testBooking.getBookId(),
                BookingList.getInstance().get("456").getBookId()
        );
        assertNotEquals("Error in readBookings: PassengerInformation Last Name",
                testBooking.getPassenger().getLastname(),
                BookingList.getInstance().get("456").getPassenger().getLastname()
        );
        assertNotEquals("Error in readBookings: PassengerInformation First Name",
                testBooking.getPassenger().getFirstname(),
                BookingList.getInstance().get("456").getPassenger().getFirstname()
        );
        assertNotEquals("Error in readBookings: PassengerInformation Age",
                testBooking.getPassenger().getAge(),
                BookingList.getInstance().get("456").getPassenger().getAge()
        );
        assertNotEquals("Error in readBookings: Baggage Weight",
                testBooking.getBaggageSize().getWeight(),
                BookingList.getInstance().get("456").getBaggageSize().getWeight()
        );
        assertNotEquals("Error in readBookings: Baggage Volume",
                testBooking.getBaggageSize().getVolume(),
                BookingList.getInstance().get("456").getBaggageSize().getVolume()
        );

    }

}
