package au.com.nukon.mil.repositories;

import au.com.nukon.mil.domain.CustomerOrder;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder,Long>, Serializable {
    Collection<CustomerOrder> findAllByStartTimeIsBetween(Instant start, Instant end);
}
