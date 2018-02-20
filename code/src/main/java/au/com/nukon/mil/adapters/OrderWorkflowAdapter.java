package au.com.nukon.mil.adapters;

import au.com.nukon.mil.domain.CustomerOrder;
import au.com.nukon.mil.repositories.CustomerOrderRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.spin.SpinCollection;
import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import static org.camunda.spin.Spin.JSON;

@Component
public class OrderWorkflowAdapter implements JavaDelegate {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderWorkflowAdapter.class);

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("Looking DB for the records");
        Instant startTime = Instant.now();
        Instant endTime = startTime.plus(Duration.ofHours(1));
        if (delegateExecution.hasVariable("startTime") && delegateExecution.hasVariable("endTime")) {
            startTime = Instant.parse(delegateExecution.getVariable("startTime").toString());
            endTime = Instant.parse(delegateExecution.getVariable("endTime").toString());
        }
        Collection<CustomerOrder> collection = customerOrderRepository.findAllByStartTimeIsBetween(startTime, endTime);


        SpinJsonNode lst = JSON("[]");
        for (CustomerOrder customerOrder: collection) {
            SpinJsonNode jsonNode = JSON("{}");
            jsonNode.prop("outletID", customerOrder.getOutletID());
            jsonNode.prop("orderID", customerOrder.getOrderID());
            jsonNode.prop("startTime", customerOrder.getStartTime().toString());
            jsonNode.prop("endTime", customerOrder.getEndTime().toString());
            jsonNode.prop("flowRate", customerOrder.getFlowRate());
            lst.append(jsonNode);
        }
        delegateExecution.setVariable("records", lst.toString());
    }
}
