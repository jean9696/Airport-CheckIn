package view;

import model.collection.PassengerQueue;
import model.entity.CheckInDesk;
import model.entity.Flight;
import model.entity.Passenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * view.GUI of the app
 */
public class GUI extends JFrame {

	public GUI (PassengerQueue queueModel, LinkedList<CheckInDesk> desks, HashMap<String, Flight> flightList) {

		// set up the title of the window
		setTitle("Airport");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		setMinimumSize(new Dimension(1400, 100));

		// call the three functions that set up the view.GUI
		setupNorthPanel(queueModel);
		setupCenterPanel(desks);
		setupSouthPanel(flightList);

		// pack and set visible
		pack();
		setVisible(true);

	}

	private void setupNorthPanel(PassengerQueue queueModel){
	    JPanel queuePanel = new JPanel();
	    // Use GridBagLayout for north panel to have different sized panels inside
	    GridBagLayout queueLayout = new GridBagLayout();
	    queuePanel.setLayout(queueLayout);
	    // Setup GridBagConstraints for the queue panel
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.weightx = 1.0;
	    gbc.weighty = 1.0;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.anchor = GridBagConstraints.NORTH;
	    gbc.gridwidth = GridBagConstraints.RELATIVE;
	    gbc.gridheight = GridBagConstraints.RELATIVE;
		Queue queue = new Queue(queueModel);
	    queuePanel.add(queue.setupAmountInQueue(), gbc);
	    gbc.gridy = 1;
	    gbc.anchor = GridBagConstraints.SOUTH;
        queuePanel.add(queue.setupQueue(new LinkedList<Passenger>()), gbc);
        this.add(queuePanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel(LinkedList<CheckInDesk> checkInDesks) {
        JPanel desksPanel = new JPanel();
        // Allow setup of multiple deksks
        desksPanel.setLayout(new GridLayout(1, checkInDesks.size()));
		for (CheckInDesk deskModel : checkInDesks) {
			Desk deskView = new Desk( "Desk " + deskModel.getId(), deskModel);
			desksPanel.add(deskView.setupDesk());
		}
        this.add(desksPanel, BorderLayout.CENTER);
    }


	private void setupSouthPanel(HashMap<String, Flight> flightList) {
	    JPanel flightsPanel = new JPanel();
	    //Allow setup of multiple flights
	    flightsPanel.setLayout(new GridLayout(1, flightList.size()));
		flightsPanel.setPreferredSize(new Dimension(getWidth(), 100));
		for (model.entity.Flight flight : flightList.values()) {
			view.Flight flightView = new view.Flight(flight);
			flightsPanel.add(flightView.setupFlightPanel());
		}
	    this.add(flightsPanel, BorderLayout.SOUTH);
    }

}
