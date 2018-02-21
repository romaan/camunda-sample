package au.com.nukon.mil.adapters;

import org.apache.commons.lang.math.NumberUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DecisionMakerAdapter implements ExecutionListener{

    private final static Logger LOGGER = LoggerFactory.getLogger(DecisionMakerAdapter.class);

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("DecisionMakerAdapter invoked");
        int orderID = NumberUtils.toInt(delegateExecution.getVariable("orderID").toString());
        if (orderID % 2 == 0) {
            delegateExecution.setVariable("result", true);
        } else {
            delegateExecution.setVariable("result", false);
        }
    }
}
