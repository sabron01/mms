package tn.welldone.webSockets;

import java.util.Map;

import com.firebase.client.Firebase;

public class FirebaseUtil {
	public static void writeToList(String url, Map<String, Object> map) {
		long num = RandomNumberGenerator.generate();
		Firebase listRef = new Firebase(url + "/notifications/");
		map.put("_id", num);
		Firebase push = listRef.child("" + num);
		push.setValue(map);
	}
	
	
	public static void readNotifications(String url) {
		long num = RandomNumberGenerator.generate();
		Firebase firebase = new Firebase(url + "/data/");
	}
}
