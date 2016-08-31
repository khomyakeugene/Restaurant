package com.company.restaurant.model.proto;

/**
 * Created by Yevhen on 23.05.2016.
 */
public class FloatLinkObject extends LinkObject {
    public Float getFloatLinkData() {
        return (linkData == null) ? null : Float.parseFloat(linkData);
    }

    public void setFloatLinkData(Float floatValue) {
        this.linkData = (floatValue == null) ? null : Float.toString(floatValue);
    }
}
