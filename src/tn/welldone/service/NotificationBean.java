package tn.welldone.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import tn.welldone.controller.SessionUser;
import tn.welldone.data.NotificationRepository;
import tn.welldone.data.UserRepository;
import tn.welldone.model.Employee;
import tn.welldone.model.MedicalJourney;
import tn.welldone.model.Notification;
import tn.welldone.model.Notification.Type;
import tn.welldone.model.Service;
import tn.welldone.model.Tache;
import tn.welldone.model.User;

@Local
@Stateless
@Named
public class NotificationBean implements Serializable {

	private static final long serialVersionUID = 9045986005423917507L;

	@Inject
	NotificationRepository notificationRepository;
	
	@Inject
	UserRepository userRepository;
	
	@Inject
	private SessionUser session;

	@Inject
	private transient Logger logger;

	public Notification getNotificationById(int id) {
		return notificationRepository.getNotificationById(id);
	}

	public Collection<Notification> getAllNotifications() {
		return notificationRepository.getNonDeletedNotifications();
	}

	public Collection<Notification> getAllNotificationsByUser(User user) {
		return notificationRepository.getAllNonDeletedNotificationsByUser(user);
	}
	
	public void generateTasks(MedicalJourney medicalJourney) {
		
		for (Employee u : medicalJourney.getAffectedEmployees()) {
			if (!u.equals(session.getUser().getEmployee())){
				// Create Task Notification
				Notification task = new Notification();
				Collection<User> targetUsers = new ArrayList<User>();
				targetUsers.add(u.getUser());
				task.setType(Type.Task);
				task.setTargetUsers(targetUsers);
				task.setMedicalJourney(medicalJourney);
				task.setText("Id: " + medicalJourney.getIdentifier()
						+ " pour patient :"
						+ medicalJourney.getPatient().getFirstName() + " "
						+ medicalJourney.getPatient().getLastName());
				task.setSubject("Tâche de Séjour");
				task.setStatus("");
				notificationRepository.add(task);
				
			}
		}
	}

	public void addNotification(MedicalJourney medicalJourney) {
		Notification notification = new Notification();
		Collection<User> targetUsers = new ArrayList<User>();
		for (User u : userRepository.getList()) {
			if (!u.equals(session.getUser()))
				targetUsers.add(u);
		}
		logger.info("Nb users AFTER :" + targetUsers.size());
		notification.setTargetUsers(targetUsers);
		notification.setMedicalJourney(medicalJourney);
		notification.setType(Type.Notification);
		notification.setText("Id: " + medicalJourney.getIdentifier()
				+ " pour patient :"
				+ medicalJourney.getPatient().getFirstName() + " "
				+ medicalJourney.getPatient().getLastName());
		notification.setSubject("Nouveau séjour");
		notification.setStatus("");
		notificationRepository.add(notification);
	}
	
	public void addCustomNotification(User u,MedicalJourney medicalJourney,String subject,String text){
		Notification notification = new Notification();
		Collection<User> targetUsers = new ArrayList<User>();
		if (u != null)
			targetUsers.add(u);
		notification.setTargetUsers(targetUsers);
		notification.setMedicalJourney(medicalJourney);
		notification.setType(Type.Notification);
		notification.setText(text);
		notification.setSubject(subject);
		notificationRepository.add(notification);
		
	}
	
	public void addTaskNotification(MedicalJourney medicalJourney, Service service) {
		Notification notification = new Notification();
		Collection<User> targetUsers = new ArrayList<User>();
		for (User u : userRepository.getList()) {
			if (!u.equals(session.getUser()))
				targetUsers.add(u);
		}
		logger.info("Nb users AFTER :" + targetUsers.size());
		notification.setTargetUsers(targetUsers);
		notification.setMedicalJourney(medicalJourney);
		notification.setType(Type.Task);
		String text = "New Task of "+service.getLabel()+" is assigned for M-J "+medicalJourney.getIdentifier();
		notification.setText(text);
		notification.setSubject("Nouvelle Tâche");
		notification.setStatus("");
		notificationRepository.addTask(notification);
	}

	public Notification addNotification(Notification notification) {
		notificationRepository.add(notification);
		return notification;
	}

	public Notification editNotification(Notification notification) {
		notificationRepository.edit(notification);
		return notification;
	}

	public Notification deleteNotification(Notification notification) {
		notificationRepository.delete(notification);
		return notification;
	}

	public Collection<Notification> getUncheckedNotificationsByUser() {
		return notificationRepository.getUncheckedNotificationsByUser();
	}

	public Collection<Notification> getNotificationsByUser(User user) {
		return notificationRepository.getNotificationsByUser(user);
	}

	public void addMessage(Notification notification) {
		notificationRepository.addMessage(notification);

	}

	public Collection<Notification> getCurrentUserMessages() {
		return notificationRepository.getCurrentUserMessages();
	}

	public Collection<Notification> getUncheckeTasksByUser() {
		return notificationRepository.getUncheckeTasksByUser();
	}



}
