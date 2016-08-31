package com.company.restaurant.model.proto;

import java.io.Serializable;

/**
 * Created by Yevhen on 21.05.2016.
 */
public class SimpleDic implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleDic)) return false;

        SimpleDic simpleDic = (SimpleDic) o;

        return id == simpleDic.id && (name != null ? name.equals(simpleDic.name) : simpleDic.name == null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleDic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
