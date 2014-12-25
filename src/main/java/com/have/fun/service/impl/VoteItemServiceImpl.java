package com.have.fun.service.impl;

import com.have.fun.entity.VoteItem;
import com.have.fun.service.VoteItemService;
import com.have.fun.utils.ResourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/24 Time: 01:21
 * </pre>
 */
@Service("voteItemService")
public class VoteItemServiceImpl implements VoteItemService{

    @Autowired
    ResourceContext resourceContext;

    public void setResourceContext(ResourceContext resourceContext) {
        this.resourceContext = resourceContext;
    }

    @Override
    public List<VoteItem> getVoteItems() {
        return resourceContext.getVoteItems();
    }

    @Override
    public Integer getRecordsById(Integer id) {
        return resourceContext.getRecordsById(id);
    }

    @Override
    public void increaseRecords(Integer chkId) {
        resourceContext.increase(chkId);
    }

    public static void main(String[] args) {
        VoteItemService voteItemService = new VoteItemServiceImpl();
        System.out.println(voteItemService.getVoteItems());
    }
}
