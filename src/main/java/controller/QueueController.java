package controller;

import model.PassengerQueue;

import java.util.concurrent.ThreadLocalRandom;

public class QueueController {

    private PassengerQueue passengerQueue;

    /**
     * Constructor
     * @param passengerQueue
     */
    public QueueController(PassengerQueue passengerQueue) {
        this.passengerQueue = passengerQueue;
    }

    /**
     * @param loopSize
     * Simulate the passenger arrival asynchronously
     */
    public void simulatePassengerArrival(final Integer loopSize) {
        final PassengerQueue passengerQueue = this.passengerQueue;
        new Thread(new Runnable() {
            public void run() {
                for (int i=0; i<loopSize; i++) {
                    passengerQueue.addFakeRandomPassenger();
                    System.out.println(passengerQueue.poll() + " just arrived");  //TODO: remove that
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
