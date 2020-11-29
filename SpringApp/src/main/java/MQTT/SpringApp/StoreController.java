package MQTT.SpringApp;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController

public class StoreController {

	@Autowired
	private StoreService stores;

	@Autowired
	private MqttService messages;

	@RequestMapping("/stores")
	public List<Store> getStores() {
		return stores.getAllStores();
	}

	@RequestMapping("/store/{name}")
	public String getStore(@PathVariable String name) {
		return stores.getStore(name).printStore();
	}

	@RequestMapping("/store/{name}/count")
	public String getStoreCount(@PathVariable String name) {
		Store thisStore = stores.getStore(name);
		if (thisStore.getCount() < thisStore.getNumberMax()) {
			return "The number of people present in the store " + name + " is " + thisStore.getCount();

		} else {
			return "The number of people present in the store " + name + " is " + thisStore.getCount()
					+ " --> ATTENTION: The maximum number of people allowed has been reached";
		}

	}

	@RequestMapping(("/addstore/{name}"))
	public String addStore(@PathVariable String name) {
		stores.createAndAdd(name);
		return "The store " + name + " has been added";
	}

	@RequestMapping("/store/{name}/setmax/{max}")
	public String setStoreMax(@PathVariable String name, @PathVariable int max) {
		stores.updateMaxValue(name, max);
		return "The maximum number of people allowed in the store " + name + " has been changed to " + max;
	}

	@RequestMapping("/messages")
	public List<Message> getMessage() {
		return messages.getAllMessage();
	}

}
