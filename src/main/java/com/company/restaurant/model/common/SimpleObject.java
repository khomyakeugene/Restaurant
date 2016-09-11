package com.company.restaurant.model.common;

/**
 * Created by Yevhen on 11.09.2016.
 */
public class SimpleObject {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleObject)) return false;

        SimpleObject that = (SimpleObject) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "SimpleObject{" + "id=" + id + '}';
    }
}
