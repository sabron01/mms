package tn.welldone.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import tn.welldone.data.GroupeRepository;
import tn.welldone.model.Groupe;

@Stateless
@Local
public class GroupeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502279889876278987L;
	
	@Inject
	GroupeRepository groupeRepository;

	public Groupe addGroupe(Groupe groupe) {
		Groupe g = groupeRepository.add(groupe);
		return g;
	}

	public Groupe editGroupe(Groupe groupe) {
		groupeRepository.edit(groupe);
		return groupe;
	}

	public Groupe deleteGroupe(Groupe groupe) {
		groupeRepository.delete(groupe);
		return groupe;
	}

	public Groupe getGroupeById(int id) {
		return groupeRepository.getGroupeById(id);

	}

	public List<Groupe> getAllGroupes() {
		return groupeRepository.getAllGroupes();
	}

}
