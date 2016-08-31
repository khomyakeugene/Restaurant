package com.company.restaurant.model;

import java.util.Arrays;

/**
 * Created by Yevhen on 03.08.2016.
 */
public class CommonData {
    private int commonDataId;
    private String name;
    private String value;
    private byte[] image;

    public int getCommonDataId() {
        return commonDataId;
    }

    public void setCommonDataId(int commonDataId) {
        this.commonDataId = commonDataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (o == null || getClass() != o.getClass()) return false;

        CommonData that = (CommonData) o;

        return commonDataId == that.commonDataId && (name != null ? name.equals(that.name) :
                that.name == null && (value != null ? value.equals(that.value) :
                        that.value == null && Arrays.equals(image, that.image)));

    }

    @Override
    public int hashCode() {
        int result = commonDataId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
