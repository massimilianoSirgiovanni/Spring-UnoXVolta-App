package MQTT.SpringApp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Store {

	@Id
	public String name;
	private int count; // Number of people currently in the room
	private int numberMax = 0; // Maximum number of people allowed

	public Store() {

	}

	public Store(String name) {
		this.name = name;
	}

	public Store(String name, int numberMax) {

		this.name = name;
		this.setNumberMax(numberMax);
	}

	public int getCount() {
		return count;
	}

	public int getNumberMax() {
		return numberMax;
	}

	public void setNumberMax(int numberMax) {
		this.numberMax = numberMax;
	}

	public String getName() {
		return name;
	}

	public void addCount(int number) {
		count = count + number;
		if (count < 0) {
			count = 0;
		}
	}

	public String printStore() {
		return "Name: " + name + " -- People currently in the room: " + count + " -- Maximum number of people allowed: "
				+ numberMax;
	}

}
