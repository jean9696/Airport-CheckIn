package view;

import model.collection.PassengerQueue;
import model.entity.CheckInDesk;
import model.entity.Passenger;
import model.entity.Flight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * view.GUI of the app
 */
public class GUI extends JFrame {

	private Queue queue;

    private JTextField lastNameInput, surnameInput, bookingReferenceInput, baggageVolumeInput, baggageWeightInput;
    private JButton addButton;
	private JTextArea messageArea;

	public GUI (PassengerQueue queueModel, LinkedList<CheckInDesk> desks, HashMap<String, Flight> flightList) {

		// set up the title of the window
		this.setTitle("model.entity.Booking view.GUI");

		// call the two functions that set up the view.GUI
		setupNorthPanel(queueModel);
		setupCenterPanel(desks);
		setupSouthPanel(flightList);

		//setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

		// pack and set visible
		pack();
		setVisible(true);

	}


	/**
	 * Set up the 5 input fields
	 */
	/*private void setupNorthPanel() {

		//Set up the 1st line for the surname
		JPanel surnamePanel = new JPanel();
		surnamePanel.setLayout(new GridLayout(1,2));
		surnamePanel.add(new JLabel("Surname"));
		surnameInput = new JTextField(15);
		surnamePanel.add(surnameInput);

		//Set up the 2nd line for the last name
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setLayout(new GridLayout(1,2));
		lastNamePanel.add(new JLabel("Last name"));
		lastNameInput = new JTextField(15);
		lastNamePanel.add(lastNameInput);

		//Set up the 3rd line for the booking reference
		JPanel bookingRefPanel = new JPanel();
		bookingRefPanel.setLayout(new GridLayout(1,2));
		bookingRefPanel.add(new JLabel("model.entity.Booking ref. code"));
		bookingReferenceInput = new JTextField(15);
		bookingRefPanel.add(bookingReferenceInput);

		//Set up the 4th line for the baggage's volume
		JPanel volumePanel = new JPanel();
		volumePanel.setLayout(new GridLayout(1,2));
		volumePanel.add(new JLabel("Volume (in litre)"));
		baggageVolumeInput = new JTextField(15);
		volumePanel.add(baggageVolumeInput);

		//Set up the 5th line for the baggage's weight
		JPanel weightPanel = new JPanel();
		weightPanel.setLayout(new GridLayout(1,2));
		weightPanel.add(new JLabel("Weight (in kgs)"));
		baggageWeightInput = new JTextField(15);
		weightPanel.add(baggageWeightInput);

		//Set up the whole north panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(5,1));
		northPanel.add(surnamePanel);
		northPanel.add(lastNamePanel);
		northPanel.add(bookingRefPanel);
		northPanel.add(volumePanel);
		northPanel.add(weightPanel);

		this.add(northPanel, BorderLayout.NORTH);

	}*/

	private void setupNorthPanel(PassengerQueue queueModel){
	    JPanel queuePanel = new JPanel();
	    queuePanel.setLayout(new GridLayout(2, 1));
	    Queue queue = new Queue(queueModel);
	    queuePanel.add(queue.setupAmountInQueue());
        queuePanel.add(queue.setupQueue(new LinkedList<Passenger>()));
        this.add(queuePanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel(LinkedList<CheckInDesk> checkInDesks) {
        JPanel desksPanel = new JPanel();
        desksPanel.setLayout(new GridLayout(1, checkInDesks.size()));
		for (CheckInDesk deskModel : checkInDesks) {
			Desk deskView = new Desk( "Desk", deskModel);
			desksPanel.add(deskView.setupDesk());
		}
        this.add(desksPanel, BorderLayout.CENTER);
    }


	/**
	 * Set up the center panel for the text area
	 */
	/*private void setupCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,1));
		
		messageArea = new JTextArea(2, 1);
		messageArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		messageArea.setEditable(false);
		centerPanel.add(messageArea);
		
		this.add(centerPanel, BorderLayout.CENTER);
	}*/
	
	/**
	 * Set up the south panel for the button
	 */
	/*private void setupSouthPanel() {

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,1));

		addButton = new JButton("Add");
		southPanel.add(addButton);		

		this.add(southPanel, BorderLayout.SOUTH);
	}*/

	private void setupSouthPanel(HashMap<String, Flight> flightList) {
	    JPanel flightsPanel = new JPanel();
	    flightsPanel.setLayout(new GridLayout(1, flightList.size()));
		for (model.entity.Flight flight : flightList.values()) {
			view.Flight flightView = new view.Flight(flight);
			flightsPanel.add(flightView.setupFlightPanel(flight));
		}
	    this.add(flightsPanel, BorderLayout.SOUTH);
    }

	/**
	 * @return lastname input as string
	 */
    public String getLastNameInput() {
        return lastNameInput.getText();
    }

	/**
	 * @return surname input as string
	 */
    public String getSurnameInput() {
        return surnameInput.getText();
    }

	/**
	 * @return booking ref as string
	 */
    public String getBookingReferenceInput() {
        return bookingReferenceInput.getText();
    }

	/**
	 * @return volume input as int
	 */
    public Integer getBaggageVolumeInput() {
        return convertJTextStringToInt(baggageVolumeInput.getText());
    }

	/**
	 * @return weight input as int
	 */
    public Integer getBaggageWeightInput() {
        return convertJTextStringToInt(baggageWeightInput.getText());
    }

	/**
	 * @param message
	 * set a message in the view.GUI text area
	 */
    public void setMessage(String message) {
        messageArea.setText(message);
    }

	/**
	 * @param price
	 * @return response from the user
	 */
    public Boolean printOverCapacityConfirmDialog(Integer price) {
		return JOptionPane.showConfirmDialog(
				null,
				"You have too much baggage, you have to pay £ " + price
		) == 0;
	}

	/**
	 * Clear the view.GUI of every inputs
	 */
    public void clear() {
	    messageArea.setText("");
        lastNameInput.setText("");
        surnameInput.setText("");
        bookingReferenceInput.setText("");
        baggageVolumeInput.setText("");
        baggageWeightInput.setText("");
    }

	/**
	 * Set the listener of the button
	 * @param onConfirm
	 */
	public void setOnConfirm(ActionListener onConfirm) {
        addButton.addActionListener(onConfirm);
    }

	/**
	 * @param text
	 * @return Integer
	 */
    public Integer convertJTextStringToInt(String text) {
	    try {
	        return Integer.parseInt(text);
        } catch (java.lang.NumberFormatException e) {
            return null;
        }
    }

	/**
	 * Close the view.GUI
	 */
    public void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}
