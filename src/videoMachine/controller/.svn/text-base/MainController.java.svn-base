package videoMachine.controller;

import java.awt.event.ActionEvent;

import javax.sound.midi.MidiDevice.Info;

import videoMachine.midi.events.MidiEvent;
import videoMachine.midi.events.MidiListener;
import videoMachine.model.Notes;
import videoMachine.model.locale.Localization;
import videoMachine.model.vo.MidiNoteVO;
import videoMachine.model.vo.NoteVO;
import videoMachine.view.DebugWindow;
import videoMachine.view.FileSelectWindow;
import videoMachine.view.MainWindow;
import videoMachine.view.VideoWindow;
import videoMachine.view.WaitingForNoteWindow;


public class MainController implements MidiListener {

	private DebugWindow debugWindow;
	private MainWindow mainView;
	private WaitingForNoteWindow waitingWindow;
	private FileSelectWindow fileSelectWindow;
	private VideoWindow videoWindow;
	private MidiController midiController;
	private Notes _notes;
	
	private Boolean _waitingForMidi = false;
	
	

	public MainController(){
		
		midiController = new MidiController(this);
		
		_notes = new Notes();
		
		mainView = new MainWindow(Localization.getString("APPLICATION_TITLE"), this);
		mainView.setVisible(true);
		
		debugWindow = new DebugWindow(mainView);
		
		waitingWindow = new WaitingForNoteWindow(mainView, this);
		waitingWindow.setLocation((mainView.getWidth() - waitingWindow.getWidth()) / 2, (mainView.getHeight() - waitingWindow.getHeight()) / 2);
		
		videoWindow = new VideoWindow();
		videoWindow.setVisible(true);
		
		fileSelectWindow = new FileSelectWindow(mainView, this);
		
		mainView.updateDeviceList(midiController.listDevices(true, false));
	}
	
	public boolean connectToDevice(Info device){
		return midiController.connectToDevice(device);
	}
	
	public void disconnectDevice(){
		midiController.disconnectDevice();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public Notes getNotes(){
		return this._notes;
	}
	
	public void selectFileToAdd(){
		fileSelectWindow.setVisible(true);
	}

	public void listenForMidiNote(){
		_waitingForMidi = true;
		
		log("Waiting for Midi note.");
		
		waitingWindow.setVisible(true);
	}
	
	public void cancelListenForMidiNote(){
		_waitingForMidi = false;
		
		log("No longer waiting for Midi note.");
		
		waitingWindow.setVisible(false);
	}
	
	@Override
	public void midiPerformed(MidiEvent event) {
		MidiNoteVO midiNote = event.noteVO;
		log("Midi Happened: " + midiNote.get_type() +
			" Note: " + midiNote.get_note() +
			" Octave: " + midiNote.get_octave() +
			" Velocity: " + midiNote.get_velocity() +
			" Value: " + midiNote.get_value());
		
		if (_waitingForMidi){
			log("Got the midi note");
			_waitingForMidi = false;
			if (_notes.getSelectedNote() != null){
				_notes.getSelectedNote().setMidiNote(midiNote);
			} else {
				log("No note to apply midi");
			}
			waitingWindow.setVisible(false);
		} else {
			for (int i = 0; i < _notes.getNotes().size(); i++){
				NoteVO curNote = _notes.getNotes().get(i);
				
				if (curNote.getMidiNote() != null){
					log(curNote.getMidiNote().toString() + " | " + midiNote.toString());
					if(curNote.getMidiNote().equals(midiNote)){
						videoWindow.playVideo((_notes.getNotes().get(i)).getMediaURL());
					}
				} else {
					log("No midi note.");
				}
				
			}
		}
	}
	
	public void log(String message){
		debugWindow.log(message);
	}

	public void showHideDebug() {
		debugWindow.setVisible(!debugWindow.isVisible());
	}
}
