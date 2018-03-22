import controller.CheckInDeskController;
import controller.QueueController;
import controller.SettingsController;
import helpers.FileHelper;
import model.collection.BookingList;
import model.collection.FlightList;
import model.collection.PassengerList;
import model.collection.PassengerQueue;
import model.entity.CheckInDesk;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CheckIn {


    /**
     * @param args
     * Starting point of the program, initialize variables and launch the view.GUI with the listener
     */
    public static void main (String[] args) {

        // init config
        Integer initialQueueLength = 10;
        Integer checkInDeskNumber = 2;
        Integer openTime = 60;
        final SettingsController settingsController = new SettingsController(initialQueueLength, checkInDeskNumber, openTime);

        ActionListener onConfirmSettings = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    start(
                        settingsController.getInitialQueueLength(),
                        settingsController.getCheckInDeskNumber(),
                        settingsController.getOpenTime()
                    );
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
            }
        };
        new view.Settings(settingsController, onConfirmSettings);
    }

    public static void start(Integer initialQueueLength, Integer checkInDeskNumber, Integer openTime) throws Exception {

        FileHelper.readFlightsFromInputFiles();
        FileHelper.readBookingsFromInputFiles();

        PassengerList passengers = BookingList.getInstance().getPassengerList();

        // Queue
        PassengerQueue passengerQueue = new PassengerQueue(passengers.getRandomPassengersToQueue(initialQueueLength));
        QueueController queueController = new QueueController(passengerQueue);
        queueController.simulatePassengerArrival(passengers); // async

        //Desk(s)
        LinkedList<CheckInDesk> deskList = new LinkedList<>();
        for (int i=0;i<checkInDeskNumber;i++) {
            CheckInDesk checkInDesk = new CheckInDesk(i);
            CheckInDeskController checkInDeskController = new CheckInDeskController(checkInDesk);
            checkInDeskController.simulateCheckIn(passengerQueue, openTime); //async
            deskList.add(checkInDesk);
        }

        //run GUI with obervables
        new view.GUI(passengerQueue, deskList, FlightList.getInstance());
    }
}
