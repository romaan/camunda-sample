package au.com.myapp.mil.repositories;

import au.com.myapp.mil.domain.CustomerOrder;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long>, Serializable {
    Collection<CustomerOrder> findAllByStartTimeIsBetween(Instant start, Instant end);

    CustomerOrder findByOrderID(Long orderID);
}
