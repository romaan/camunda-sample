package au.com.nukon.mil.service;

import au.com.nukon.mil.domain.CustomerOrder;
import au.com.nukon.mil.repositories.CustomerOrderRepository;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CustomerOrderService {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    public CustomerOrder save(String outletID, Long flowRate, Instant startTime, Instant endTime) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOutletID(outletID);
        customerOrder.setFlowRate(flowRate);
        customerOrder.setStartTime(startTime);
        customerOrder.setEndTime(endTime);
        customerOrderRepository.save(customerOrder);
        customerOrderRepository.flush();
        return customerOrder;
    }
}
