package com.epam.naumovich.rest_service.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Dzmitry_Naumovich on 3/9/2017.
 */

@RestController
@RequestMapping("/main")
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());
    private static String SUCCESS_PREFIX = "Success: ";

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String get(HttpServletRequest request, @PathVariable Integer id) {
        LOGGER.debug("GET Method called in MainController, id = " + id);
        return SUCCESS_PREFIX + request.getRequestURI();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String add(HttpServletRequest request) {
        LOGGER.debug("Add Method called in MainController");
        return SUCCESS_PREFIX + request.getRequestURL();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public String delete(HttpServletRequest request, @PathVariable Integer id) {
        LOGGER.debug("Delete Method called in MainController, id = " + id);
        return SUCCESS_PREFIX + request.getRequestURI();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public String update(HttpServletRequest request, @PathVariable Integer id) {
        LOGGER.debug("Update Method called in MainController, id = " + id);
        return SUCCESS_PREFIX + request.getRequestURI();
    }
}
