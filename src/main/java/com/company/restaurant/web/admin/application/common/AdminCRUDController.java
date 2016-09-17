package com.company.restaurant.web.admin.application.common;

import com.company.restaurant.web.common.CRUDModelHandler;

/**
 * Created by Yevhen on 08.09.2016.
 */
public class AdminCRUDController<T> extends AdminApplicationController {
    private CRUDModelHandler<T> crudModelHandler;

    @Override
    protected void initModelAndViewData() {
        super.initModelAndViewData();

        crudModelHandler = new CRUDModelHandler<>(modelAndView);
    }

    protected void clearCurrentObject() {
        crudModelHandler.clearCurrentObject();
    }

    protected T getCurrentObject() {
        return crudModelHandler.getCurrentObject();
    }

    protected T setCurrentObject(T object) {
        crudModelHandler.setCurrentObject(object);

        return object;
    }

    protected void nullCurrentObject() {
        setCurrentObject(null);
    }
}
