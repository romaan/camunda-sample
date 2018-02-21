package au.com.nukon.mil.repositories;

import au.com.nukon.mil.domain.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface OutletRepository extends JpaRepository<Outlet,Long>, Serializable {
    Outlet findByOutletID(String outletID);
}
