package videoMachine.midi.events;

import java.awt.AWTEvent;
import java.awt.Event;

import videoMachine.model.vo.MidiNoteVO;


public class MidiEvent extends AWTEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MidiNoteVO noteVO;
	
	public MidiEvent(Event event) {
		super(event);
	}

	public MidiEvent(Object source, int id) {
		super(source, id);
	}
	
	public MidiEvent(Object source, int id, String midiInfo) {
		super(source, id);
		
		String[] midiInfos = midiInfo.split("/");
		
		if (midiInfos[0].equals(MidiNoteVO.MIDI_NOTE_ON) ||
			midiInfos[0].equals(MidiNoteVO.MIDI_NOTE_OFF))
		{
			this.noteVO = new MidiNoteVO(midiInfos[0], midiInfos[1], midiInfos[2], Integer.parseInt(midiInfos[3]));
		}
		else if (midiInfos[0].equals(MidiNoteVO.MIDI_CONTROL_CHANGE) ||
				 midiInfos[0].equals(MidiNoteVO.MIDI_PITCH_WHEEL_CHANGE))
		{
			this.noteVO = new MidiNoteVO(midiInfos[0], Integer.parseInt(midiInfos[midiInfos.length - 1]));
		}
	}

}
