package tn.welldone.data;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.welldone.controller.SessionUser;
import tn.welldone.model.Department;
import tn.welldone.model.Employee;
import tn.welldone.model.User;

@Stateless
@Local
@Named
public class DepartmentRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Department department, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			department.setAddedBy(user);
			department.setCreatedAt(new Date());
			department.setUpdateAt(new Date());
			break;

		case UPDATE:
			department.setEditedBy(user);
			department.setUpdateAt(new Date());
			break;

		case DELETE:
			department.setDeletedBy(user);
			department.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Department department) {
		setTransactionDetails(department, Action.CREATE);
		department.setIsDeleted(false);
		entityManager.persist(department);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void edit(Department department) {
		setTransactionDetails(department, Action.UPDATE);
		department.setIsDeleted(false);
		entityManager.merge(department);
		entityManager.flush();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Department department) {
		setTransactionDetails(department, Action.DELETE);
		// Option 1
		department.setIsDeleted(true);
		Department p = entityManager.merge(department);
		// Option 2
		// entityManager.remove(service);
		entityManager.flush();
	}

	List<Department> getList() {
		return entityManager.createQuery("select p from Department p")
				.getResultList();
	}

	public List<Department> getNonDeletedProviders() {
		return entityManager.createQuery(
				"select p from Department p where p.isDeleted = 'false' ")
				.getResultList();
	}

	public Department getDepartmentById(int id) {
		return entityManager.getReference(Department.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Department add(Department department, Employee manager) {
		department.setIsDeleted(false);
		entityManager.persist(department);
		department.setChief(manager);
		Department d = entityManager.merge(department);
		entityManager.flush();
		return d;
	}

	public List<Department> getAllDepartments() {
		return entityManager.createQuery(
				"select d from Department d where d.isDeleted = 'false' ")
				.getResultList();
	}

}
