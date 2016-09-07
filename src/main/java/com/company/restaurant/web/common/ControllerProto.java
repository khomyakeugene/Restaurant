package com.company.restaurant.web.common;

import com.company.restaurant.dao.common.ErrorHandlingService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

/**
 * Created by Yevhen on 05.08.2016.
 */
public class ControllerProto {
    protected static final String REDIRECT_PREFIX = "redirect:";
    protected static String ERROR_PAGE_VIEW_NAME = "error";

    private static final String ERROR_MESSAGE_VAR_NAME = "errorMessage";

    protected ModelAndView modelAndView = new ModelAndView();

    private String lastNavigationViewName;

    protected void setLastNavigationViewName(String lastNavigationViewName) {
        this.lastNavigationViewName = lastNavigationViewName;
    }

    @PostConstruct
    protected void initModelAndViewData() {
        // To be override in child classes ....

    }

    protected String base64EncodeToString(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    protected void storeLastNavigationViewName() {
        // To possible further return from <ExceptionHandler>
        setLastNavigationViewName(modelAndView.getViewName());
    }

    protected String getErrorViewName() {
        // To possible return to <lastNavigationViewName> when error occurred
        return (lastNavigationViewName == null) ? ERROR_PAGE_VIEW_NAME : lastNavigationViewName;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        modelAndView.addObject(ERROR_MESSAGE_VAR_NAME, ErrorHandlingService.getErrorMessage(ex));
        modelAndView.setViewName(getErrorViewName());

        return modelAndView;
    }

}
