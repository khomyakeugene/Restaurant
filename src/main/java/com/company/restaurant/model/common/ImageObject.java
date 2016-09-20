package com.company.restaurant.model.common;

import java.util.Base64;

/**
 * Created by Yevhen on 06.08.2016.
 */
public class ImageObject extends SimpleObject {
    protected String base64EncodeToString(byte[] data) {
        return (data == null) ? null : Base64.getEncoder().encodeToString(data);
    }
}
