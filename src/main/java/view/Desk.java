package view;

import model.entity.Passenger;
import model.entity.CheckInDesk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Desk extends JPanel implements Observer {

    private String name;
    private JLabel deskText = new JLabel();
    private JButton deskButton = new JButton();
    private CheckInDesk desk;
    private String closeText = "Close Desk";
    private String openText = "Open Desk";

    public Desk(String name, Observable observable) {
        super();
        this.name = name;
        observable.addObserver(this);
        this.desk = (CheckInDesk) observable;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Passenger) {
            updateDesk((Passenger) arg);
        }
        else if (arg instanceof Boolean) {
            System.out.println("Here");
            if ((Boolean) arg == true) {
                setDeskText(closeText);
                System.out.println("Opening Desk");
            } else {
                setDeskText(openText);
                emptyDesk();
                System.out.println("Closing Desk");
            }
        }
        else {
            emptyDesk();
        }
    }

    public JPanel setupDesk() {
        JPanel deskPanel = new JPanel();
        deskPanel.setLayout(new GridLayout(3, 1));
        deskPanel.add(new JLabel(this.name));
        emptyDesk();
        deskPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        deskPanel.add(deskText);
        setupButton();
        deskPanel.add(deskButton);
        return deskPanel;
    }

    private void updateDesk(Passenger person) {
        deskText.setText(person.getFirstname() + " " + person.getLastname() + " is dropping of 1 bag of " +
                person.getBaggage().getWeight() + "kg. A baggage fee of £" +
                        person.getBaggage().calculateOverCapacityPrice(person.getBaggage()) + " is due"
        );
    }

    private void emptyDesk() {
        deskText.setText("No passenger is being served");
    }

    private void setupButton() {
        deskButton.setText(closeText);

        deskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (desk.isOpen()) {
                    closeDesk();
                }
                else {
                    openDesk();
                }
            }
        });
    }

    private void openDesk() {
        desk.open();
    }

    private void closeDesk() {
        desk.close();

    }

    private void setDeskText(String text) {
        deskButton.setText(text);
    }
}
