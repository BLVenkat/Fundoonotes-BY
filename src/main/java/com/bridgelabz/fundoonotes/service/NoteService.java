package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.entity.Note;

public interface NoteService {

	public void createNote(String token,NoteDTO noteDto);
	public void createNote(String token,List<NoteDTO> noteDto);

	public List<Note> getNotes(String token);
}
