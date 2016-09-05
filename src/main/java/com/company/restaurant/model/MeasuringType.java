package com.company.restaurant.model;

import com.company.restaurant.model.common.SimpleDic;

import java.io.Serializable;

/**
 * Created by Yevhen on 28.06.2016.
 */
public class MeasuringType extends SimpleDic implements Serializable {
    private String measuringTypeCode;

    public int getMeasuringTypeId() {
        return getId();
    }

    public void setMeasuringTypeId(int measuringTypeId) {
        setId(measuringTypeId);
    }

    public String getMeasuringTypeCode() {
        return measuringTypeCode;
    }

    public void setMeasuringTypeCode(String measuringTypeCode) {
        this.measuringTypeCode = measuringTypeCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeasuringType)) return false;
        if (!super.equals(o)) return false;

        MeasuringType that = (MeasuringType) o;

        return measuringTypeCode != null ? measuringTypeCode.equals(that.measuringTypeCode) :
                that.measuringTypeCode == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (measuringTypeCode != null ? measuringTypeCode.hashCode() : 0);
        return result;
    }
}
