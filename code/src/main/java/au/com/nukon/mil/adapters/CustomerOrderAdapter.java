package au.com.nukon.mil.adapters;

import au.com.nukon.mil.domain.CustomerOrder;
import au.com.nukon.mil.repositories.CustomerOrderRepository;
import org.apache.commons.lang.math.NumberUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerOrderAdapter implements ExecutionListener {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    public void notify(DelegateExecution delegateExecution) throws Exception {
        // Note: You can catch any exception if there are any data format issues and return orderId as 0 and also set errorMessage
        // it all depends on the design
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOutletID(delegateExecution.getVariable("outletID").toString());
        customerOrder.setFlowRate(NumberUtils.toLong(delegateExecution.getVariable("flowRate").toString()));
        customerOrder.setStartTime(Instant.parse(delegateExecution.getVariable("startTime").toString()));
        customerOrder.setEndTime(Instant.parse(delegateExecution.getVariable("endTime").toString()));
        customerOrderRepository.save(customerOrder);
        customerOrderRepository.flush();
        delegateExecution.setVariable("orderID", customerOrder.getOrderID());
    }
}
