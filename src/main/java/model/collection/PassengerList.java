package model.collection;

import model.entity.Passenger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PassengerList extends ArrayList<Passenger> {

    public LinkedList<Passenger> getRandomPassengersToQueue(Integer size) throws Exception {
        LinkedList<Passenger> passengers = new LinkedList<Passenger>();
        for (int i=0; i<size; i++) {
            int index = new Random().nextInt(this.size());
            Passenger passenger = this.get(index);
            passengers.add(passenger);
            this.remove(index);
        }
        return passengers;
    }

}
