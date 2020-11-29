package MQTT.SpringApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

@SpringBootApplication
public class SpringAppApplication {

	public static MqttAsyncClient myClient;

	@Autowired
	private Callback myCallback;

	@Autowired
	private StoreService stores;

	public static void main(String[] args) throws MqttException {

		SpringApplication.run(SpringAppApplication.class, args); // The application starts

	}

	@EventListener(ApplicationReadyEvent.class)
	public void startMqttClient() throws MqttException {
		stores.initialAdd(); // Temporary data initialization, to facilitate testing

		// Operations for connecting the client to the mqtt broker

		myClient = new MqttAsyncClient("tcp://192.168.0.223:1883", UUID.randomUUID().toString()); // A client is created

		myClient.setCallback(myCallback); // The callback is assigned to the client
		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setAutomaticReconnect(true);
		mqttConnectOptions.setCleanSession(false);

		IMqttToken token = myClient.connect(mqttConnectOptions);
		token.waitForCompletion();

		myClient.subscribe("sensors/+/+", 2); // The customer subscribes to the requested topics*/
	}

}
