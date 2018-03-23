package view;

import model.collection.PassengerQueue;
import model.entity.Passenger;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Queue extends JPanel implements Observer {

    private JLabel queueText = new JLabel();
    private JPanel queuePanel = new JPanel();
    private JTextArea passengerArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(passengerArea);
    private PassengerQueue queue;

    public Queue (Observable observable) {
        observable.addObserver(this);
        passengerArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        passengerArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        queuePanel.setLayout(new GridLayout(1, 1));
        queuePanel.add(scrollPane);
        queue = (PassengerQueue) observable;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (queue.size() > 0) {
            queueText.setText("There are currently " + queue.size() + " people in the queue");
            setupQueue(queue.getPassengers());
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
