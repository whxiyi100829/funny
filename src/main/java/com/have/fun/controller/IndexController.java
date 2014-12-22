package com.have.fun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/22 Time: 22:30
 * </pre>
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "index", method = { RequestMethod.POST,RequestMethod.GET })
    public String index(HttpServletRequest request) {
        LOGGER.info(String.format("user logout system"));
        //        request.getSession()
        return "index";
    }
}
