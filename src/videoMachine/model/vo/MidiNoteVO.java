package videoMachine.model.vo;

public class MidiNoteVO {
	/*this._outputTextArea.setText(this._outputTextArea.getText() + "\n" +
	"Midi Happened: " + event.eventType +
	" Note: " + event.note +
	" Octave: " + event.octave +
	" Velocity: " + event.velocity +
	" Value: " + event.value);*/
	
	public static String MIDI_NOTE_ON = "note On";
	public static String MIDI_NOTE_OFF = "note Off";
	public static String MIDI_CONTROL_CHANGE = "control change";
	public static String MIDI_PITCH_WHEEL_CHANGE = "pitch wheel change";
	
	private String _type;
	private String _note;
	private String _octave;
	private int _velocity;
	private int _value;
	
	/**
	 * @return the midi type
	 */
	public String get_type() {
		return _type;
	}

	/**
	 * @return the midi note
	 */
	public String get_note() {
		return _note;
	}

	/**
	 * @return the midi octave
	 */
	public String get_octave() {
		return _octave;
	}

	/**
	 * @return the midi velocity
	 */
	public int get_velocity() {
		return _velocity;
	}

	/**
	 * @return the midi value
	 */
	public int get_value() {
		return _value;
	}
	
	public MidiNoteVO(String type, String note, String octave, int velocity){
		this._type = type;
		this._note = note;
		this._octave = octave;
		this._velocity = velocity;
	}
	
	public MidiNoteVO(String type, int value){
		this._type = type;
		this._value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		
		MidiNoteVO other = (MidiNoteVO)obj;

		/*System.out.println((other.get_type().equals(this.get_type())) + "\t" + other.get_type() + "\t" + this.get_type());
		System.out.println((other.get_note().equals(this.get_note())) + "\t" + other.get_note() + "\t" + this.get_note());
		System.out.println((other.get_octave().equals(this.get_octave())) + "\t" + other.get_octave() + "\t" + this.get_octave());
		System.out.println((other.get_value() == this.get_value()) + "\t" + other.get_value() + "\t" + this.get_value());*/
		
		if (other.get_type().equals(this.get_type()) &&
			other.get_note().equals(this.get_note()) &&
			other.get_octave().equals(this.get_octave()) &&
			other.get_value() == this.get_value()){
			isEqual = true;
		}
		
		
		return isEqual;
	}
	
	@Override
	public String toString() {
		return this.get_type() + " " + this.get_note();
	}
}
