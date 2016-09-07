package com.company.restaurant.web.admin.application.common;

import com.company.restaurant.web.common.CommonDataController;

/**
 * Created by Yevhen on 03.09.2016.
 */
public class AdminApplicationController extends CommonDataController {
    private static final String SUBMIT_BUTTON_SAVE_VALUE = "save";
    private static final String SUBMIT_BUTTON_DELETE_VALUE = "delete";

    protected static final String SUBMIT_BUTTON_VAR_NAME = "submitButtonValue";

    static {
        ERROR_PAGE_VIEW_NAME = "admin-application/error";
    }

    protected boolean isSubmitSave(String submitButtonValue) {
        return submitButtonValue.toLowerCase().equals(SUBMIT_BUTTON_SAVE_VALUE.toLowerCase());
    }

    protected boolean isSubmitDelete(String submitButtonValue) {
        return submitButtonValue.toLowerCase().equals(SUBMIT_BUTTON_DELETE_VALUE.toLowerCase());
    }

    @Override
    protected void initModelAndViewData() {
        super.initModelAndViewData();


    }
}
