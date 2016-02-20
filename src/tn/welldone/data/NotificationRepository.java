package tn.welldone.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Notification;
import tn.welldone.model.Notification.Type;
import tn.welldone.model.User;
import tn.welldone.webSockets.TimeEvent;
import tn.welldone.webSockets.WBTimeEvent;

@Stateless
@Local
public class NotificationRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	@Inject
	UserRepository userRepository;
	
	@Inject  @WBTimeEvent Event<Notification> notificationEvent;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Notification notification, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			notification.setIsActive(true);
			notification.setIsChecked(false);
			notification.setNotifiedBy(user);
			notification.setAddedBy(user);
			notification.setCreatedAt(new Date());
			notification.setUpdateAt(new Date());
			break;

		case UPDATE:
			notification.setEditedBy(user);
			notification.setUpdateAt(new Date());
			break;

		case DELETE:
			notification.setDeletedBy(user);
			notification.setDeletedAt(new Date());
			break;
		}

	}

	public void addMessage(Notification notification) {
		setTransactionDetails(notification, Action.CREATE);
		notification.setIsActive(true);
		notification.setType(Type.Message);
		notification.setIsChecked(false);
		notification.setIsDeleted(false);
		entityManager.persist(notification);
		entityManager.flush();
		notificationEvent.fire(notification);
	}

	public void addTask(Notification notification) {
		setTransactionDetails(notification, Action.CREATE);
		notification.setIsActive(true);
		notification.setType(Type.Task);
		notification.setIsChecked(false);
		notification.setIsDeleted(false);
		entityManager.persist(notification);
		entityManager.flush();
		notificationEvent.fire(notification);
	}
	
	public void add(Notification notification) {
		setTransactionDetails(notification, Action.CREATE);
		notification.setIsActive(true);
		notification.setType(Type.Notification);
		notification.setIsChecked(false);
		notification.setIsDeleted(false);
		entityManager.persist(notification);
		entityManager.flush();
		notificationEvent.fire(notification);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Notification notification) {
		notification.setIsDeleted(false);
		setTransactionDetails(notification, Action.UPDATE);
		Notification m = entityManager.merge(notification);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Notification notification) {
		setTransactionDetails(notification, Action.DELETE);
		notification.setIsDeleted(true);
		Notification m = entityManager.merge(notification);
		entityManager.flush();
	}

	List<Notification> getList() {
		return entityManager.createQuery("select m from Notification m")
				.getResultList();

	}

	public Collection<Notification> getNonDeletedNotifications() {
		return entityManager.createQuery(
				"select m from Notification m where m.type = 'Notification' AND m.isDeleted = 'false' ")
				.getResultList();

	}

	public Notification getNotificationById(int id) {
		return entityManager.getReference(Notification.class, id);
	}

	public Collection<Notification> getUncheckedNotificationsByUser() {
		Collection<Notification> notifications = new ArrayList<Notification>();
		for (Notification n : session.getUser().getNotifications()) {
			if (!n.getIsChecked() && n.getType().equals(Type.Notification)
					&& !n.getIsDeleted())
				notifications.add(n);
		}
		return notifications;
	}

	public Collection<Notification> getNotificationsByUser(User user) {
		Collection<Notification> notifications = new ArrayList<Notification>();
		for (Notification n : user.getNotifications()) {
			if (n.getIsChecked() && n.getType().equals(Type.Notification)
					&& !n.getIsDeleted())
				notifications.add(n);
		}
		return notifications;
	}

	public Collection<Notification> getCurrentUserMessages() {
		Collection<Notification> messages = new ArrayList<Notification>();
		for (Notification n : session.getUser().getNotifications()) {
			if (!n.getIsChecked() && n.getType().equals(Type.Message)
					&& !n.getIsDeleted())
				messages.add(n);
		}
		return messages;
	}

	public Collection<Notification> getAllNonDeletedNotificationsByUser(
			User user) {
		Collection<Notification> notifications = new ArrayList<Notification>();
		for (Notification n : user.getNotifications()) {
			if (n.getType().equals(Type.Notification) && !n.getIsDeleted())
				notifications.add(n);
		}
		return notifications;
	}

	public Collection<Notification> getUncheckeTasksByUser() {
		Collection<Notification> tasks = new ArrayList<Notification>();
		for (Notification n : session.getUser().getNotifications()) {
			if (!n.getIsChecked() && n.getType().equals(Type.Task)
					&& !n.getIsDeleted())
				tasks.add(n);
		}
		return tasks;
	}



}
