import model.entity.PassengerInformation;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
* view.GUI Tester.
* 
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0 
*/ 
public class PassengerInformationTest {

    static public PassengerInformation createTestPassenger() throws Exception {
        return new PassengerInformation("Jon", "Doe", 48);
    }

    @Test
    public void setPassenger() {
        boolean thrown = false;

        try {
            new PassengerInformation("", "", -10);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

} 
