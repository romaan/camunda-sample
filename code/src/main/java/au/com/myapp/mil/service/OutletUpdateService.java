package au.com.myapp.mil.service;

import au.com.myapp.mil.domain.CustomerOrder;
import au.com.myapp.mil.domain.Outlet;
import au.com.myapp.mil.repositories.CustomerOrderRepository;
import au.com.myapp.mil.repositories.OutletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OutletUpdateService {

    @Autowired
    OutletRepository outletRepository;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    private void updateOutlet(String outletID, Integer value, Instant time) {
        Outlet outlet = outletRepository.findByOutletID(outletID);
        if (outlet == null) {
            outlet = new Outlet();
            outlet.setQtyDelivered(0L);
        } else {
            outlet.setQtyDelivered((long) (outlet.getValue() - value));
        }
        outlet.setOutletID(outletID);
        outlet.setTime(time);
        outlet.setValue(value);
        outletRepository.save(outlet);
    }

    public void upsert(String outletID, Integer value, Instant time) {
        updateOutlet(outletID, value, time);
    }

    public void upsert(String outletID, Integer value, Instant time, Long orderID) {
        updateOutlet(outletID, value, time);
        CustomerOrder customerOrder = customerOrderRepository.findByOrderID(orderID);
        if (customerOrder != null) {
            customerOrder.setQtyDelivered(customerOrder.getQtyDelivered() + value);
            customerOrderRepository.save(customerOrder);
        }
    }

}
