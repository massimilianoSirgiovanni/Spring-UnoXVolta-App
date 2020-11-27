package MQTT.SpringApp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {

	// This class allows you to keep both the MQTT message and the message topic in
	// a single object

	@Id
	private String id = CreateID.createID();
	private String topic;
	private String message;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/////////////////////////////////////////////////

}