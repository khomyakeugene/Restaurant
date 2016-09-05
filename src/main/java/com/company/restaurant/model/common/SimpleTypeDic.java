package com.company.restaurant.model.common;

/**
 * Created by Yevhen on 25.06.2016.
 */
public class SimpleTypeDic {
    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(o instanceof SimpleTypeDic)) return false;

        SimpleTypeDic that = (SimpleTypeDic) o;

        return type != null ? type.equals(that.type) : that.type == null && (name != null ?
                name.equals(that.name) : that.name == null);

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimpleTypeDic{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
