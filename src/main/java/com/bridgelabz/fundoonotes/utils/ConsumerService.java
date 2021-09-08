package com.bridgelabz.fundoonotes.utils;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.entity.Note;

@Component
public class ConsumerService {
	
	@KafkaListener(topics  = {"test"})
    public void consume(Note message) 
    {
		System.out.println(message);
    }

}
