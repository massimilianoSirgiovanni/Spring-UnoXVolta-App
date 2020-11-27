package MQTT.SpringApp;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;

	public List<Store> getAllStores() {
		List<Store> stores = new ArrayList<>();
		storeRepository.findAll().forEach(t -> stores.add(t));
		return stores;
	}

	public Store getStore(String name) {

		return storeRepository.findById(name).get();

	}

	public void addCount(String name, int number) {
		Store thisStore = this.getStore(name);
		thisStore.addCount(number);
		storeRepository.save(thisStore);
	}

	public void addStore(Store store) {

		storeRepository.save(store);

	}

	public void removeStore(Store store) {

		storeRepository.delete(store);

	}

	public void createAndAdd(String name) {
		Store store = new Store(name);
		addStore(store);
	}

	public void updateMaxValue(String name, int max) {
		Store store = getStore(name);
		store.setNumberMax(max);
		addStore(store);
	}

	////////////////// TMP//////////////////////////

	public void initialAdd() {
		addStore(new Store("Seven", 50));
		addStore(new Store("American", 20));
		addStore(new Store("Club", 30));

	}

}
