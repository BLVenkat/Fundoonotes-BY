package com.bridgelabz.fundoonotes.utils;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.bridgelabz.fundoonotes.entity.Note;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KafkaCustomDeserilizer implements Deserializer<Note> {

	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Note deserialize(String topic, byte[] data) {
		try {
			if (data == null) {
				System.out.println("Null received at deserializing");
				return null;
			}
			System.out.println("Deserializing...");
			return objectMapper.readValue(new String(data, "UTF-8"), Note.class);
		} catch (Exception e) {
			throw new SerializationException("Error when deserializing byte[] to MessageDto");
		}
	}

}
