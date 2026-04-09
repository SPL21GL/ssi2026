/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "LOADINGUNIT")
public class LoadingUnit {
    @DatabaseField(id = true, columnName = "luId") private String luId;
    @DatabaseField(foreign = true, columnName = "stoLocId") private StoLoc stoLoc;
    @DatabaseField(columnName = "luType") private String luType;

    public String getLuId() {
        return luId;
    }

    public void setLuId(String luId) {
        this.luId = luId;
    }

    public StoLoc getStoLoc() {
        return stoLoc;
    }

    public void setStoLoc(StoLoc stoLoc) {
        this.stoLoc = stoLoc;
    }

    public String getLuType() {
        return luType;
    }

    public void setLuType(String luType) {
        this.luType = luType;
    }
    public LoadingUnit() {}
}