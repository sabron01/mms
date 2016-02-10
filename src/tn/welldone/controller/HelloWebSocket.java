package tn.welldone.controller;

import java.io.IOException;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/hello")
public class HelloWebSocket {

   @OnOpen
   public void greetTheClient(Session session){
       try {
    	   System.out.println("From web socket");
        session.getBasicRemote().sendText("Hello stranger");

    } catch (IOException ioe) {
        System.out.println(ioe.getMessage());
    }
   }
}

