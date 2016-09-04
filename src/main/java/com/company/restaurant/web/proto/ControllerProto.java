package com.company.restaurant.web.proto;

import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

/**
 * Created by Yevhen on 05.08.2016.
 */
public class ControllerProto {
    protected static final String REDIRECT_PREFIX = "redirect:";
    protected ModelAndView modelAndView = new ModelAndView();

    protected String base64EncodeToString(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
