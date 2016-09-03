package com.company.restaurant.web.rest;

import com.company.restaurant.model.Menu;
import com.company.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Yevhen on 17.08.2016.
 */

@RestController
public class MenuRestController {
    private MenuService menuService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value="/menus", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getMenuList() {
        return menuService.findAllMenuNames();
    }

    @RequestMapping(value="/menus/menu_id/{menuId}", method = RequestMethod.GET)
    @ResponseBody
    public Menu getMenuById(@PathVariable int menuId) {
        return menuService.findMenuById(menuId);
    }

    @RequestMapping(value="/menus/menu_name/{menuName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Menu> getMenuByName(@PathVariable String menuName) {
        return menuService.findMenusByNameFragment(menuName);
    }
}
