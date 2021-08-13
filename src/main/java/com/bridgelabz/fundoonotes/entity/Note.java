package com.bridgelabz.fundoonotes.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	private String reminder;
	
	private String colour;
	
	private Boolean archive;

	private Boolean pin;
	
	private Boolean trash;
	
	@CreationTimestamp
	private LocalDateTime createdTimeStamp;
	
	@UpdateTimestamp
	private LocalDateTime updatedTimeStamp;
}
