package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.configuration.ApplicationConfig;
import com.bridgelabz.fundoonotes.dto.NoteDTO;
import com.bridgelabz.fundoonotes.entity.Note;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.utils.TokenService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private TokenService tokenService;
	
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
	
	@GetMapping("/{pageNumber}/{pageSize}")
	@ApiOperation("Api to get  notes of a user")
	public ResponseEntity<Response> getNotes(@RequestHeader String token,@PathVariable int pageNumber, @PathVariable int pageSize) {
			//Long userId = tokenService.decodeToken(token);
		List<Note> notes = noteService.getNotes(token,pageNumber,pageSize);
		return new ResponseEntity<Response>(
				new Response(HttpStatus.OK.value(), "Notes Fetched Successfully", notes), HttpStatus.OK);
	}
	
	@PutMapping(value = "/image/{noteId}")
	public ResponseEntity<Response> addImage(@RequestHeader String token,@PathVariable Long noteId,@RequestParam MultipartFile file){
		
		String key = noteService.addImage(token, noteId, file);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(),"Image Added to note Successfully" , key), HttpStatus.OK);
	}
}
