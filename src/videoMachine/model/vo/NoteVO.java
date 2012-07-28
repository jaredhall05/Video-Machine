package videoMachine.model.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import videoMachine.model.AbstractModelObject;

public class NoteVO extends AbstractModelObject {
	
	// Static Fields
	private static int numInstances = 0;
	
	// Static Properties
	public static int getNumInstances(){
		return numInstances;
	}
	
	// Fields
	
	private String _title;
	private List<String> _mediaFileURLs = new ArrayList<String>();
	private Boolean _randomMediaFile = false;
	private int _curMediaFile = 0;
	private MidiNoteVO _midiNote;
	
	// Properties
	
	public String getTitle(){
		return _title;
	}
	
	public void setTitle(String val){
		String oldValue = _title;
		_title = val;
		firePropertyChange("title", oldValue, _title);
	}
	
	public List<String> getMediaURLs(){
		return _mediaFileURLs;
	}
	
	public void setMediaURLs(List<String> mediaURLs){
		List<String> oldValue = _mediaFileURLs;
		_mediaFileURLs = mediaURLs;
		firePropertyChange("mediaURLs", oldValue, _mediaFileURLs);
	}
	
	public void addMediaFileURL(String mediaFileURL){
		List<String> oldValue = _mediaFileURLs;
		_mediaFileURLs = new ArrayList<String>(_mediaFileURLs);
		_mediaFileURLs.add(mediaFileURL);
		firePropertyChange("mediaURLs", oldValue, _mediaFileURLs);
	}
	
	public void addMediaFileURLs(List<String> mediaFileURLs){
		List<String> oldValue = _mediaFileURLs;
		_mediaFileURLs = new ArrayList<String>(_mediaFileURLs);
		for(int i = 0; i < mediaFileURLs.size(); i++){
			_mediaFileURLs.add(mediaFileURLs.get(i));
		}
		firePropertyChange("mediaURLs", oldValue, _mediaFileURLs);
	}
	
	public void removeMediaFileURL(String mediaFileURL){
		List<String> oldValue = _mediaFileURLs;
		_mediaFileURLs = new ArrayList<String>(_mediaFileURLs);
		_mediaFileURLs.remove(mediaFileURL);
		firePropertyChange("mediaURLs", oldValue, _mediaFileURLs);
	}
	
	public String getMediaURL(){
		if (_randomMediaFile){
			Random rnd = new Random();
			return _mediaFileURLs.get(rnd.nextInt(_mediaFileURLs.size()));
		} else {
			_curMediaFile++;
			if (_curMediaFile >= _mediaFileURLs.size()){
				_curMediaFile = 0;
			}
			return _mediaFileURLs.get(_curMediaFile);
		}
	}
	
	public Boolean getRandomMediaFile(){
		return _randomMediaFile;
	}
	
	public void setRandomMediaFile(Boolean isRandom){
		Boolean oldValue = _randomMediaFile;
		_randomMediaFile = isRandom;
		firePropertyChange("randomMediaFile", oldValue, _randomMediaFile);
	}
	
	public MidiNoteVO getMidiNote(){
		return _midiNote;
	}
	
	public void setMidiNote(MidiNoteVO val){
		MidiNoteVO oldValue = _midiNote;
		_midiNote = val;
		firePropertyChange("midiNote", oldValue, _midiNote);
	}
	
	// Constructors
	
	public NoteVO(String title){
		_title = title;
		_midiNote = null;
		
		numInstances++;
	}
	
	@Override
	public String toString() {
		return _title;
	}
}
