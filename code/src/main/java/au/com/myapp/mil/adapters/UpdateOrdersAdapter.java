package au.com.myapp.mil.adapters;

import au.com.myapp.mil.service.OutletUpdateService;
import org.apache.commons.lang.math.NumberUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UpdateOrdersAdapter implements JavaDelegate {

    @Autowired
    private OutletUpdateService outletUpdateService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if (!delegateExecution.hasVariable("outletID") || !delegateExecution.hasVariable("value") || !delegateExecution.hasVariable("time")) {
            return;
        }
        String outletID = delegateExecution.getVariable("outletID").toString();
        Instant time = Instant.parse(delegateExecution.getVariable("time").toString());
        Integer value = NumberUtils.toInt(delegateExecution.getVariable("value").toString());
        if (delegateExecution.hasVariable("orderID")) {
            Long orderID = NumberUtils.toLong(delegateExecution.getVariable("orderID").toString());
            outletUpdateService.upsert(outletID, value, time, orderID);
        } else {
            outletUpdateService.upsert(outletID, value, time);
        }
    }

}
