package com.company.restaurant.web.user.application;

import com.company.restaurant.web.user.application.common.UserApplicationController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 06.08.2016.
 */

@Controller
public class UserRestaurantSchemaController extends UserApplicationController {
    private static final String RESTAURANT_SCHEMA_PAGE_VIEW_NAME = "user-application/restaurant-schema-page";
    private static final String RESTAURANT_RESTAURANT_SCHEMA_IMAGE_MAP_VAR_NAME = "restaurantRestaurantSchema";
    private static final String RESTAURANT_SCHEMA_REQUEST_MAPPING_VALUE = "/user-restaurant-schema";

    @RequestMapping(value = RESTAURANT_SCHEMA_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView restaurantSchemaPage() {
        modelAndView.addObject(RESTAURANT_RESTAURANT_SCHEMA_IMAGE_MAP_VAR_NAME,
                base64EncodeToString(commonDataService.getRestaurantSchemeImage()));
        modelAndView.setViewName(RESTAURANT_SCHEMA_PAGE_VIEW_NAME);

        return modelAndView;
    }
}
