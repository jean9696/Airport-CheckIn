package model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private String logReport;

    private static Log ourInstance = new Log();

    public static Log getInstance() {
        return ourInstance;
    }

    private Log() {
        logReport = "Logs of the previous simulation:\n";
    }

    /**
     * Method to add some text to the logReport variable
     * @param text Text to be added
     * @return The updated logReport string
     */
    public String addToLog(String text) {
        logReport += getCurrentTimeStamp() + ": " + text + "\n";
        return logReport;
    }

    /**
     * Method to get the logReport variable
      * @return The logReport variable which contains all the logs
     */
    public String getLog() {
        return logReport;
    }

    /**
     * @return the current time stamp
     */
    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
