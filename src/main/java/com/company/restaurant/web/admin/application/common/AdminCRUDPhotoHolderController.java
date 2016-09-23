package com.company.restaurant.web.admin.application.common;

import com.company.restaurant.model.common.PhotoHolderObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Yevhen on 23.09.2016.
 */
public class AdminCRUDPhotoHolderController<T extends PhotoHolderObject> extends AdminCRUDController<T> {
    protected ModelAndView uploadPhoto(MultipartFile file) {
        if (file != null) {
            try {
                getCurrentObject().setPhoto(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return modelAndView;
    }
}
