package au.com.nukon.mil.adapters;

import au.com.nukon.mil.domain.CustomerOrder;
import au.com.nukon.mil.repositories.CustomerOrderRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderAdapter implements ExecutionListener {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    public void notify(DelegateExecution delegateExecution) throws Exception {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderID(delegateExecution.getVariable("orderID").toString());
        customerOrder.setOutletID(delegateExecution.getVariable("outletID").toString());
        customerOrder.setFlowRate((Long)delegateExecution.getVariable("flowRate"));
        customerOrderRepository.save(customerOrder);
    }
}
