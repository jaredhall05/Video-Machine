package videoMachine.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFrame;

import videoMachine.controller.MainController;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import net.iharder.dnd.FileDrop;

public class FileSelectWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -584110371009041466L;
	private JTextField txtFile;

	public FileSelectWindow(JFrame owner, final MainController controller){
		super(owner, true);
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setSize(new Dimension(400, 100));
		getContentPane().setPreferredSize(new Dimension(400, 100));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 400, 100);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFile = new JLabel("File:");
		lblFile.setBounds(10, 12, 36, 20);
		lblFile.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblFile);
		
		txtFile = new JTextField();
		txtFile.setBounds(56, 12, 233, 20);
		panel.add(txtFile);
		txtFile.setColumns(10);
		
		// This is needed to reference the window inside of the actionlistener
		final FileSelectWindow ths = this;
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(ths);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					txtFile.setText(file.getAbsolutePath());
				}
			}
		});
		btnBrowse.setBounds(299, 11, 91, 23);
		panel.add(btnBrowse);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.getNotes().getSelectedNote().addMediaFileURL(txtFile.getText());
				txtFile.setText("");
				setVisible(false);
			}
		});
		btnOk.setBounds(299, 66, 91, 23);
		panel.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtFile.setText("");
				setVisible(false);
			}
		});
		btnCancel.setBounds(201, 66, 91, 23);
		panel.add(btnCancel);
		
		new FileDrop(panel, new FileDrop.Listener() {
			
			@Override
			public void filesDropped(File[] files) {
				txtFile.setText(files[0].getAbsolutePath());
			}
		});
		
		pack();
	}
}
