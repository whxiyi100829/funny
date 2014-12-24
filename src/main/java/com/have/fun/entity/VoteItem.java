package com.have.fun.entity;

/**
 * <pre>
 * User: hzwangxx Date: 14/12/23 Time: 23:24
 * 投票选项
 * </pre>
 */
public class VoteItem implements Comparable<VoteItem>{
    private Integer id;
    private String name;

    public VoteItem(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int compareTo(VoteItem o) {
        if (o == null) {
            return 1;
        }
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "com.have.fun.entity.VoteItem{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
