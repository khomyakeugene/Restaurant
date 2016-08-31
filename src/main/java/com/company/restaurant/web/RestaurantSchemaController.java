package com.company.restaurant.web;

import com.company.restaurant.web.proto.CommonDataController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 06.08.2016.
 */
@Controller
public class RestaurantSchemaController extends CommonDataController {
    private static final String RESTAURANT_SCHEMA_PAGE_VIEW_NAME = "/restaurant-schema-page";
    private static final String RESTAURANT_RESTAURANT_SCHEMA_IMAGE_MAP_VAR_NAME = "restaurantRestaurantSchema";

    @RequestMapping(value = "/restaurant-schema", method = RequestMethod.GET)
    public ModelAndView restaurantSchemaPage() {
        initData();

        modelAndView.addObject(RESTAURANT_RESTAURANT_SCHEMA_IMAGE_MAP_VAR_NAME,
                base64EncodeToString(commonDataService.getRestaurantSchemeImage()));
        modelAndView.setViewName(RESTAURANT_SCHEMA_PAGE_VIEW_NAME);

        return modelAndView;
    }
}
