/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "STOCKOBJECT")
public class StockObject {
    // Composite Key Workaround
    @DatabaseField( columnName = "stockObjectId", generatedId = true) private Integer StockObjectId;
    
    @DatabaseField( foreign = true, columnName = "luId") private LoadingUnit loadingUnit;
    @DatabaseField( columnName = "stockObjectNumber") private Integer stockObjectNumber;
    @DatabaseField(foreign = true, columnName = "itemId") private Item item;
    @DatabaseField(columnName = "amount") private Integer amount;

    public LoadingUnit getLoadingUnit() {
        return loadingUnit;
    }

    public void setLoadingUnit(LoadingUnit loadingUnit) {
        this.loadingUnit = loadingUnit;
    }

    public Integer getStockObjectNumber() {
        return stockObjectNumber;
    }

    public void setStockObjectNumber(Integer stockObjectNumber) {
        this.stockObjectNumber = stockObjectNumber;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public StockObject() {}
}