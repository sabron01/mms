package tn.welldone.ressources;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class Resources {

	@Produces
	public Logger getLogger(InjectionPoint p) {
		return Logger.getLogger(p.getMember().getDeclaringClass().getName());
	}

}
