package com.company.restaurant.web.admin.application.common;

import com.company.restaurant.model.common.SimpleObject;
import com.company.restaurant.web.common.CRUDModelHandler;

/**
 * Created by Yevhen on 08.09.2016.
 */

public class AdminCRUDController<T> extends AdminApplicationController {
    private static final String CURRENT_OBJECT_ID_VAR_NAME = "currentObjectId";
    protected static final String FILE_PAR_NAME = "file";

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

        modelAndView.addObject(CURRENT_OBJECT_ID_VAR_NAME,
                ((object != null) && (object instanceof SimpleObject)) ? ((SimpleObject) object).getId() : -1);

       return object;
    }

    protected void nullCurrentObject() {
        setCurrentObject(null);
    }
}
