/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "OBO_LINE")
public class OboLine {
    @DatabaseField(uniqueCombo = true, foreign = true, columnName = "orderId") private OutboundOrder outboundOrder;
    @DatabaseField(uniqueCombo = true, columnName = "lineNumber") private Integer lineNumber;
    @DatabaseField(foreign = true, columnName = "itemId") private Item item;
    @DatabaseField(columnName = "orderAmount") private Integer orderAmount;
    @DatabaseField(columnName = "deliveryAmount") private Integer deliveryAmount;

    public OutboundOrder getOutboundOrder() {
        return outboundOrder;
    }

    public void setOutboundOrder(OutboundOrder outboundOrder) {
        this.outboundOrder = outboundOrder;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
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

    public Integer getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }
    public OboLine() {}
}
