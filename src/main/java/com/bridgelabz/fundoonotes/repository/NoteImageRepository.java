package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoonotes.entity.NoteImage;

public interface NoteImageRepository extends JpaRepository<NoteImage, Long> {

}
