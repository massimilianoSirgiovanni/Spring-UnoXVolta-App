package MQTT.SpringApp;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import MQTT.UnoXVoltaApp.MessageStored;

@Service
public class MyCallback implements MqttCallback {

	@Autowired
	private MqttService controller;

	public MyCallback() {

	}

//////////////Methods implemented by the MtqqCallback interface currently unused/////////////////////
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

//////////////////////////////////////////////////////////////////////////////////////////////////

	public void messageArrived(String topic, MqttMessage message) throws Exception {

		elaborateMessage(topic, message); // The content of the message is processed and used
		controller.storeMessages(topic, message.toString());
	}

	public void elaborateMessage(String topic, MqttMessage message) {

		// The content of the message is processed and used

		String storeName = elaborateStore(topic);
		topic = elaborateTopic(topic);
		if (topic.compareTo("enter") == 0) {
			controller.enter(storeName, message.toString());

		} else if (topic.compareTo("exit") == 0) {
			controller.exit(storeName, message.toString());

		}

	}

	public String elaborateTopic(String topic) {

		String finalTopic = "";

		for (int i = topic.length() - 1; i >= 0; i--) {
			if (topic.charAt(i) != '/') {

				finalTopic = topic.charAt(i) + finalTopic;
			} else {

				return finalTopic;

			}

		}

		return finalTopic;
	}

	public String elaborateStore(String topic) {
		// The name of the store to send the message to is extracted from the topic

		String finalStore = "";

		boolean second = false;

		for (int i = topic.length() - 1; i >= 0; i--) {
			if (topic.charAt(i) != '/' && second == true) {

				finalStore = topic.charAt(i) + finalStore;
			} else if (topic.charAt(i) == '/' && second == true) {

				return finalStore;

			} else if (topic.charAt(i) == '/') {
				second = true;
			}

		}

		return finalStore;

	}

}
