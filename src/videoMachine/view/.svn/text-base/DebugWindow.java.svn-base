package videoMachine.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;

public class DebugWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -310387775393192319L;
	
	private JTextArea _debugTextArea;
	
	public DebugWindow(JFrame owner)
	{
		super(owner);
		
		this.setPreferredSize(new Dimension(600, 300));
		this.pack();
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		_debugTextArea = new JTextArea();
		scrollPane.setViewportView(_debugTextArea);
		_debugTextArea.setRows(10);
		_debugTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
		_debugTextArea.setEditable(false);
	}
	
	public void log(String message){
		this._debugTextArea.setText(this._debugTextArea.getText() + message + "\n");
		this._debugTextArea.setCaretPosition( this._debugTextArea.getText().length() );
	}

}
