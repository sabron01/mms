package tn.welldone.controller;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import tn.welldone.model.Notification;
import tn.welldone.model.User;
import tn.welldone.service.UserBean;

@Named("sessionUser")
@SessionScoped
public class SessionUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;

    @EJB
	private UserBean userBean;

    public User getUser() {
        if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userBean.getUserByLogin(principal.getName());
            }
        }
        return user;
    }
     
	public void setUser(User user) {
		this.user = user;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "/home/dashboard?faces-redirect=true";
	}

}

