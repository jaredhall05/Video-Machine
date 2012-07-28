package videoMachine.model;

import java.util.ArrayList;
import java.util.List;

import videoMachine.model.vo.NoteVO;

public class Notes extends AbstractModelObject {
	private List<NoteVO> _notes = new ArrayList<NoteVO>();
	private NoteVO _selectedNote;
	
	public void addNote(NoteVO note){
		List<NoteVO> oldValue = _notes;
		_notes = new ArrayList<NoteVO>(_notes);
		_notes.add(note);
		firePropertyChange("notes", oldValue, _notes);
	}
	
	public void removeNote(NoteVO note){
		List<NoteVO> oldValue = _notes;
		_notes = new ArrayList<NoteVO>(_notes);
		_notes.remove(note);
		firePropertyChange("notes", oldValue, _notes);
	}
	
	public List<NoteVO> getNotes(){
		return _notes;
	}
	
	public NoteVO getSelectedNote(){
		return _selectedNote;
	}
	
	public void setSelectedNote(NoteVO note){
		NoteVO oldValue = _selectedNote;
		_selectedNote = note;
		firePropertyChange("selectedNote", oldValue, _selectedNote);
	}
}
