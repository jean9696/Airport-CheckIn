package view;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Flight extends JPanel implements Observer {

    private JLabel flightText = new JLabel();
    private JLabel passengersCheckedIn = new JLabel();
    private JLabel baggageCheckedIn = new JLabel();

    public Flight(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        setPassengerText((model.entity.Flight) arg);
        setBaggageText((model.entity.Flight) arg);
    }

    public JPanel setupFlightPanel(model.entity.Flight flight) {
        JPanel flightPanel = new JPanel();
        flightPanel.setLayout(new GridLayout(3, 1));
        flightText.setText(flight.getFlightCode() + " " + flight.getDestinationAirport());
        setPassengerText(flight);
        setBaggageText(flight);
        flightPanel.add(flightText);
        flightPanel.add(passengersCheckedIn);
        flightPanel.add(baggageCheckedIn);
        flightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return flightPanel;
    }

    private void setPassengerText(model.entity.Flight flight) {
        passengersCheckedIn.setText(flight.getNbPassengersRegistered() + " passengers checked in");
    }

    private void setBaggageText(model.entity.Flight flight) {
        baggageCheckedIn.setText(flight.getBaggageRegistered().getWeight() + "kg of baggage checked in");
    }
}
