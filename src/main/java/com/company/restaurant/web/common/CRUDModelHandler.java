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
    private String currentObjectVarName;
    private Class currentObjectClass;

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

        addCurrentObject(currentObject);
    }

    private Class<?> getCurrentObjectClass() {
        if (currentObjectClass == null) {
            currentObjectClass = (currentObject == null) ? getEntityClass() : currentObject.getClass();
            setEntityClass(currentObjectClass);
        }

        return currentObjectClass;
    }

    private String getCurrentObjectVarName() {
        if (currentObjectVarName == null) {
            currentObjectVarName = Util.decapitalize(getCurrentObjectClass().getSimpleName());
        }

        return currentObjectVarName;
    }

    private void addCurrentObject(T currentObject) {
        modelAndView.addObject(getCurrentObjectVarName(), currentObject);
    }

    private void newCurrentObject() {
        addCurrentObject(newObject());
    }

    public void clearCurrentObject() {
        if (currentObject != null) {
            newCurrentObject();
        }
    }
}
