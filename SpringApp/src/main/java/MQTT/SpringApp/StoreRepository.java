package MQTT.SpringApp;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Long> {
	
	List<Store> findAllByName(String name);

}