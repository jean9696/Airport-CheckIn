package view;

import model.entity.Passenger;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Queue extends JPanel implements Observer {

    public Queue (Observable observable) {
        observable.addObserver(this);
        passengerArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        passengerArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        queuePanel.setLayout(new GridLayout(1, 1));
        queuePanel.add(scrollPane);
    }

    JLabel queueText = new JLabel();
    JPanel queuePanel = new JPanel();
    JTextArea passengerArea = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(passengerArea);
    LinkedList<Passenger> passengers;

    @Override
    public void update(Observable o, Object arg) {
        passengers = (LinkedList<Passenger>) arg;
        if (passengers.size() > 0) {
            queueText.setText("There are currently " + passengers.size() + " people in the queue");
            setupQueue(passengers);
        } else {
            queueText.setText("There are currently " + 0 + " people in the queue");
            setupQueue(new LinkedList<Passenger>());
        }
    }

    public JPanel setupAmountInQueue() {
        //Set up the line for the text
        JPanel amountInQueuePanel = new JPanel();
        amountInQueuePanel.setLayout(new GridLayout(1, 1));
        queueText.setText("The queue is empty");
        amountInQueuePanel.add(this.queueText);
        return amountInQueuePanel;
    }

    public JPanel setupQueue(LinkedList<Passenger> passengers) {
        passengerArea.setText("");
        for (Passenger passenger : passengers) {
            passengerArea.append(passenger.getBookingId().toString() + " ");
            passengerArea.append(passenger.getFirstname() + " ");
            passengerArea.append(passenger.getLastname() + ", ");
            passengerArea.append("Baggage: " + passenger.getBaggage().getWeight().toString() + "kg, ");
            passengerArea.append(passenger.getBaggage().getVolume().toString() + " liters\n");
        }
        return queuePanel;
    }
}
