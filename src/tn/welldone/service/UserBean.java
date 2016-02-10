package tn.welldone.service;

import java.io.Serializable;
import java.util.Collection;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.UserRepository;
import tn.welldone.model.Permission;
import tn.welldone.model.User;

@Stateless
@Local
public class UserBean implements Serializable {

	private static final long serialVersionUID = 3491915688820634422L;

	@Inject
	UserRepository userRepository;
	
	public Collection<Permission> getPermissions(User user){
		return userRepository.getPermissions(user);
	}

	public User addUser(User user) {

		userRepository.add(user);
		return user;

	}

	public User editUser(User user) {
		userRepository.edit(user);
		return user;

	}

	public User deleteUser(User user) {
		userRepository.delete(user);
		return user;

	}
	
	public User getUserByLogin(String login) {
		return userRepository.getUserByLogin(login);

	}

	public User getUserById(int id) {
		return userRepository.getUserById(id);

	}

	public Collection<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

}
