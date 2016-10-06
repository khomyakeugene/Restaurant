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

    @PostConstruct
    protected void initModelAndViewData() {
        // To be overridden in child classes ....
    }

    protected void clearErrorMessage()  {
        modelAndView.addObject(ERROR_MESSAGE_VAR_NAME, "");
    }

    protected String base64EncodeToString(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private String getErrorViewName() {
        String currentViewName = modelAndView.getViewName();

        return (currentViewName == null) ? ERROR_PAGE_VIEW_NAME : currentViewName;
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        modelAndView.addObject(ERROR_MESSAGE_VAR_NAME, ErrorHandlingService.getErrorMessage(ex));
        modelAndView.setViewName(getErrorViewName());

        return modelAndView;
    }
}
