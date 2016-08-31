package com.company.restaurant.model.proto;

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
