package au.com.nukon.mil.service;

import au.com.nukon.mil.domain.Outlet;
import au.com.nukon.mil.repositories.OutletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class OutletUpdateService {

    @Autowired
    OutletRepository outletRepository;

    public void upsert(String outletID, Integer value, Instant time) {
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
}
