package model.collection;

import model.entity.Flight;

import java.util.HashMap;

public class FlightList extends HashMap<String,Flight>{
    private static FlightList ourInstance = new FlightList();

    public static FlightList getInstance() {
        return ourInstance;
    }

    private FlightList() {
        super();
    }
}
