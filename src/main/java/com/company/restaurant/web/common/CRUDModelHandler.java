package com.company.restaurant.web.common;

import com.company.util.common.GenericHolder;
import com.company.util.common.Util;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 08.09.2016.
 */
public class CRUDModelHandler<T> extends GenericHolder<T> {
    protected ModelAndView modelAndView;
    private T currentObject;

    public CRUDModelHandler(ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }

    public void setModelAndView(ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }

    public T getCurrentObject() {
        return currentObject;
    }

    public void setCurrentObject(T currentObject) {
        this.currentObject = currentObject;

        addCurrentObjectAttribute(currentObject);
    }

    protected String getCurrentObjectVarName() {
        return Util.decapitalize(getEntitySimpleName());
    }

    protected void addCurrentObjectAttribute(T currentObject) {
        modelAndView.addObject(getCurrentObjectVarName(), currentObject);
    }
}
