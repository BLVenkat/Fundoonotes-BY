package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.NoteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@PostMapping(value = {"","/"})
	@ApiOperation("Api to create note for  a user")
	public ResponseEntity<Response> createNote(@RequestHeader String token, @RequestBody NoteDTO noteDto) {
	   noteService.createNote(token, noteDto);
		return new ResponseEntity<Response>(
				new Response(HttpStatus.CREATED.value(), "Note Created Successfully", ""), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/list")
	@ApiOperation("Api to create note for  a user")
	public ResponseEntity<Response> createNote(@RequestHeader String token, @RequestBody List<NoteDTO> noteDto) {
	   noteService.createNote(token, noteDto);
		return new ResponseEntity<Response>(
				new Response(HttpStatus.CREATED.value(), "Notes Created Successfully", ""), HttpStatus.CREATED);
	}
	
	@GetMapping()
	@ApiOperation("Api to get  notes of a user")
	public ResponseEntity<Response> getNotes(@RequestHeader String token) {
	   List<Note> notes = noteService.getNotes(token);
		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), "Notes Fetched Successfully", notes), HttpStatus.OK);
	}
}
