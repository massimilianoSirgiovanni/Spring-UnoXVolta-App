package MQTT.SpringApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttController {

	@Autowired
	private StoreService stores;

	@Autowired
	private MessageRepository messages;

	public void enter(String storeName, String message) {
		stores.addCount(storeName, Integer.parseInt(message));
	}

	public void exit(String storeName, String message) {

		stores.addCount(storeName, -Integer.parseInt(message));
	}

	public void storeMessages(String topic, String message) {

		Message toStore = new Message(topic, message);
		messages.save(toStore);

	}

	public List<Message> getAllMessage() {
		List<Message> toStoreMessages = new ArrayList<>();
		messages.findAll().forEach(t -> toStoreMessages.add(t));
		return toStoreMessages;
	}

}
