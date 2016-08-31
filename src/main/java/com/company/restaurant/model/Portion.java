package com.company.restaurant.model;

import java.io.Serializable;

/**
 * Created by Yevhen on 24.05.2016.
 */
public class Portion implements Serializable {
    private int portionId;
    private Float amount;
    private String description;
    private PortionType portionType = new PortionType();
    private MeasuringType measuringType = new MeasuringType();

    public int getPortionId() {
        return portionId;
    }

    public void setPortionId(int portionId) {
        this.portionId = portionId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PortionType getPortionType() {
        return portionType;
    }

    public void setPortionType(PortionType portionType) {
        this.portionType = portionType;
    }

    public MeasuringType getMeasuringType() {
        return measuringType;
    }

    public void setMeasuringType(MeasuringType measuringType) {
        this.measuringType = measuringType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Portion)) return false;

        Portion portion = (Portion) o;

        return portionId == portion.portionId && (amount != null ?
                amount.equals(portion.amount) : portion.amount == null && (description != null ?
                description.equals(portion.description) :
                portion.description == null && (portionType != null ?
                        portionType.equals(portion.portionType) :
                        portion.portionType == null && (measuringType != null ?
                                measuringType.equals(portion.measuringType) :
                                portion.measuringType == null))));

    }

    @Override
    public int hashCode() {
        int result = portionId;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (portionType != null ? portionType.hashCode() : 0);
        result = 31 * result + (measuringType != null ? measuringType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Portion{" +
                "portionId=" + portionId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", portionType=" + portionType +
                ", measuringType=" + measuringType +
                '}';
    }
}
