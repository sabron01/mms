package tn.welldone.controller;

import helpers.RepeatPaginator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import tn.welldone.model.Consultation;
import tn.welldone.model.Employee;
import tn.welldone.model.Groupe;
import tn.welldone.model.Notification;
import tn.welldone.model.User;
import tn.welldone.service.GroupeService;
import tn.welldone.service.NotificationBean;
import tn.welldone.service.UserBean;

@Named("notificationController")
@RequestScoped
public class NotificationController implements Serializable {

	private static final long serialVersionUID = -3274547482962789369L;

	@EJB
	private UserBean userBean;
	
	@EJB
	private NotificationBean notificationBean;

	@EJB
	private GroupeService groupeService;

	private RepeatPaginator paginator;
	
	private Notification notification = new Notification();

	private Notification selectedNotification = new Notification();
	
	private User user = new User();

	private User selectedUser = new User();

	private Employee employee = new Employee();
	
	private Collection<Notification> notifications;
	
	private Collection<Notification> currentUserNotifications;
	
	private Collection<Notification> currentUserMessages;

	private Collection<Groupe> groupes = new ArrayList<Groupe>();

	private Collection<User> list;

	@javax.annotation.PostConstruct
	public void init() {
		setNotifications(notificationBean.getAllNotifications());
		setPaginator(new RepeatPaginator(new ArrayList(this.notifications)));
		setCurrentUserNotifications(notificationBean.getUncheckedNotificationsByUser());
		setCurrentUserMessages(notificationBean.getCurrentUserMessages());
	}

	public String createNewUser() {
		return "addUser.faces";
	}

	public String createUser() {
		user.setGroupes(groupes);
		user.setEmployee(employee);
		user = userBean.addUser(user);
		return "users.faces?faces-redirect=true";
	}

	public String showEditUser(int id) {
		User u = userBean.getUserById(id);
		setSelectedUser(u);
		return "showEditUser.faces";
	}

	public String deleteUser(int id) {
		User u = userBean.getUserById(id);
		userBean.deleteUser(u);
		return "users.faces?faces-redirect=true";
	}
	
	public String deleteNotification(int id) {
		Notification notification = notificationBean.getNotificationById(id);
		notificationBean.deleteNotification(notification);
		return "dashboard.faces?faces-redirect=true";
	}

	public String editUser() {
		userBean.editUser(selectedUser);
		return "users.faces?faces-redirect=true";
	}

	public String listUsers() {
		return "users.faces?faces-redirect=true";
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Collection<User> getList() {
		return list;
	}

	public void setList(Collection<User> list) {
		this.list = list;
	}

	public Collection<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Collection<Notification> getNotifications() {
		return notifications;
	}


	public void setNotifications(Collection<Notification> notifications) {
		this.notifications = notifications;
	}


	public Notification getNotification() {
		return notification;
	}


	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Collection<Notification> getCurrentUserNotifications() {
		return currentUserNotifications;
	}

	public void setCurrentUserNotifications(Collection<Notification> currentUserNotifications) {
		this.currentUserNotifications = currentUserNotifications;
	}

	public RepeatPaginator getPaginator() {
		return paginator;
	}

	public void setPaginator(RepeatPaginator paginator) {
		this.paginator = paginator;
	}

	public Collection<Notification> getCurrentUserMessages() {
		return currentUserMessages;
	}

	public void setCurrentUserMessages(Collection<Notification> currentUserMessages) {
		this.currentUserMessages = currentUserMessages;
	}

	public Notification getSelectedNotification() {
		return selectedNotification;
	}

	public void setSelectedNotification(Notification selectedNotification) {
		this.selectedNotification = selectedNotification;
	}

}
