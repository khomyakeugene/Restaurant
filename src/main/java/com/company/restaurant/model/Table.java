package com.company.restaurant.model;

import com.company.restaurant.model.common.SimpleDic;

/**
 * Created by Yevhen on 22.05.2016.
 */
public class Table extends SimpleDic {
    private String description;

    public int getTableId() {
        return getId();
    }

    public void setTableId(int tableId) {
        setId(tableId);
    }

    public int getNumber() {
        return Integer.parseInt(getName());
    }

    public void setNumber(int number) {
        setName(Integer.toString(number));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        if (!super.equals(o)) return false;

        Table table = (Table) o;

        return description != null ? description.equals(table.description) : table.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Table{" +
                super.toString() +
                " description='" + description + '\'' +
                '}';
    }
}
