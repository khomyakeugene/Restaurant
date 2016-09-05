package com.company.restaurant.model.common;

import java.io.Serializable;

/**
 * Created by Yevhen on 21.05.2016.
 */
public class LinkObject extends JoinObject implements Serializable  {
    protected String linkData;

    public String getLinkData() {
        return linkData;
    }

    public void setLinkData(String linkData) {
        this.linkData = linkData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkObject)) return false;
        if (!super.equals(o)) return false;

        LinkObject that = (LinkObject) o;

        return linkData != null ? linkData.equals(that.linkData) : that.linkData == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (linkData != null ? linkData.hashCode() : 0);
        return result;
    }
}
