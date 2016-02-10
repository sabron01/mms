package tn.welldone.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LifeCycleListener implements PhaseListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8792148240337724095L;

	public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    public void beforePhase(PhaseEvent event) {
        //System.out.println("START PHASE " + event.getPhaseId());
    }

    public void afterPhase(PhaseEvent event) {
        //System.out.println("END PHASE " + event.getPhaseId());
    }

}