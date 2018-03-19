import org.junit.Test;
import view.GUI;

import static junit.framework.Assert.assertEquals;

/** 
* view.GUI Tester.
* 
* @author Jean Dessane
* @since feb. 7, 2018
* @version 1.0 
*/ 
public class GUITest { 


    @Test
    public void convertJTextStringToInt() {
        GUI GUI = new GUI();
        assertEquals(GUI.convertJTextStringToInt(""),  null);
        assertEquals(GUI.convertJTextStringToInt("0"),  new Integer(0));
        assertEquals(GUI.convertJTextStringToInt("test"),  null);
    }

    @Test
    public void clear() {
        GUI GUI = new GUI();
        GUI.clear();
        assertEquals(GUI.getBaggageVolumeInput(),  null);
        assertEquals(GUI.getBaggageWeightInput(),  null);
        assertEquals(GUI.getLastNameInput(),  "");
        assertEquals(GUI.getFirstnameInput(),  "");
        assertEquals(GUI.getBookingReferenceInput(),  "");
    }

} 
