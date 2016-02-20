/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.welldone.webSockets;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.sun.security.auth.UserPrincipal;

import tn.welldone.model.Notification;
import tn.welldone.model.User;
import tn.welldone.service.UserBean;

@Singleton
@ServerEndpoint(value = "/mediatorendpoint", encoders = { NotificationEncoder.class })
public class SocketMediator {

	@Inject
	UserBean userBean;

	private static Set<Session> peers = Collections
			.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public String onMessage(String message, Session session) {
		System.out.println("message received " + message);
		for (Session peer : peers) {
			if (!peer.equals(session)) {
				try {
					peer.getBasicRemote().sendText(message + " - retweet");
				} catch (IOException ex) {
					Logger.getLogger(SocketMediator.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		}
		return "message was received and processed: " + message;
	}

	@OnOpen
	public void onOpen(Session peer) {
		peers.add(peer);
	}

	public void onTimeEvent(@Observes @WBTimeEvent TimeEvent event) {
		System.out.println("Time Event observed " + event.getTimestamp());

		for (Session peer : peers) {
			try {
				peer.getBasicRemote().sendText(
						"Time event: " + event.getTimestamp());
			} catch (IOException ex) {
				Logger.getLogger(SocketMediator.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}

	}

	public void onNotification(@Observes @WBTimeEvent Notification notification)
			throws EncodeException {

		System.out.println("Notification Fired ");

		System.out.println("Notification observed " + notification.getText());

		for (Session peer : peers) {
			try {
				Principal principal = peer.getUserPrincipal();
				User u = userBean.getUserByLogin(principal.getName());
				if (!(notification.getType().toString().equals("Message") && notification
						.getTargetUsers().contains(u)))
					peer.getBasicRemote().sendObject(notification);
				// peer.getBasicRemote().sendText("Time event: " +
				// notification.getText());
			} catch (IOException ex) {
				Logger.getLogger(SocketMediator.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}

	}

	@OnClose
	public void onClose(Session peer) {
		peers.remove(peer);
	}
}
