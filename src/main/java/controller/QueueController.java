package controller;

import model.collection.PassengerList;
import model.collection.PassengerQueue;
import model.entity.Log;
import model.entity.Passenger;

import java.util.Date;
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

    private void addPassengerToQueue(Passenger passenger) {
        passengerQueue.add(passenger);
        Log.getInstance().addToLog(passenger.getSurname() + " " + passenger.getLastname() + " has joined the queue");
        System.out.println(passenger + " just arrived");  //TODO: remove that
        try {
            // new passenger arrive at random time between 1/2sec and 3sec
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                    addPassengerToQueue(randomPassenger);
                    passengers.remove(randomPassenger);
                }
            }
        }).start();
    }
}
