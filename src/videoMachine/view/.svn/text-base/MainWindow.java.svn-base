package videoMachine.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.sound.midi.MidiDevice.Info;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.iharder.dnd.FileDrop;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.SwingBindings;

import videoMachine.controller.MainController;
import videoMachine.model.vo.MidiNoteVO;
import videoMachine.model.vo.NoteVO;


public class MainWindow extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MainController _mainController;
	
	private Boolean _deviceSettingsEnabled = false;
	private Boolean _noteSettingsEnabled = false;
	
	private JComboBox _devicesList;
	private JButton _connectBtn;
	private JButton _disconnectBtn;
	private JPanel panel;
	private JPanel settingsPanel;
	private JPanel listPanel;
	private JScrollPane scrollPane_1;
	private JPanel panel_1;
	private JButton addItemBtn;
	private JButton removeItemBtn;
	private JScrollPane scrollPane_2;
	private JList lstNotes;
	private JPanel pnlElements;
	private JButton btnShowhideDebug;
	private JLabel lblTitle;
	private JTextField txtTitle;
	private JLabel lblCurFile;
	private JLabel lblNote;
	private JLabel lblCurrentNote;
	private JButton btnAssignNote;
	private JScrollPane scrollPane;
	private JList lstMediaFiles;
	private JPanel panel_2;
	private JButton btnAddMediaFile;
	private JButton btnRemoveMediaFile;
	private JToggleButton tglbtnRandomMediaFile;
	
	public MainWindow(String WindowTitle, MainController mainController)
	{
		super(WindowTitle);
		
		this._mainController = mainController;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(800, 600);
		
		this.buildGUI();
		
		this.setEnabledDeviceSettings(false);
		this.setEnabledNoteSettings(false, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == this._connectBtn ||
		   arg0.getActionCommand() == "Connect_Button"){
			Info deviceInfo = (Info) this._devicesList.getSelectedItem();
			
			boolean isConnected = _mainController.connectToDevice(deviceInfo);
			
			if (isConnected){
				this._connectBtn.setEnabled(false);
				this._devicesList.setEnabled(false);
				this._disconnectBtn.setEnabled(true);
				
				this.setEnabledDeviceSettings(true);
			}
		}
		else if (arg0.getSource() == this._disconnectBtn ||
				 arg0.getActionCommand() == "disconnect_Button"){
			_mainController.disconnectDevice();
			this._connectBtn.setEnabled(true);
			this._devicesList.setEnabled(true);
			this._disconnectBtn.setEnabled(false);
			
			this.setEnabledDeviceSettings(false);
		}
	}
	
	/**
	 * Build the main GUI for this application
	 */
	private void buildGUI()
	{
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		this._devicesList = new JComboBox();
		panel.add(_devicesList);
		this._devicesList.addActionListener(this);
		this._devicesList.setActionCommand("Device_List");
		
		this._connectBtn = new JButton("Connect");
		panel.add(_connectBtn);
		this._connectBtn.addActionListener(this);
		this._connectBtn.setActionCommand("Connect_Button");
		
		this._disconnectBtn = new JButton("Disconnect");
		_disconnectBtn.setEnabled(false);
		panel.add(_disconnectBtn);
		this._disconnectBtn.addActionListener(this);
		this._disconnectBtn.setActionCommand("disconnect_Button");
		
		btnShowhideDebug = new JButton("Show/Hide Debug");
		panel.add(btnShowhideDebug);
		btnShowhideDebug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_mainController.showHideDebug();
			}
		});
		btnShowhideDebug.setActionCommand("ShowHideDebug_Button");
		
		settingsPanel = new JPanel();
		getContentPane().add(settingsPanel, BorderLayout.CENTER);
		settingsPanel.setLayout(new BorderLayout(0, 0));
		
		listPanel = new JPanel();
		settingsPanel.add(listPanel, BorderLayout.WEST);
		listPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setAlignmentY(Component.TOP_ALIGNMENT);
		listPanel.add(scrollPane_2, BorderLayout.CENTER);
		
		lstNotes = new JList();
		lstNotes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Boolean itemSelected = (lstNotes.getSelectedIndex() > -1);
				removeItemBtn.setEnabled(itemSelected);
				setEnabledNoteSettings(itemSelected, getSelectedNote());
			}
		});
		scrollPane_2.setViewportView(lstNotes);
		
		panel_1 = new JPanel();
		listPanel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		addItemBtn = new JButton("Add Element");
		addItemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = NoteVO.getNumInstances();
				_mainController.getNotes().addNote(new NoteVO("Note " + pos));
			}
		});
		panel_1.add(addItemBtn);
		
		removeItemBtn = new JButton("Remove Element");
		removeItemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_mainController.getNotes().removeNote(getSelectedNote());
			}
		});
		removeItemBtn.setEnabled(false);
		panel_1.add(removeItemBtn);
		
		scrollPane_1 = new JScrollPane();
		settingsPanel.add(scrollPane_1, BorderLayout.CENTER);
		
		pnlElements = new JPanel();
		scrollPane_1.setViewportView(pnlElements);
		GridBagLayout gbl_pnlElements = new GridBagLayout();
		gbl_pnlElements.columnWidths = new int[]{80, 200, 0};
		gbl_pnlElements.rowHeights = new int[]{20, 14, 23, 14, 23, 0, 0, 0, 0};
		gbl_pnlElements.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnlElements.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlElements.setLayout(gbl_pnlElements);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.NORTH;
		gbc_lblTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 1;
		pnlElements.add(lblTitle, gbc_lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setColumns(10);
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.anchor = GridBagConstraints.NORTH;
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitle.gridx = 1;
		gbc_txtTitle.gridy = 1;
		pnlElements.add(txtTitle, gbc_txtTitle);
		
		lblCurFile = new JLabel("Media File(s):");
		lblCurFile.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCurFile = new GridBagConstraints();
		gbc_lblCurFile.anchor = GridBagConstraints.NORTH;
		gbc_lblCurFile.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCurFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurFile.gridx = 0;
		gbc_lblCurFile.gridy = 2;
		pnlElements.add(lblCurFile, gbc_lblCurFile);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		pnlElements.add(scrollPane, gbc_scrollPane);
		
		lstMediaFiles = new JList();
		lstMediaFiles.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Boolean itemSelected = (lstMediaFiles.getSelectedIndex() > -1);
				btnRemoveMediaFile.setEnabled(itemSelected);
			}
		});
		scrollPane.setViewportView(lstMediaFiles);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		pnlElements.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnAddMediaFile = new JButton("Add Media File");
		btnAddMediaFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_mainController.selectFileToAdd();
			}
		});
		panel_2.add(btnAddMediaFile);
		
		btnRemoveMediaFile = new JButton("Remove Media File");
		btnRemoveMediaFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getSelectedNote()
					.removeMediaFileURL(
							lstMediaFiles.getSelectedValue().toString()
						);
			}
		});
		panel_2.add(btnRemoveMediaFile);
		
		btnAssignNote = new JButton("Assign Note");
		btnAssignNote.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_mainController.listenForMidiNote();
			}
		});
		
		tglbtnRandomMediaFile = new JToggleButton("Random Media File");
		GridBagConstraints gbc_tglbtnRandomMediaFile = new GridBagConstraints();
		gbc_tglbtnRandomMediaFile.insets = new Insets(0, 0, 5, 0);
		gbc_tglbtnRandomMediaFile.gridx = 1;
		gbc_tglbtnRandomMediaFile.gridy = 4;
		pnlElements.add(tglbtnRandomMediaFile, gbc_tglbtnRandomMediaFile);
		
		lblNote = new JLabel("Note:");
		lblNote.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNote = new GridBagConstraints();
		gbc_lblNote.anchor = GridBagConstraints.NORTH;
		gbc_lblNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNote.insets = new Insets(0, 0, 5, 5);
		gbc_lblNote.gridx = 0;
		gbc_lblNote.gridy = 5;
		pnlElements.add(lblNote, gbc_lblNote);
		
		lblCurrentNote = new JLabel("No Current Note");
		GridBagConstraints gbc_lblCurrentNote = new GridBagConstraints();
		gbc_lblCurrentNote.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCurrentNote.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrentNote.gridx = 1;
		gbc_lblCurrentNote.gridy = 5;
		pnlElements.add(lblCurrentNote, gbc_lblCurrentNote);
		GridBagConstraints gbc_btnAssignNote = new GridBagConstraints();
		gbc_btnAssignNote.insets = new Insets(0, 0, 5, 0);
		gbc_btnAssignNote.anchor = GridBagConstraints.NORTH;
		gbc_btnAssignNote.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAssignNote.gridx = 1;
		gbc_btnAssignNote.gridy = 6;
		pnlElements.add(btnAssignNote, gbc_btnAssignNote);
		
		new FileDrop(lstMediaFiles, new FileDrop.Listener() {
			
			@Override
			public void filesDropped(File[] files) {
				for (int i = 0; i < files.length; i++){
					_mainController.getNotes().getSelectedNote().addMediaFileURL(files[i].getAbsolutePath());
				}
			}
		});
		
		initDataBindings();
	}
	
	private NoteVO getSelectedNote(){
		return _mainController.getNotes().getSelectedNote();
	}
	
	private void setEnabledDeviceSettings(Boolean en){
		this._deviceSettingsEnabled = en;
		
		this.lstNotes.setEnabled(en);
		this.addItemBtn.setEnabled(en);
		
		if (!en){
			this.removeItemBtn.setEnabled(en);
			this.setEnabledNoteSettings(en, null);
		}
	}
	
	@SuppressWarnings("unused")
	private Boolean isEnabledDeviceSettings(){
		return this._deviceSettingsEnabled;
	}
	
	private void setEnabledNoteSettings(Boolean en, NoteVO noteVO){
		this._noteSettingsEnabled = en;
		
		this.txtTitle.setEnabled(en);
		this.lblCurrentNote.setEnabled(en);
		this.btnAssignNote.setEnabled(en);
		this.lblTitle.setEnabled(en);
		this.lblCurFile.setEnabled(en);
		this.lstMediaFiles.setEnabled(en);
		this.btnAddMediaFile.setEnabled(en);
		if (en == false) {
			this.btnRemoveMediaFile.setEnabled(en);
		}
		this.lblNote.setEnabled(en);
		
		if (noteVO != null){
			if (noteVO.getMidiNote() != null){
				this.lblCurrentNote.setText(noteVO.getMidiNote().toString());
			} else {
				this.lblCurrentNote.setText("No Current Note");
			}
		}
		
		if (!en){
			this.txtTitle.setText("");
			this.lblCurrentNote.setText("No Current Note");
		}
	}
	
	@SuppressWarnings("unused")
	private Boolean isEnabledNoteSettings(){
		return this._noteSettingsEnabled;
	}
	
	public void updateDeviceList(Info[] inputDevices){
		for (int i = 0; i < inputDevices.length; i++){
			this._devicesList.addItem(inputDevices[i]);
		}
	}

	public void midiPerformed(MidiNoteVO midiNote){
	}
	protected void initDataBindings() {
		BeanProperty<MainController, List<NoteVO>> mainControllerBeanProperty = BeanProperty.create("notes.notes");
		JListBinding<NoteVO, MainController, JList> jListBinding = SwingBindings.createJListBinding(UpdateStrategy.READ, _mainController, mainControllerBeanProperty, lstNotes);
		//
		BeanProperty<NoteVO, String> noteVOBeanProperty = BeanProperty.create("title");
		jListBinding.setDetailBinding(noteVOBeanProperty);
		//
		jListBinding.bind();
		//
		BeanProperty<JList, String> jListBeanProperty = BeanProperty.create("selectedElement.title");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<JList, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, lstNotes, jListBeanProperty, txtTitle, jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JList, MidiNoteVO> jListBeanProperty_1 = BeanProperty.create("selectedElement.midiNote");
		ELProperty<JList, Object> jListEvalutionProperty = ELProperty.create(jListBeanProperty_1, "${_type} ${_note}");
		BeanProperty<JLabel, String> jLabelBeanProperty = BeanProperty.create("text");
		AutoBinding<JList, Object, JLabel, String> autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, lstNotes, jListEvalutionProperty, lblCurrentNote, jLabelBeanProperty);
		autoBinding_1.bind();
		//
		BeanProperty<MainController, NoteVO> mainControllerBeanProperty_1 = BeanProperty.create("notes.selectedNote");
		BeanProperty<JList, NoteVO> jListBeanProperty_3 = BeanProperty.create("selectedElement");
		AutoBinding<MainController, NoteVO, JList, NoteVO> autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, _mainController, mainControllerBeanProperty_1, lstNotes, jListBeanProperty_3);
		autoBinding_3.bind();
		//
		BeanProperty<JList, List<String>> jListBeanProperty_2 = BeanProperty.create("selectedElement.mediaURLs");
		JListBinding<String, JList, JList> jListBinding_1 = SwingBindings.createJListBinding(UpdateStrategy.READ, lstNotes, jListBeanProperty_2, lstMediaFiles);
		jListBinding_1.bind();
		//
		BeanProperty<JList, Boolean> jListBeanProperty_4 = BeanProperty.create("selectedElement.randomMediaFile");
		BeanProperty<JToggleButton, Boolean> jToggleButtonBeanProperty = BeanProperty.create("selected");
		AutoBinding<JList, Boolean, JToggleButton, Boolean> autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, lstNotes, jListBeanProperty_4, tglbtnRandomMediaFile, jToggleButtonBeanProperty);
		autoBinding_2.bind();
	}
}
