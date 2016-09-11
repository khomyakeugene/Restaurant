package com.company.restaurant.model.common;

import java.io.Serializable;

/**
 * Created by Yevhen on 21.05.2016.
 */
public class SimpleDic extends SimpleObject implements Serializable {
    private String name;

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
        if (!super.equals(o)) return false;

        SimpleDic simpleDic = (SimpleDic) o;

        return name != null ? name.equals(simpleDic.name) : simpleDic.name == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleDic{" +
                super.toString() + "\n" +
                "name='" + name + '\'' +
                '}';
    }
}
