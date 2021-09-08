package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.entity.Note;

public interface NoteService {

	public List<Note> createNote(String token,NoteDTO noteDto);
	public void createNote(String token,List<NoteDTO> noteDto);

	public List<Note> getNotes(String token,int pageNumber,int pageSize);
	
	public String addImage(String  token,Long noteId,MultipartFile file);

}
