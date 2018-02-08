import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
* GUI Tester.
*
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0
*/
public class BaggageSizeTest {

    static public BaggageSize createTestBaggageSize() {
        return new BaggageSize(10, 10);
    }

    @Test
    public void isOverWeight() {
        BaggageSize refBaggage = new BaggageSize(10, 10);
        assertEquals(createTestBaggageSize().isOverWeight(refBaggage), new Boolean(false));
        refBaggage.setWeight(5);
        assertEquals(createTestBaggageSize().isOverWeight(refBaggage), new Boolean(true));
        refBaggage.setWeight(15);
        assertEquals(createTestBaggageSize().isOverWeight(refBaggage), new Boolean(false));
    }

    @Test
    public void isOverVolume() {
        BaggageSize refBaggage = new BaggageSize(10, 10);
        assertEquals(createTestBaggageSize().isOverVolume(refBaggage), new Boolean(false));
        refBaggage.setVolume(5);
        assertEquals(createTestBaggageSize().isOverVolume(refBaggage), new Boolean(true));
        refBaggage.setVolume(15);
        assertEquals(createTestBaggageSize().isOverVolume(refBaggage), new Boolean(false));
    }

    @Test
    public void isOverCapacity() {
        BaggageSize refBaggage = new BaggageSize(10, 10);
        assertEquals(createTestBaggageSize().isOverCapacity(refBaggage), new Boolean(false));
        refBaggage = new BaggageSize(5, 10);
        assertEquals(createTestBaggageSize().isOverCapacity(refBaggage), new Boolean(true));
        refBaggage = new BaggageSize(10, 5);
        assertEquals(createTestBaggageSize().isOverCapacity(refBaggage), new Boolean(true));
        refBaggage = new BaggageSize(15, 5);
        assertEquals(createTestBaggageSize().isOverCapacity(refBaggage), new Boolean(true));
        refBaggage = new BaggageSize(15, 15);
        assertEquals(createTestBaggageSize().isOverCapacity(refBaggage), new Boolean(false));
    }


} 