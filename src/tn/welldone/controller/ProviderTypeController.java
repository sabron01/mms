package tn.welldone.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import tn.welldone.model.ProviderType;
import tn.welldone.service.ProviderTypeBean;

@Named("providerTypeController")
@RequestScoped
@ViewScoped
public class ProviderTypeController implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1800313847517207223L;

	@EJB
	private ProviderTypeBean providerTypeBean;
	
	private List<ProviderType> list;
    
	private ProviderType providerType = new ProviderType();
	
	private ProviderType selectedProviderType = new ProviderType();
	
	
	@javax.annotation.PostConstruct
	public void init() {
		setList(providerTypeBean.getAllProviderTypes());
	}

	public String createNewProviderType() {
		return "addProviderType.faces";
	}
	
	public String createProviderType() {
		providerType = providerTypeBean.addProviderType(providerType);
		return "providerType.faces?faces-redirect=true";
	}

	public String showEditProviderType(int id) {
		ProviderType p = providerTypeBean.getProviderTypeById(id);
		setSelectedProviderType(p);
		return "showEditProviderType.faces";
	}
	
	public String deleteProviderType(int id) {
		ProviderType p = providerTypeBean.getProviderTypeById(id);
		providerTypeBean.deleteProviderType(p);
		return "providerType.faces?faces-redirect=true";
	}

	public String editProviderType() {
		providerTypeBean.editProviderType(selectedProviderType);
		return "providerType.faces?faces-redirect=true";
	}

	public String listProviderTypes() {
		return "providerType.faces?faces-redirect=true";
	}
	

	public ProviderTypeBean getProviderTypeBean() {
		return providerTypeBean;
	}

	public void setProviderTypeBean(ProviderTypeBean providerTypeBean) {
		this.providerTypeBean = providerTypeBean;
	}

	public ProviderType getProviderType() {
		return providerType;
	}

	public void setProviderType(ProviderType providerType) {
		this.providerType = providerType;
	}

	public ProviderType getSelectedProviderType() {
		return selectedProviderType;
	}

	public void setSelectedProviderType(ProviderType selectedProviderType) {
		this.selectedProviderType = selectedProviderType;
	}

	public List<ProviderType> getList() {
		return list;
	}

	public void setList(List<ProviderType> list) {
		this.list = list;
	}
	
	

}
