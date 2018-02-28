package controller;

import model.collection.PassengerList;
import model.collection.PassengerQueue;
import model.entity.Passenger;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QueueController {

    private PassengerQueue passengerQueue;

    /**
     * Constructpr
     * @param passengerQueue
     */
    public QueueController(PassengerQueue passengerQueue) {
        this.passengerQueue = passengerQueue;
    }

    /**
     * @param passengers
     * Simulate the passenger arrival asynchronously
     */
    public void simulatePassengerArrival(final PassengerList passengers) {
        final PassengerQueue passengerQueue = this.passengerQueue;
        new Thread(new Runnable() {
            public void run() {
                while (!passengers.isEmpty()) {
                    int index = new Random().nextInt(passengers.size());
                    Passenger randomPassenger = passengers.get(index);
                    passengerQueue.add(randomPassenger);
                    passengers.remove(randomPassenger);
                    System.out.println(randomPassenger + " just arrived");  //TODO: remove that
                    try {
                        // new passenger arrive at random time bewten 1/2sec and 3sec
                        Thread.sleep(ThreadLocalRandom.current().nextInt(500, 3000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
