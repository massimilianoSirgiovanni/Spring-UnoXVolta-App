package MQTT.SpringApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

	// This class allows you to keep both the MQTT message and the message topic in
	// a single object

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	String topic;
	String message;
	
	public Message() {
		
	}

	public Message(String topic, String message) {

		this.topic = topic;

		this.message = message;

	}

	///////// Getter and Setters//////////////////////////

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/////////////////////////////////////////////////

}