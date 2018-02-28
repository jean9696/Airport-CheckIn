package model.collection;

public class FlightList {
    private static FlightList ourInstance = new FlightList();

    public static FlightList getInstance() {
        return ourInstance;
    }

    private FlightList() {
    }
}
