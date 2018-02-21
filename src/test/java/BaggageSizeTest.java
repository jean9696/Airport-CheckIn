import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
* BaggageSizeTest
*
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0
*/
public class BaggageSizeTest {

    static public BaggageSize createTestBaggageSize() {
        return new BaggageSize(10, 10);
    }

    BaggageSize refBaggage;

    BaggageSize testBaggage;

    @Before
    public void setUp() throws Exception {
        refBaggage = new BaggageSize(10, 10);
        testBaggage = createTestBaggageSize();
    }

    @Test
    public void isOverWeight() {
        assertEquals(testBaggage.isOverWeight(refBaggage), new Boolean(false));
        refBaggage.setWeight(5);
        assertEquals(testBaggage.isOverWeight(refBaggage), new Boolean(true));
        refBaggage.setWeight(15);
        assertEquals(testBaggage.isOverWeight(refBaggage), new Boolean(false));
    }

    @Test
    public void isOverVolume() {
        assertEquals(testBaggage.isOverVolume(refBaggage), new Boolean(false));
        refBaggage.setVolume(5);
        assertEquals(testBaggage.isOverVolume(refBaggage), new Boolean(true));
        refBaggage.setVolume(15);
        assertEquals(testBaggage.isOverVolume(refBaggage), new Boolean(false));
    }

    @Test
    public void isOverCapacity() {
        assertEquals(testBaggage.isOverCapacity(refBaggage), new Boolean(false));
        refBaggage = new BaggageSize(5, 10);
        assertEquals(testBaggage.isOverCapacity(refBaggage), new Boolean(true));
        refBaggage = new BaggageSize(10, 5);
        assertEquals(testBaggage.isOverCapacity(refBaggage), new Boolean(true));
        refBaggage = new BaggageSize(15, 5);
        assertEquals(testBaggage.isOverCapacity(refBaggage), new Boolean(true));
        refBaggage = new BaggageSize(15, 15);
        assertEquals(testBaggage.isOverCapacity(refBaggage), new Boolean(false));
    }

    @Test
    public void addBaggage() {
        testBaggage.addBaggage(refBaggage);
        assertEquals(testBaggage.getVolume(), new Integer(20));
        assertEquals(testBaggage.getWeight(), new Integer(20));

        refBaggage.setVolume(new Integer(21));
        refBaggage.setWeight(new Integer(14));
        testBaggage.addBaggage(refBaggage);
        assertEquals(testBaggage.getVolume(), new Integer(41));
        assertEquals(testBaggage.getWeight(), new Integer(34));
    }

    @Test
    public void isValidSize() {
        assertEquals(testBaggage.isValidSize(), new Boolean(true));
        testBaggage.setVolume(new Integer (-1));
        assertEquals(testBaggage.isValidSize(), new Boolean(false));
        testBaggage.setWeight(new Integer (-1));
        assertEquals(testBaggage.isValidSize(), new Boolean(false));
    }

    @Test
    public void calculateOverCapacityPrice() {
        testBaggage.setVolume(new Integer(20));
        assertEquals(testBaggage.calculateOverCapacityPrice(refBaggage), new Integer(20));
        testBaggage.setWeight(new Integer(20));
        assertEquals(testBaggage.calculateOverCapacityPrice(refBaggage), new Integer(70));
    }

}
