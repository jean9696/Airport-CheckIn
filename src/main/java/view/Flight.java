package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Flight extends JPanel implements Observer {

    private JLabel flightText = new JLabel();
    private JLabel passengersCheckedIn = new JLabel();
    private JLabel baggageCheckedIn = new JLabel();
    private model.entity.Flight flight;

    public Flight(Observable observable) {
        observable.addObserver(this);
        flight = (model.entity.Flight) observable;
    }

    @Override
    public void update(Observable o, Object arg) {
        setPassengerText();
        setBaggageText();
    }

    public JPanel setupFlightPanel() {
        JPanel flightPanel = new JPanel();
        flightPanel.setLayout(new GridLayout(3, 1));
        flightText.setText(flight.getFlightCode() + " " + flight.getDestinationAirport());
        setPassengerText();
        setBaggageText();
        flightPanel.add(flightText);
        flightPanel.add(passengersCheckedIn);
        flightPanel.add(baggageCheckedIn);
        flightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return flightPanel;
    }

    private void setPassengerText() {
        passengersCheckedIn.setText(flight.getNbPassengersRegistered() + " passengers checked in");
    }

    private void setBaggageText() {
        baggageCheckedIn.setText(flight.getBaggageRegistered().getWeight() + "kg of baggage checked in");
    }
}
