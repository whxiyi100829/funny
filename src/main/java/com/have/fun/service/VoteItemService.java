package com.have.fun.service;

import com.have.fun.entity.VoteItem;

import java.util.List;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/24 Time: 01:19
 * </pre>
 */
public interface VoteItemService {
    List<VoteItem> getVoteItems();

    Integer getRecordsById(Integer id);

    void increaseRecords(Integer chkId);
}
