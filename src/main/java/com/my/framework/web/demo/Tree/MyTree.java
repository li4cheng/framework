package com.my.framework.web.demo.Tree;

import com.my.framework.customConfig.tree.BaseTree;

/**
 * @param <T> 主键类型
 */
public class  MyTree<T> extends BaseTree<T> {

    private String note;

    MyTree<T> note(String note) {
        this.note = note;
        return this;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
