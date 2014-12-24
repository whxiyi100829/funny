package com.have.fun.controller;

import com.have.fun.entity.VoteItem;
import com.have.fun.service.VoteItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/22 Time: 22:30
 * </pre>
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    VoteItemService voteItemService;


    @RequestMapping(value = "index", method = { RequestMethod.POST,RequestMethod.GET })
    public String index(HttpServletRequest request) {
        LOGGER.info(String.format("user logout system"));
        //        request.getSession()
        return "index";
    }

    @RequestMapping(value = "show", method = {RequestMethod.GET})
    public String show(HttpServletRequest request, ModelMap modelMap) {
        List<VoteItem> voteItems = voteItemService.getVoteItems();
        modelMap.put("items", voteItems);
        return "show";

    }

    @RequestMapping(value = "test", method = {RequestMethod.GET})
    public String test(HttpServletRequest request) {

        return "test";

    }
}
