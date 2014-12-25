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
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "show", method = {RequestMethod.GET, RequestMethod.POST})
    public String show(HttpServletRequest request, ModelMap modelMap, @RequestParam (value = "voteChecks", required = false) Integer[] voteChecks
                       ) {
        if (voteChecks != null && voteChecks.length > 0) {
            for (Integer chkId : voteChecks) {
                voteItemService.increaseRecords(chkId);
            }
            List<VoteItem> voteItems = voteItemService.getVoteItems();
            int totalCount = 0;
            for (VoteItem voteItem : voteItems) {
                int records = voteItemService.getRecordsById(voteItem.getId());
                voteItem.setRecords(records);
                totalCount += records;
            }
            modelMap.put("items", voteItems);
            modelMap.put("totalCount", totalCount);
            modelMap.put("return", true);
        } else {
            List<VoteItem> voteItems = voteItemService.getVoteItems();
            int totalCount = 0;
            for (VoteItem voteItem : voteItems) {
                totalCount += voteItem.getRecords();
            }
            modelMap.put("items", voteItems);
            modelMap.put("totalCount", totalCount);
            modelMap.put("return", false);
        }
        return "show";
    }

    @RequestMapping(value = "test", method = {RequestMethod.GET})
    public String test(HttpServletRequest request) {

        return "test";

    }
}
