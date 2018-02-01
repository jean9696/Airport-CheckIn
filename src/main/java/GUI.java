import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	JTextField lastNameInput, surnameInput, bookingReferenceInput, baggageVolumeInput, baggageWeightInput;
	JButton addButton;
	JTextArea messageArea;
	
	public GUI () {
		
		// set up the title of the window
		this.setTitle("Booking GUI");
		
		// call the two functions that set up the GUI
		setupNorthPanel();
		setupSouthPanel();
		
		//setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

		// pack and set visible
		pack();
		setVisible(true);
	
	}
	
	
	private void setupNorthPanel() {
		
		//Set up the 1st line for the surname
		JPanel surnamePanel = new JPanel();
		surnamePanel.setLayout(new GridLayout(1,2));
		surnamePanel.add(new JLabel("Surname"));
		surnameInput = new JTextField(30);
		surnamePanel.add(surnameInput);
		
		//Set up the 2nd line for the last name
		JPanel lastNamePanel = new JPanel();
		lastNamePanel.setLayout(new GridLayout(1,2));
		lastNamePanel.add(new JLabel("Last name"));
		lastNameInput = new JTextField(30);
		lastNamePanel.add(lastNameInput);
		
		//Set up the 3rd line for the booking reference
		JPanel bookingRefPanel = new JPanel();
		bookingRefPanel.setLayout(new GridLayout(1,2));
		bookingRefPanel.add(new JLabel("Booking ref. code"));
		bookingReferenceInput = new JTextField(30);
		bookingRefPanel.add(bookingReferenceInput);
		
		//Set up the 4th line for the baggage's volume
		JPanel volumePanel = new JPanel();
		volumePanel.setLayout(new GridLayout(1,2));
		volumePanel.add(new JLabel("Volume (in litre)"));
		baggageVolumeInput = new JTextField(30);
		volumePanel.add(baggageVolumeInput);
		
		//Set up the 5th line for the baggage's weight
		JPanel weightPanel = new JPanel();
		weightPanel.setLayout(new GridLayout(1,2));
		weightPanel.add(new JLabel("Weight (in kgs)"));
		baggageWeightInput = new JTextField(30);
		weightPanel.add(baggageWeightInput);
		
		//Set up the whole north panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(4,2));
		northPanel.add(surnamePanel);
		northPanel.add(lastNamePanel);
		northPanel.add(bookingRefPanel);
		northPanel.add(volumePanel);
		northPanel.add(weightPanel);
		
		this.add(northPanel, BorderLayout.NORTH);
		
	}

	private void setupSouthPanel() {
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2,1));
		
		addButton = new JButton("Add");
		southPanel.add(addButton);		
		addButton.addActionListener(this);
		
		messageArea = new JTextArea(10, 30);
		messageArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		messageArea.setEditable(false);
		southPanel.add(messageArea);
			
		this.add(southPanel, BorderLayout.SOUTH);
	}




	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
