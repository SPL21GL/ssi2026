/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;


@DatabaseTable(tableName = "PICKINGORDER")
public class PickingOrder {
    @DatabaseField( columnName = "pickingOrderId", generatedId = true) private Integer pickingOrderId;
    @DatabaseField(columnName = "status") private OrderState status;
    @DatabaseField(foreign = true, columnName = "userId") private AppUser user;
    @DatabaseField(columnName = "startTime") private Date startTime;
    @DatabaseField(columnName = "finishTime") private Date finishTime;
    @DatabaseField(foreign = true, columnName = "customerId") private Customer customer;
    @DatabaseField(columnName = "deliveryTime") private Date deliveryTime;

    public Integer getPickingOrderId() {
        return pickingOrderId;
    }

    public void setPickingOrderId(Integer pickingOrderId) {
        this.pickingOrderId = pickingOrderId;
    }

    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public PickingOrder() {}
}