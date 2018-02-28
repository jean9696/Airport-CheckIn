import model.Passenger;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
* view.GUI Tester.
* 
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0 
*/ 
public class PassengerTest {

    static public Passenger createTestPassenger() throws Exception {
        return new Passenger("Jon", "Doe", 48);
    }

    @Test
    public void setPassenger() {
        boolean thrown = false;

        try {
            new Passenger("", "", -10);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

} 
