package com.bridgelabz.fundoonotes.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "user_details",
//       indexes = @Index(name = "mulitIndex1", 
//                        columnList = "firstName, lastName",  
//                        unique=true))
public class User  implements Serializable{

	private static final long serialVersionUID = 7337529020223496451L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//@Column(columnDefinition = "varchar(255) default 'John Wick'")
	private String firstName;
	
	private String lastName;
	
	@Column(nullable = false,unique = true)
	private String emailId;
	
	@Column(nullable = false,unique = true)
	private String phoneNumber;
	
	private Boolean isVerified;
	
	private String profilePicURL;
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@Transient
	private String temp;
	
	@CreationTimestamp
	private LocalDateTime createdTimeStamp;

	@UpdateTimestamp
	private LocalDateTime updatedTimeStamp;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(targetEntity = Note.class)
	@JoinColumn(name = "user_id")
	private List<Note> notes;
}
