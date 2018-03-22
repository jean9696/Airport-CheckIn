package view;

import model.collection.BookingList;
import model.entity.BaggageSize;
import model.entity.Passenger;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Desk extends JPanel implements Observer {

    private String name;
    private JLabel deskText = new JLabel();

    public Desk(String name, Observable observable) {
        super();
        this.name = name;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Passenger)
            updateDesk((Passenger) arg);
        else
            emptyDesk();
    }

    public JPanel setupDesk() {
        JPanel deskPanel = new JPanel();
        deskPanel.setLayout(new GridLayout(2, 1));
        deskPanel.add(new JLabel(this.name));
        emptyDesk();
        deskPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        deskPanel.add(deskText);
        return deskPanel;
    }

    private void updateDesk(Passenger person) {
        BaggageSize bookingBaggageSize = BookingList.getInstance().get(person.getBookingId()).getBaggageSize();
        deskText.setText(person.getFirstname() + " " + person.getLastname() + " is dropping of 1 bag of " +
                person.getBaggage().getWeight() + "kg. A baggage fee of £" +
                        person.getBaggage().calculateOverCapacityPrice(bookingBaggageSize) + " is due"
        );
    }

    private void emptyDesk() {
        deskText.setText("No passenger is being served");
    }
}
