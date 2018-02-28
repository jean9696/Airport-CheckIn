import model.entity.Flight;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
* view.GUI Tester.
* 
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0 
*/ 
public class FlightTest {

    static public Flight createTestFlight() {
        return new Flight("0001", "Paris", "CDG", 150, BaggageSizeTest.createTestBaggageSize());
    }

    @Test
    public void createFligth() {
        Flight flight = createTestFlight();
        assertEquals(flight.getBaggageRegistered().getVolume(), new Integer(0));
        assertEquals(flight.getBaggageRegistered().getWeight(), new Integer(0));
        assertEquals(flight.getNbPassengersRegistered(), 0);
    }


} 
