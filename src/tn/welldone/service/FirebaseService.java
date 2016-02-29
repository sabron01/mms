package tn.welldone.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Named;

import tn.welldone.webSockets.DateUtil;
import tn.welldone.webSockets.FirebaseUtil;


@Stateless
@Local
public class FirebaseService {

  private static final String FIREBASE_URL = "https://mmsesprit.firebaseio.com/";

  private static final String NOTIFICATIONS = "/notifications/";

  private static final String FIRST_USER = "john-doe";

  private static final String SECOND_USER = "jane-doe";


  public void pushNotification(String subject,String sender,String type , String text) {
    Map<String, Object> data = new LinkedHashMap<>();

    data.put("subject", subject);
    data.put("sender", sender);
    data.put("type",type);
    data.put("sendTime",DateUtil.now().getTime());
    data.put("text",text);
    String url = FIREBASE_URL;
    //String url = FIREBASE_URL + NOTIFICATIONS + FIRST_USER;
    FirebaseUtil.writeToList(url, data);
  }

}
