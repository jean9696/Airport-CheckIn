package controller;

import model.collection.PassengerQueue;
import model.entity.CheckInDesk;
import model.entity.Log;
import model.entity.Passenger;
import FileHelper;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class CheckInDeskController {
    private CheckInDesk checkInDesk;

    public CheckInDeskController(CheckInDesk checkInDesk) {
        this.checkInDesk = checkInDesk;
    }

    private void checkInPassenger(Passenger passenger) {
        checkInDesk.setCurrentPassenger(passenger);
        if (checkInDesk.getCurrentPassenger() != null) {
            try {
                // checkIn process last a random time between 1/2sec and 3sec
                Log.getInstance().addToLog(checkInDesk.getCurrentPassenger().getSurname() + " " + checkInDesk.getCurrentPassenger().getLastname() + " is checking in");
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.getInstance().addToLog(checkInDesk.getCurrentPassenger().getSurname() + " " + checkInDesk.getCurrentPassenger().getLastname() + " has checked in");
            // TODO: checkIn the passenger
            System.out.println(checkInDesk.getCurrentPassenger() + " has checked in"); // TODO: remove that
        }
    }

    /**
     * @param passengerQueue
     * @param openTime in seconds
     */
    public void simulateCheckIn(final PassengerQueue passengerQueue, final long openTime) {
        final CheckInDesk checkInDesk = this.checkInDesk;
        new Thread(new Runnable() {
            public void run() {
                long timer = new Date().getTime();
                while (new Date().getTime() - timer < openTime * 1000) {
                    checkInPassenger(passengerQueue.poll());
                }
                checkInDesk.close();
                Log.getInstance().addToLog("Checkin desk " + checkInDesk.getId() + " has closed");
                System.out.println("CheckIn desk " + checkInDesk.getId() + " is closing after " + ((new Date().getTime() - timer) / 1000) + "s");
            }
        }).start();
        System.out.println("write to file called here");
        FileHelper.writeToFile("Report.txt",Log.getInstance().getLog());
    }
}
