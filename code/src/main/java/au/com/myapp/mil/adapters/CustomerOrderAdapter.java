package au.com.myapp.mil.adapters;

import au.com.myapp.mil.domain.CustomerOrder;
import au.com.myapp.mil.repositories.CustomerOrderRepository;
import au.com.myapp.mil.service.CustomerOrderService;
import org.apache.commons.lang.math.NumberUtils;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerOrderAdapter implements ExecutionListener {

    @Autowired
    CustomerOrderService customerOrderService;

    public void notify(DelegateExecution delegateExecution) throws Exception {
        // Note: You can catch any exception if there are any data format issues and return orderId as 0 and also set errorMessage
        // it all depends on the design
        String outletID = delegateExecution.getVariable("outletID").toString();
        Long flowRate = NumberUtils.toLong(delegateExecution.getVariable("flowRate").toString());
        Instant startTime = Instant.parse(delegateExecution.getVariable("startTime").toString());
        Instant endTime = Instant.parse(delegateExecution.getVariable("endTime").toString());
        CustomerOrder customerOrder = customerOrderService.save(outletID, flowRate, startTime, endTime);
        delegateExecution.setVariable("orderID", customerOrder.getOrderID());
    }
}
