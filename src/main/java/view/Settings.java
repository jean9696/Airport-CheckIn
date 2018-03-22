package view;

import controller.SettingsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * view.GUI of the app
 */
public class Settings extends JFrame {

	public Settings(final SettingsController settingsController, final ActionListener onConfirm) {

		this.setTitle("Settings");
		// setting panel
		JPanel settingPanel = new JPanel();
		settingPanel.setLayout(new GridLayout(4, 1));

		final JTextField initialQueueLengthTextField = new JTextField(settingsController.getInitialQueueLength().toString());
		settingPanel.add(createField("Initial queue length", initialQueueLengthTextField));

		final JTextField checkInDeskNumberTextField = new JTextField(settingsController.getCheckInDeskNumber().toString());
		settingPanel.add(createField("Check in desks", checkInDeskNumberTextField));

		final JTextField openTimeTextField = new JTextField(settingsController.getOpenTime().toString());
		settingPanel.add(createField("Desk open time (sec)", openTimeTextField));


		// confirm
		JButton confirm = new JButton("Confirm");
		final JFrame settings = this;
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					settingsController.setInitialQueueLength(Integer.parseInt(initialQueueLengthTextField.getText()));
					settingsController.setCheckInDeskNumber(Integer.parseInt(checkInDeskNumberTextField.getText()));
					settingsController.setOpenTime(Integer.parseInt(openTimeTextField.getText()));
					onConfirm.actionPerformed(e);
					settings.dispatchEvent(new WindowEvent(settings, WindowEvent.WINDOW_CLOSING));
				} catch (Exception error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
				}
			}
		});
		settingPanel.add(confirm);

		// show everything
		this.add(settingPanel);
		pack();
		setVisible(true);

	}

	private JPanel createField(String label, JTextField textField) {
		JPanel fieldPanel = new JPanel();
		JLabel jLabel = new JLabel(label);
		jLabel.setPreferredSize(new Dimension(150, 25));
		fieldPanel.add(jLabel);
		textField.setPreferredSize(new Dimension(70, 25));
		fieldPanel.add(textField);
		return fieldPanel;
	}



}
