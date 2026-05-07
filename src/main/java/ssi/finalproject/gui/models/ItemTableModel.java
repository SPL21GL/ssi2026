/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssi.finalproject.gui.models;

/**
 *
 * @author GL
 */
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import ssi.finalproject.entities.Item;

public class ItemTableModel extends AbstractTableModel {

    private final String[] columns = {
            "Item ID",
            "Name",
            "Länge",
            "Breite",
            "Höhe",
            "Picking StoLoc"
    };

    private final List<Item> items;

    public ItemTableModel(List<Item> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Item item = items.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> item.getItemId();
            case 1 -> item.getName();
            case 2 -> item.getItemLength();
            case 3 -> item.getItemWidth();
            case 4 -> item.getItemHeight();
            case 5 -> item.getPickingStoLoc() != null
                    ? item.getPickingStoLoc().toString()
                    : "";
            default -> "";
        };
    }

    public Item getItemAt(int row) {
        return items.get(row);
    }

    public void setItems(List<Item> newItems) {
        items.clear();
        items.addAll(newItems);
        fireTableDataChanged();
    }
}