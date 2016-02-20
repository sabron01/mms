package tn.welldone.webSockets;

import java.text.SimpleDateFormat;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import tn.welldone.model.Notification;

public class NotificationEncoder implements Encoder.Text<Notification> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String encode(Notification notification) throws EncodeException {
		JsonObject jsonObject = null;
		System.out.println("Notification Type : "+notification.getType());
		if (notification.getType().toString().equals("Message")) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
			jsonObject = Json.createObjectBuilder()
					.add("subject", notification.getSubject())
					.add("sender",notification.getNotifiedBy().getEmployee().getFirstName()+" "+notification.getNotifiedBy().getEmployee().getFirstName())
					.add("type", notification.getType().toString())
					.add("sendTime",dateFormatter.format(notification.getCreatedAt()))
					.add("text", notification.getText()).build();
		}
		if (notification.getType().toString().equals("Notification")) {
			jsonObject = Json
					.createObjectBuilder()
					.add("subject", notification.getSubject())
					.add("medicalJourney",
							notification.getMedicalJourney().toString())
					.add("type", notification.getType().toString())
					.add("text", notification.getText()).build();
		}
		if (notification.getType().toString().equals("Task")) {
			jsonObject = Json
					.createObjectBuilder()
					.add("subject", notification.getSubject())
					.add("medicalJourney",
							notification.getMedicalJourney().toString())
					.add("type", notification.getType().toString())
					.add("text", notification.getText()).build();
		}
		return jsonObject.toString();
	}

}