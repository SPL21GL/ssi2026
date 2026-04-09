/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



@DatabaseTable(tableName = "PICKINGORDER_LINE")
public class PickingOrderLine {
    @DatabaseField(uniqueCombo = true, foreign = true, columnName = "pickingOrderId") private PickingOrder pickingOrder;
    @DatabaseField(uniqueCombo = true, columnName = "lineNumber") private Integer lineNumber;
    @DatabaseField(columnName = "status") private OrderState status;
    @DatabaseField(columnName = "pickSequence") private Integer pickSequence;
    @DatabaseField(foreign = true, columnName = "stoLocId") private StoLoc stoLoc;
    @DatabaseField(foreign = true, columnName = "itemId") private Item item;
    @DatabaseField(columnName = "orderAmount") private Integer orderAmount;
    @DatabaseField(columnName = "bookingAmount") private Integer bookingAmount;
    // Composite FK mapped als einfache Felder
    @DatabaseField(columnName = "targetLuId") private String targetLuId;
    @DatabaseField(columnName = "targetStockObject") private Integer targetStockObject;

    public PickingOrder getPickingOrder() {
        return pickingOrder;
    }

    public void setPickingOrder(PickingOrder pickingOrder) {
        this.pickingOrder = pickingOrder;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }

    public Integer getPickSequence() {
        return pickSequence;
    }

    public void setPickSequence(Integer pickSequence) {
        this.pickSequence = pickSequence;
    }

    public StoLoc getStoLoc() {
        return stoLoc;
    }

    public void setStoLoc(StoLoc stoLoc) {
        this.stoLoc = stoLoc;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getBookingAmount() {
        return bookingAmount;
    }

    public void setBookingAmount(Integer bookingAmount) {
        this.bookingAmount = bookingAmount;
    }

    public String getTargetLuId() {
        return targetLuId;
    }

    public void setTargetLuId(String targetLuId) {
        this.targetLuId = targetLuId;
    }

    public Integer getTargetStockObject() {
        return targetStockObject;
    }

    public void setTargetStockObject(Integer targetStockObject) {
        this.targetStockObject = targetStockObject;
    }
    public PickingOrderLine() {}
}