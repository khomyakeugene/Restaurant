package com.company.restaurant.web.common;

import com.company.restaurant.service.CommonDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Yevhen on 06.08.2016.
 */
public class CommonDataController extends ControllerProto {
    private static final String RESTAURANT_NAME_VAR_NAME = "restaurantName";
    private static final String RESTAURANT_ADDRESS_VAR_NAME = "restaurantAddress";
    private static final String RESTAURANT_E_MAIL_VAR_NAME = "restaurantEMail";
    private static final String RESTAURANT_PHONE_NUMBERS_VAR_NAME = "restaurantPhoneNumbers";
    private static final String RESTAURANT_EMBLEM_IMAGE_VAR_NAME = "restaurantEmblemImage";

    protected CommonDataService commonDataService;

    @Autowired
    public void setCommonDataService(CommonDataService commonDataService) {
        this.commonDataService = commonDataService;
    }

    private void initCommonModelAndViewData() {
        modelAndView.addObject(RESTAURANT_NAME_VAR_NAME, commonDataService.getRestaurantName());
        modelAndView.addObject(RESTAURANT_ADDRESS_VAR_NAME, commonDataService.getRestaurantAddress());
        modelAndView.addObject(RESTAURANT_E_MAIL_VAR_NAME, commonDataService.getRestaurantEMail());
        modelAndView.addObject(RESTAURANT_PHONE_NUMBERS_VAR_NAME, commonDataService.getPhoneNumbers());
        modelAndView.addObject(RESTAURANT_EMBLEM_IMAGE_VAR_NAME,
                base64EncodeToString(commonDataService.getEmblemImage()));
    }

    @Override
    protected void initModelAndViewData() {
        super.initModelAndViewData();

        initCommonModelAndViewData();
    }
}
