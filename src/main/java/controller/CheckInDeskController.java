package controller;

import helpers.FileHelper;
import model.collection.BookingList;
import model.collection.PassengerQueue;
import model.entity.*;

import javax.swing.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class CheckInDeskController {
    private CheckInDesk checkInDesk;

    public CheckInDeskController(CheckInDesk checkInDesk) {
        this.checkInDesk = checkInDesk;
    }

    /**
     * CheckIn the passenger
     * @param passenger
     */
    private void checkInPassenger(Passenger passenger) throws Exception {
        checkInDesk.setCurrentPassenger(passenger);
        if (checkInDesk.getCurrentPassenger() != null) {
            try {

                // checkIn process last a random time between 2sec and 5sec
                Log.getInstance().addToLog(checkInDesk.getCurrentPassenger().getSurname() + " " + checkInDesk.getCurrentPassenger().getLastname() + " is checking in");
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Passenger currentPassenger = checkInDesk.getCurrentPassenger();
            BookingList bookingList = BookingList.getInstance();
            Integer bookingId = currentPassenger.getBookingId();
            Booking passengerBooking = bookingList.get(bookingId);
            if (passengerBooking == null) {
                throw new Exception("Unable to get the booking #" + bookingId);
            }
            if (passengerBooking.canPassengerAccess(currentPassenger.getSurname(), currentPassenger.getLastname())) {
                passengerBooking.setCheckedIn(true);
                Flight passengerFlight = passengerBooking.getFlight();
                passengerFlight.addOnePassenger();
                passengerFlight.addBaggageRegistered(passengerBooking.getBaggageSize());
                Integer extraFees = currentPassenger.getBaggage().calculateOverCapacityPrice(passengerBooking.getBaggageSize());
                passengerFlight.addExtraFees(extraFees);
            }
        }
    }

    /**
     * Close the checkInDeskAssociated
     * @param timer
     */
    private void closeCheckInDesk(long timer) {
        this.checkInDesk.close();
        FileHelper.writeToFile("log.txt", Log.getInstance().getLog());
    }

    /**
     * @param passengerQueue
     * @param openTime in seconds
     */
    public void simulateCheckIn(final PassengerQueue passengerQueue, final long openTime) {
        new Thread(new Runnable() {
            public void run() {
                long timer = new Date().getTime();
                while (new Date().getTime() - timer < openTime * 1000) {
                    try {
                        checkInPassenger(passengerQueue.poll());
                    } catch (Exception e) {
                        // Print a message if something went wrong during the check in
                        JOptionPane.showMessageDialog(null, e);
                        e.printStackTrace();
                    }
                }
                checkInDesk.close();
                Log.getInstance().addToLog("Checkin desk " + checkInDesk.getId() + " has closed");
                closeCheckInDesk(timer);
            }
        }).start();
    }
}
