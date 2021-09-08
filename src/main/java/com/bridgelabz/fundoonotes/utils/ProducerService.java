package com.bridgelabz.fundoonotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.entity.Note;

@Component
public class ProducerService {

	@Autowired
	KafkaTemplate<String, Note> kafkaTemplate;
	
	public void sendMessage(Note message) {
		kafkaTemplate.send("test", message);
	}
}
