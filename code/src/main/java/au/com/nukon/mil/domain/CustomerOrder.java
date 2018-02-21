package au.com.nukon.mil.domain;


import java.time.Instant;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "customer_order")
public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private Long orderID;

    @Column(length=50)
    private String outletID;

    @Column
    private Instant startTime;

    @Column
    private Instant endTime;

    @Column
    private Long flowRate;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getOutletID() {
        return outletID;
    }

    public void setOutletID(String outletID) {
        this.outletID = outletID;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Long getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(Long flowRate) {
        this.flowRate = flowRate;
    }

}
