package tn.welldone.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Department;
import tn.welldone.model.Employee;
import tn.welldone.model.Location;
import tn.welldone.model.PhoneNumber;
import tn.welldone.model.Service;
import tn.welldone.model.User;

@Stateless
@Local
public class EmployeeRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Employee employee, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			employee.setAddedBy(user);
			employee.setCreatedAt(new Date());
			employee.setUpdateAt(new Date());
			break;

		case UPDATE:
			employee.setEditedBy(user);
			employee.setUpdateAt(new Date());
			break;

		case DELETE:
			employee.setDeletedBy(user);
			employee.setDeletedAt(new Date());
			break;
		}

	}

	public void setTransactionDetails(PhoneNumber phone, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			phone.setAddedBy(user);
			phone.setCreatedAt(new Date());
			phone.setUpdateAt(new Date());
			break;

		case UPDATE:
			phone.setEditedBy(user);
			phone.setUpdateAt(new Date());
			break;

		case DELETE:
			phone.setDeletedBy(user);
			phone.setDeletedAt(new Date());
			break;
		}

	}

	public void setTransactionDetails(Location location, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			location.setAddedBy(user);
			location.setCreatedAt(new Date());
			location.setUpdateAt(new Date());
			break;

		case UPDATE:
			location.setEditedBy(user);
			location.setUpdateAt(new Date());
			break;

		case DELETE:
			location.setDeletedBy(user);
			location.setDeletedAt(new Date());
			break;
		}

	}

	public Employee getEmployeeById(int id) {
		return entityManager.getReference(Employee.class, id);
	}

	public void add(Employee employee) {
		setTransactionDetails(employee, Action.CREATE);
		employee.setIsDeleted(false);
		entityManager.persist(employee);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Employee add(Employee employee, Department department,
			Employee manager, Department managedDepartment) {
		setTransactionDetails(employee, Action.CREATE);
		employee.setIsDeleted(false);
		entityManager.persist(employee);
		employee.setDepartment(department);
		// employee.setManagedDepartment(managedDepartment);
		employee.setManager(manager);
		Employee e = entityManager.merge(employee);
		entityManager.flush();
		return e;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Employee add(Employee employee, Collection<Location> addresses,
			Collection<PhoneNumber> phoneNumbers, Department department,
			Employee manager, Department managedDepartment) {
		setTransactionDetails(employee, Action.CREATE);
		employee.setIsDeleted(false);
		for (PhoneNumber phone : phoneNumbers) {
			setTransactionDetails(phone, Action.CREATE);
			entityManager.persist(phone);
		}
		for (Location location : addresses) {
			setTransactionDetails(location, Action.CREATE);
			entityManager.persist(location);
		}
		entityManager.persist(employee);
		employee.setDepartment(department);
		// employee.setManagedDepartment(managedDepartment);
		employee.setManager(manager);
		Employee e = entityManager.merge(employee);
		entityManager.flush();
		return e;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Employee edit(Employee employee, Collection<Location> addresses,
			Collection<PhoneNumber> phoneNumbers, Department department,
			Employee manager, Department managedDepartment) {
		Collection<PhoneNumber> phoneNumber = new ArrayList<PhoneNumber>();
		Collection<Location> locations = new ArrayList<Location>();
		setTransactionDetails(employee, Action.UPDATE);
		for (PhoneNumber phone : phoneNumbers) {
			setTransactionDetails(phone, Action.UPDATE);
			PhoneNumber p = entityManager.merge(phone);
			phoneNumber.add(p);
		}
		for (Location location : addresses) {
			setTransactionDetails(location, Action.UPDATE);
			Location l = entityManager.merge(location);
			locations.add(l);
		}
		employee.setIsDeleted(false);
		employee.setAddresses(locations);
		employee.setPhoneNumbers(phoneNumber);
		employee.setDepartment(department);
		employee.setManagedDepartment(managedDepartment);
		// employee.setManager(manager);
		Employee e = entityManager.merge(employee);
		entityManager.flush();
		return e;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Employee edit(Employee employee, Department department,
			Employee manager, Department managedDepartment) {
		setTransactionDetails(employee, Action.UPDATE);
		employee.setIsDeleted(false);
		employee.setDepartment(department);
		employee.setManagedDepartment(managedDepartment);
		entityManager.flush();
		// employee.setManager(manager);
		Employee e = entityManager.merge(employee);
		entityManager.flush();
		return e;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Employee edit(Employee employee) {
		setTransactionDetails(employee, Action.UPDATE);
		employee.setIsDeleted(false);
		Employee c = entityManager.merge(employee);
		entityManager.flush();
		return c;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Employee c) {
		setTransactionDetails(c, Action.DELETE);
		// Option 1
		c.setIsDeleted(true);
		Employee a = entityManager.merge(c);
		// Option 2
		// entityManager.remove(service);

		entityManager.flush();
	}

	public List<Employee> getEmployees() {
		return entityManager.createQuery(
				"select e from Employee e where e.isDeleted = 'false' ")
				.getResultList();
	}

	public List<Employee> getManagers() {
		return entityManager
				.createQuery(
						"select e from Employee e where e.isDeleted = 'false' AND e.isManager = true ")
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Employee> getEmployeesByService(Service service) {
		switch (service.getId()) {

		case 1:
			return entityManager
					.createQuery(
							"select e from Employee e where e.isDeleted = 'false' AND e.function IN (5,3)")
					.getResultList();
		case 2:
			return entityManager
					.createQuery(
							"select e from Employee e where e.isDeleted = 'false' AND e.function IN (6)")
					.getResultList();

		case 3:
			return entityManager
					.createQuery(
							"select e from Employee e where e.isDeleted = 'false' AND e.function IN (7,8)")
					.getResultList();

		case 4:
			return entityManager
					.createQuery(
							"select e from Employee e where e.isDeleted = 'false' AND e.function IN (5,3)")
					.getResultList();

		case 5:
			return entityManager
					.createQuery(
							"select e from Employee e where e.isDeleted = 'false' AND e.function IN (7,8)")
					.getResultList();

		}
		return null;

	}

}
