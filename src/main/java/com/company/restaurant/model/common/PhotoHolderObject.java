package com.company.restaurant.model.common;

/**
 * Created by Yevhen on 06.08.2016.
 */
public class PhotoHolderObject extends ImageObject {
    private byte[] photo;

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getBase64EncodePhoto() {
        return base64EncodeToString(getPhoto());
    }
}
