package com.have.fun.entity;

import com.have.fun.utils.FunnyConstants;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/23 Time: 23:24
 * 投票选项
 * </pre>
 */
public class VoteItem implements Comparable<VoteItem> {

    private Integer id;
    private String  name;
    private String  funnyMsg;
    private String  link;
    private Integer records;

    public VoteItem(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public VoteItem(Integer id, String name, String funnyMsg, String link, Integer records) {
        this.id = id;
        this.name = name;
        this.funnyMsg = funnyMsg;
        this.link = link;
        this.records = records;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunnyMsg() {
        return funnyMsg;
    }

    public void setFunnyMsg(String funnyMsg) {
        this.funnyMsg = funnyMsg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    @Override
    public int compareTo(VoteItem o) {
        if (o == null) {
            return 1;
        }
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return FunnyConstants.JOINER.join(new Object[] { this.getId(), this.getName(), this.getFunnyMsg(),
                this.getLink(), this.records });
    }
}
