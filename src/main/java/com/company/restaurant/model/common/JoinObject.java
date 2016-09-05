package com.company.restaurant.model.common;

/**
 * Created by Yevhen on 23.06.2016.
 */
public class JoinObject {
    private int firstId;
    private int secondId;

    public int getFirstId() {
        return firstId;
    }

    public void setFirstId(int firstId) {
        this.firstId = firstId;
    }

    public int getSecondId() {
        return secondId;
    }

    public void setSecondId(int secondId) {
        this.secondId = secondId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoinObject)) return false;

        JoinObject that = (JoinObject) o;

        return firstId == that.firstId && secondId == that.secondId;
    }

    @Override
    public int hashCode() {
        int result = firstId;
        result = 31 * result + secondId;
        return result;
    }
}
