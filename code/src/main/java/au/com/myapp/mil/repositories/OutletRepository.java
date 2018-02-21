package au.com.myapp.mil.repositories;

import au.com.myapp.mil.domain.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface OutletRepository extends JpaRepository<Outlet,Long>, Serializable {
    Outlet findByOutletID(String outletID);
}
