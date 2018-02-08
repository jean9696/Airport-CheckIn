import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class CheckInTest {

    @Test
    public void readFlightsFromInputFiles() {
        HashMap<String, Flight> testFlights = CheckIn.readFlightsFromInputFiles();
        BaggageSize testBaggage = new BaggageSize(500, 15000);
        Flight testFlight = new Flight("789", "Paris","AirFrance", 100, testBaggage);
        assertEquals("Error in testFlight: Flight Code",
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
        assertEquals("Error in testFlight: Passenger Capacity",
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
    }

    @Test
    public void readBookingsFromInputFiles() {
        HashMap<String, Flight> testFlights = CheckIn.readFlightsFromInputFiles();
        HashMap<String, Booking> testBookings = CheckIn.readBookingsFromInputFiles(testFlights);
        Flight testFlight = CheckIn.readFlightsFromInputFiles().get("789");
        BaggageSize testBaggage = new BaggageSize(2, 1);
        Passenger testPassenger = new Passenger("Bob", "Bobson", 13);
        Booking testBooking = new Booking(123, true, testFlight, testBaggage, testPassenger);
        assertEquals("Error in readBookings: Booking ID",
                testBooking.getBookId(),
                testBookings.get("123").getBookId()
        );
        assertEquals("Error in readBookings: Passenger Last Name",
                testBooking.getPassenger().getLastname(),
                testBookings.get("123").getPassenger().getLastname()
        );
        assertEquals("Error in readBookings: Passenger First Name",
                testBooking.getPassenger().getSurname(),
                testBookings.get("123").getPassenger().getSurname()
        );
        assertEquals("Error in readBookings: Passenger Age",
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



    }
}