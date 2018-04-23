package knowledgebank.web.listener;

import java.util.logging.Logger;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class KnowledgePhaseListener implements PhaseListener {

    Logger logger = Logger.getLogger(KnowledgePhaseListener.class.getName());

    @Override
    public void beforePhase(PhaseEvent event) {
        logger.info(event.getPhaseId() + " beforePhase");
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        logger.info(event.getPhaseId() + " afterPhase");
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
