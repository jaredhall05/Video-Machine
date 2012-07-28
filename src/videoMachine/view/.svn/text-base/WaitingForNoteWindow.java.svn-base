package videoMachine.view;

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;

import javax.swing.JButton;

import videoMachine.controller.MainController;
import videoMachine.model.locale.Localization;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaitingForNoteWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6228968183184367454L;

	public WaitingForNoteWindow(JFrame owner, final MainController controller) {
		super(owner, true);
		

		setAlwaysOnTop(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setSize(new Dimension(400, 100));
		getContentPane().setPreferredSize(new Dimension(400, 100));
		
		JLabel lblWaiting = new JLabel(Localization.getString("WAITING_FOR_NOTE_LBL_TEXT"));
		lblWaiting.setBounds(0, 39, 400, 22);
		lblWaiting.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWaiting.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaiting.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(lblWaiting);
		
		JButton btnCancel = new JButton(Localization.getString("WAITING_FOR_NOTE_CANCEL_BTN_TEXT"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				controller.cancelListenForMidiNote();
			}
		});
		btnCancel.setBounds(299, 72, 91, 23);
		getContentPane().add(btnCancel);
		
		pack();
	}

}
