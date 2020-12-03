package MQTT.SpringApp;

import java.util.Iterator;
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

	@RequestMapping("/stores/{name}")
	public List<Store> getStoresByName(@PathVariable String name) {
		return stores.getStoresByName(name);
	}

	@RequestMapping("/store/{id}")
	public String getStore(@PathVariable long id) {
		return stores.getStoreByID(id).printStore();
	}

	@RequestMapping("/stores/{name}/count")
	public String getStoresCount(@PathVariable String name) {
		String print = "";
		Store thisStore;
		List<Store> store = stores.getStoresByName(name);
		Iterator<Store> iterator = store.iterator();
		while (iterator.hasNext()) {
			thisStore = iterator.next();
			if (thisStore.getCount() < thisStore.getNumberMax()) {
				print = print + "The number of people present in the store " + thisStore.getID() + " is "
						+ thisStore.getCount() + " /-----------/";

			} else {
				print = print + "The number of people present in the store " + thisStore.getID() + " is "
						+ thisStore.getCount() + " --> ATTENTION: The maximum number of people allowed has been reached"
						+ " /-----------/";
			}
		}

		return print;

	}

	@RequestMapping("/store/{id}/count")
	public String getStoreCount(@PathVariable long id) {
		Store thisStore = stores.getStoreByID(id);
		if (thisStore.getCount() < thisStore.getNumberMax()) {
			return "The number of people present in the store " + thisStore.getID() + " is " + thisStore.getCount();

		} else {
			return "The number of people present in the store " + thisStore.getID() + " is " + thisStore.getCount()
					+ " --> ATTENTION: The maximum number of people allowed has been reached";
		}

	}

	@RequestMapping(("/addstore/{name}"))
	public String addStore(@PathVariable String name) {
		stores.createAndAdd(name);
		return "The store " + name + " has been added";
	}

	@RequestMapping("/stores/{name}/setmax/{max}")
	public String setStoresMax(@PathVariable String name, @PathVariable int max) {
		stores.updateMaxValue(name, max);
		return "The maximum number of people allowed in the store " + name + " has been changed to " + max;
	}

	@RequestMapping("/store/{id}/setmax/{max}")
	public String setStoreMax(@PathVariable long id, @PathVariable int max) {
		stores.updateMaxValueByID(id, max);
		return "The maximum number of people allowed in the store with id \"" + id + "\" has been changed to " + max;
	}

	@RequestMapping(("/removeallstores/{name}"))
	public String removeStore(@PathVariable String name) {
		stores.removeStore(stores.getStoresByName(name));
		return "Stores named \"" + name + "\" have been deleted";
	}

	@RequestMapping(("/removestore/{id}"))
	public String removeStoreByID(@PathVariable long id) {
		stores.removeStore(stores.getStoreByID(id));
		return "The store with the id \"" + id + "\" has been deleted";
	}

	// Messages methods

	@RequestMapping("/messages")
	public List<Message> getMessages() {
		return messages.getAllMessages();
	}

	@RequestMapping("/messages/{store}")
	public List<Message> getMessagesByStore(@PathVariable String store) {
		return messages.getAllMessagesByStore(store);
	}

	@RequestMapping("/cleanmessages")
	public String removeAllMessages() {
		messages.cleanMessageDatabase();
		CreateID.resetID();
		return "All the messages in the Database has been deleted";
	}

}
