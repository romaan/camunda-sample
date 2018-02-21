package au.com.nukon.mil.adapters;

import au.com.nukon.mil.domain.CustomerOrder;
import au.com.nukon.mil.service.CustomerOrderService;
import org.apache.commons.lang.math.NumberUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class SaveCustomerAdapter implements JavaDelegate {

    private final static Logger LOGGER = LoggerFactory.getLogger(SaveCustomerAdapter.class);

    @Autowired
    CustomerOrderService customerOrderService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long flowRate = NumberUtils.toLong(delegateExecution.getVariable("flowRate").toString());
        String outletID = delegateExecution.getVariable("outletID").toString();
        Instant startTime = Instant.parse(delegateExecution.getVariable("startTime").toString());
        Instant endTime = Instant.parse(delegateExecution.getVariable("endTime").toString());
        CustomerOrder customerOrder = customerOrderService.save(outletID, flowRate, startTime, endTime);
        delegateExecution.setVariable("orderID", customerOrder.getOrderID());
    }
}
