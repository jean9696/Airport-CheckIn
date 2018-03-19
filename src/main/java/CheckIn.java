import controller.CheckInDeskController;
import controller.QueueController;
import helpers.FileHelper;
import model.collection.BookingList;
import model.collection.FlightList;
import model.collection.PassengerList;
import model.collection.PassengerQueue;
import model.entity.CheckInDesk;
import model.entity.Flight;

import java.util.LinkedList;

public class CheckIn {


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
        LinkedList<CheckInDesk> deskList = new LinkedList<>();
        for (int i=0;i<2;i++) {
            CheckInDesk checkInDesk = new CheckInDesk(i);
            CheckInDeskController checkInDeskController = new CheckInDeskController(checkInDesk);
            checkInDeskController.simulateCheckIn(passengerQueue, 60); //async
            deskList.add(checkInDesk);
        }

        //Flight(s)
        for (Flight flight : FlightList.getInstance().values()) {
            // TODO: just print the view
        }

        //run GUI with obervables
        final view.GUI GUI = new view.GUI(passengerQueue, deskList, FlightList.getInstance());
    }
}
