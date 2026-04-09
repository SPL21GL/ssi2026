/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author estymate
 */
@DatabaseTable(tableName = "CUSTOMER")
public class Customer {

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAllowedLuType() {
        return allowedLuType;
    }

    public void setAllowedLuType(String allowedLuType) {
        this.allowedLuType = allowedLuType;
    }
    @DatabaseField(id = true, columnName = "customerId") private String customerId;
    @DatabaseField(columnName = "allowedLuType") private String allowedLuType;
    public Customer() {}
}