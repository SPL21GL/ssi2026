/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;


@DatabaseTable(tableName = "STOLOC")
public class StoLoc {

    public String getStoLocId() {
        return stoLocId;
    }

    public void setStoLocId(String stoLocId) {
        this.stoLocId = stoLocId;
    }

    public Integer getPickSequence() {
        return pickSequence;
    }

    public void setPickSequence(Integer pickSequence) {
        this.pickSequence = pickSequence;
    }
    @DatabaseField(id = true, columnName = "stoLocId") private String stoLocId;
    @DatabaseField(columnName = "pickSequence") private Integer pickSequence;
    public StoLoc() {}
}

