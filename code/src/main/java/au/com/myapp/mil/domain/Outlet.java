package au.com.myapp.mil.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Outlet {

    public String getOutletID() {
        return outletID;
    }

    public void setOutletID(String outletID) {
        this.outletID = outletID;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Long getQtyDelivered() {
        return qtyDelivered;
    }

    public void setQtyDelivered(Long qtyDelivered) {
        this.qtyDelivered = qtyDelivered;
    }

    @Id
    @Column
    private String outletID;

    @Column
    private Integer value;

    @Column
    private Instant time;

    @Column
    private Long qtyDelivered;

}
