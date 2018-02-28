import model.entity.BaggageSize;
import model.entity.Booking;
import model.entity.Flight;
import model.entity.PassengerInformation;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileHelperTest {

    @Test
    public void readFlightsFromInputFiles() {
        HashMap<String, Flight> testFlights = FileHelper.readFlightsFromInputFiles();
        BaggageSize testBaggage = new BaggageSize(500, 15000);
        Flight testFlight = new Flight("789", "Paris","AirFrance", 100, testBaggage);

        // Equal Tests
        assertEquals("Error in testFlight: model.entity.Flight Code",
                testFlight.getFlightCode(),
                testFlights.get("789").getFlightCode()
        );
        assertEquals("Error in testFlight: Destination Airport",
                testFlight.getDestinationAirport(),
                testFlights.get("789").getDestinationAirport()
        );
        assertEquals("Error in testFlight: Carrier",
                testFlight.getCarrier(),
                testFlights.get("789").getCarrier()
        );
        assertEquals("Error in testFlight: model.entity.PassengerInformation Capacity",
                testFlight.getPassengerCapacity(),
                testFlights.get("789").getPassengerCapacity()
        );
        assertEquals("Error in testFlight: Baggage Volume",
                testFlight.getBaggageCapacity().getVolume(),
                testFlights.get("789").getBaggageCapacity().getVolume()
        );
        assertEquals("Error in testFlight: Baggage Weight",
                testFlight.getBaggageCapacity().getWeight(),
                testFlights.get("789").getBaggageCapacity().getWeight()
        );

        // Not Equal Tests
        assertNotEquals("Error in testFlight: model.entity.Flight Code",
                testFlight.getFlightCode(),
                testFlights.get("123").getFlightCode()
        );
        assertNotEquals("Error in testFlight: Destination Airport",
                testFlight.getDestinationAirport(),
                testFlights.get("123").getDestinationAirport()
        );
        assertNotEquals("Error in testFlight: Carrier",
                testFlight.getCarrier(),
                testFlights.get("123").getCarrier()
        );
        assertNotEquals("Error in testFlight: model.entity.PassengerInformation Capacity",
                testFlight.getPassengerCapacity(),
                testFlights.get("123").getPassengerCapacity()
        );
        assertNotEquals("Error in testFlight: Baggage Volume",
                testFlight.getBaggageCapacity().getVolume(),
                testFlights.get("123").getBaggageCapacity().getVolume()
        );
        assertNotEquals("Error in testFlight: Baggage Weight",
                testFlight.getBaggageCapacity().getWeight(),
                testFlights.get("123").getBaggageCapacity().getWeight()
        );

    }

    @Test
    public void readBookingsFromInputFiles() throws Exception {
        HashMap<String, Flight> testFlights = FileHelper.readFlightsFromInputFiles();
        HashMap<String, Booking> testBookings = FileHelper.readBookingsFromInputFiles(testFlights);
        Flight testFlight = FileHelper.readFlightsFromInputFiles().get("789");
        BaggageSize testBaggage = new BaggageSize(2, 1);
        PassengerInformation testPassenger = new PassengerInformation("Bob", "Bobson", 13);
        Booking testBooking = new Booking(123, false, testFlight, testBaggage, testPassenger);

        // Equals Tests
        assertEquals("Error in readBookings: Booking ID",
                testBooking.getBookId(),
                testBookings.get("123").getBookId()
        );
        assertEquals("Error in readBookings: PassengerInformation Last Name",
                testBooking.getPassenger().getLastname(),
                testBookings.get("123").getPassenger().getLastname()
        );
        assertEquals("Error in readBookings: PassengerInformation First Name",
                testBooking.getPassenger().getSurname(),
                testBookings.get("123").getPassenger().getSurname()
        );
        assertEquals("Error in readBookings: PassengerInformation Age",
                testBooking.getPassenger().getAge(),
                testBookings.get("123").getPassenger().getAge()
        );
        assertEquals("Error in readBookings: Baggage Weight",
                testBooking.getBaggageSize().getWeight(),
                testBookings.get("123").getBaggageSize().getWeight()
        );
        assertEquals("Error in readBookings: Baggage Volume",
                testBooking.getBaggageSize().getVolume(),
                testBookings.get("123").getBaggageSize().getVolume()
        );
        assertEquals("Error in readBookings: Checked In",
                testBooking.getCheckedIn(),
                testBookings.get("123").getCheckedIn()
        );
        assertEquals("Error in readBookings: Flight Code",
                testBooking.getFlight().getFlightCode(),
                testBookings.get("123").getFlight().getFlightCode()
        );

        // Not Equals Tests
        assertNotEquals("Error in readBookings: Booking ID",
                testBooking.getBookId(),
                testBookings.get("456").getBookId()
        );
        assertNotEquals("Error in readBookings: PassengerInformation Last Name",
                testBooking.getPassenger().getLastname(),
                testBookings.get("456").getPassenger().getLastname()
        );
        assertNotEquals("Error in readBookings: PassengerInformation First Name",
                testBooking.getPassenger().getSurname(),
                testBookings.get("456").getPassenger().getSurname()
        );
        assertNotEquals("Error in readBookings: PassengerInformation Age",
                testBooking.getPassenger().getAge(),
                testBookings.get("456").getPassenger().getAge()
        );
        assertNotEquals("Error in readBookings: Baggage Weight",
                testBooking.getBaggageSize().getWeight(),
                testBookings.get("456").getBaggageSize().getWeight()
        );
        assertNotEquals("Error in readBookings: Baggage Volume",
                testBooking.getBaggageSize().getVolume(),
                testBookings.get("456").getBaggageSize().getVolume()
        );

    }

}
