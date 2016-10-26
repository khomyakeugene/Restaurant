package com.company.restaurant.model;

import com.company.restaurant.model.common.SimpleDic;

/**
 * Created by Yevhen on 03.08.2016.
 */
public class CommonData extends SimpleDic {
    private String value;
    private byte[] image;

    public int getCommonDataId() {
        return getId();
    }

    public void setCommonDataId(int commonDataId) {
        setId(commonDataId);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommonData)) return false;
        if (!super.equals(o)) return false;

        CommonData that = (CommonData) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
