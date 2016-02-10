package tn.welldone.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Patient extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codePatient;
	private Collection<Patient> accompaniedPersons;

	
	public String getCodePatient() {
		return codePatient;
	}
	
	public void setCodePatient(String codePatient) {
		this.codePatient = codePatient;
	}

	//Need to check Fetch Type
//	@OneToMany(fetch=FetchType.LAZY)
	@OneToMany
	public Collection<Patient> getAccompaniedPersons() {
		return accompaniedPersons;
	}

	public void setAccompaniedPersons(Collection<Patient> accompaniedPersons) {
		this.accompaniedPersons = accompaniedPersons;
	}


}