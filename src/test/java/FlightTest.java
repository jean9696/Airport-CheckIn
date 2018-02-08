/**
* GUI Tester. 
* 
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0 
*/ 
public class FlightTest {

    static public Flight createTestFlight() {
        return new Flight("0001", "Paris", "CDG", 150, BaggageSizeTest.createTestBaggageSize());
    }


} 
