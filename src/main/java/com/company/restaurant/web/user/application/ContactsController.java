package com.company.restaurant.web.user.application;

import com.company.restaurant.web.user.application.proto.UserApplicationController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yevhen on 06.08.2016.
 */
@Controller
public class ContactsController extends UserApplicationController {
    private static final String RESTAURANT_CONTACTS_VIEW_NAME = "/contacts-page";
    private static final String RESTAURANT_TRANSPORT_IMAGE_MAP_VAR_NAME = "restaurantTransportMapImage";
    private static final String CONTACTS_REQUEST_MAPPING_VALUE = "/contacts";

    @RequestMapping(value = CONTACTS_REQUEST_MAPPING_VALUE, method = RequestMethod.GET)
    public ModelAndView contactsPage() {
        initData();

        modelAndView.addObject(RESTAURANT_TRANSPORT_IMAGE_MAP_VAR_NAME,
                base64EncodeToString(commonDataService.getTransportMapImage()));
        modelAndView.setViewName(RESTAURANT_CONTACTS_VIEW_NAME);

        return modelAndView;
    }

}
