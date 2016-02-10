package tn.welldone.data;

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
import tn.welldone.model.Function;
import tn.welldone.model.User;

@Stateless
@Local
public class FunctionRepository {

	@PersistenceContext(unitName = "mmsPU")
	private EntityManager entityManager;

	@Inject
	private SessionUser session;

	public enum Action {
		CREATE, UPDATE, DELETE
	}

	public void setTransactionDetails(Function function, Action action) {
		User user = session.getUser();
		switch (action) {

		case CREATE:
			function.setAddedBy(user);
			function.setCreatedAt(new Date());
			function.setUpdateAt(new Date());
			break;

		case UPDATE:
			function.setEditedBy(user);
			function.setUpdateAt(new Date());
			break;

		case DELETE:
			function.setDeletedBy(user);
			function.setDeletedAt(new Date());
			break;
		}

	}

	public void add(Function function) {
		setTransactionDetails(function, Action.CREATE);
		function.setIsDeleted(false);
		entityManager.persist(function);
	}

	public Function getFunctionById(int id) {
		return entityManager.getReference(Function.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Function edit(Function function) {
		setTransactionDetails(function, Action.UPDATE);
		function.setIsDeleted(false);
		Function c = entityManager.merge(function);
		entityManager.flush();
		return c;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Function c) {

		setTransactionDetails(c, Action.DELETE);
		c.setIsDeleted(true);
		Function a = entityManager.merge(c);

		entityManager.flush();
	}

	public List<Function> getFunctions() {
		return entityManager.createQuery(
				"select c from Function c where c.isDeleted = 'false' ")
				.getResultList();
	}

}
