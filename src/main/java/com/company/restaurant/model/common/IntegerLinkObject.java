package com.company.restaurant.model.common;

/**
 * Created by Yevhen on 23.05.2016.
 */
public class IntegerLinkObject extends LinkObject {
    public Integer getIntegerLinkData() {
        return (linkData == null) ? null : Integer.parseInt(linkData);
    }

    public void setIntegerLinkData(Integer intValue) {
        this.linkData = (intValue == null) ? null : Integer.toString(intValue);
    }
}
