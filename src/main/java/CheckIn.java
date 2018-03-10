import controller.CheckInDeskController;
import controller.QueueController;
import model.collection.BookingList;
import model.collection.FlightList;
import model.collection.PassengerList;
import model.collection.PassengerQueue;
import model.entity.*;
import view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Contains flights and bookings available in database
 * Has the main function that displays the view.GUI to checkIn passengers
 */
public class CheckIn {

    private Integer checkInPassenger;

    /**
     */
    public CheckIn() {
        this.checkInPassenger = 0;
    }


    /**
     * @return how many passenger has checked in
     */
    public Integer getCheckInPassenger() {
        return checkInPassenger;
    }

    /**
     * Check in the passenger by incrementing the total of passengers who have checkedIn
     * and set add this passenger baggage to the corresponding flight
     * @param booking
     * @param passengerBaggage
     */
    public void checkInPassenger(Booking booking, BaggageSize passengerBaggage) {
        booking.setCheckedIn(true);
        Flight passengerFlight = booking.getFlight();
        passengerFlight.addOnePassenger();
        passengerFlight.addBaggageRegistered(passengerBaggage);
        checkInPassenger++;
    }

    /**
     * Polymorphism of checkInPassenger above to increment extra fees counter
     * @param booking
     * @param passengerBaggage
     * @param extraFees
     */
    public void checkInPassenger (Booking booking, BaggageSize passengerBaggage, Integer extraFees) {
        Flight passengerFlight = booking.getFlight();
        passengerFlight.addExtraFees(extraFees);
        checkInPassenger(booking, passengerBaggage);
    }

    /**
     * @param args
     * Starting point of the program, initialize variables and launch the view.GUI with the listener
     */
    public static void main (String[] args) throws Exception {

        FileHelper.readFlightsFromInputFiles();
        FileHelper.readBookingsFromInputFiles();

        PassengerList passengers = BookingList.getInstance().getPassengerList();


        // Queue
        PassengerQueue passengerQueue = new PassengerQueue(passengers.getRandomPassengersToQueue(10));
        QueueController queueController = new QueueController(passengerQueue);
        queueController.simulatePassengerArrival(passengers); // async

        //Desk(s)
       for (int i=0;i<2;i++) {
            CheckInDesk checkInDesk = new CheckInDesk(i);
            CheckInDeskController checkInDeskController = new CheckInDeskController(checkInDesk);
            checkInDeskController.simulateCheckIn(passengerQueue, 60); //async
       }


        //Flight(s)
        for (Flight flight : FlightList.getInstance().values()) {
            // TODO: just print the view
        }

        //run GUI with obervables

    }



}
