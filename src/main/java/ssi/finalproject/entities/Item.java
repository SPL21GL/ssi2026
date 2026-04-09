/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ITEM")
public class Item {

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemLength() {
        return itemLength;
    }

    public void setItemLength(Integer itemLength) {
        this.itemLength = itemLength;
    }

    public Integer getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(Integer itemWidth) {
        this.itemWidth = itemWidth;
    }

    public Integer getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(Integer itemHeight) {
        this.itemHeight = itemHeight;
    }

    public StoLoc getPickingStoLoc() {
        return pickingStoLoc;
    }

    public void setPickingStoLoc(StoLoc pickingStoLoc) {
        this.pickingStoLoc = pickingStoLoc;
    }
    @DatabaseField(id = true, columnName = "itemId") private String itemId;
    @DatabaseField(columnName = "name") private String name;
    @DatabaseField(columnName = "itemLength") private Integer itemLength;
    @DatabaseField(columnName = "itemWidth") private Integer itemWidth;
    @DatabaseField(columnName = "itemHeight") private Integer itemHeight;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "pickingStoLoc") private StoLoc pickingStoLoc;
    public Item() {}
}
