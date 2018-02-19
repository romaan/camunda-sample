package au.com.nukon.mil.domain;


import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "customer_order")
public class CustomerOrder implements Serializable {

    @Id
    @Column(length=50)
    private String orderID;

    @Column(length=50)
    private String outletID;

    @Column(length=50)
    private DateTime startTime;

    @Column(length=50)
    private DateTime endTime;

    @Column(length=50)
    private Long flowRate;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOutletID() {
        return outletID;
    }

    public void setOutletID(String outletID) {
        this.outletID = outletID;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public Long getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(Long flowRate) {
        this.flowRate = flowRate;
    }

}
