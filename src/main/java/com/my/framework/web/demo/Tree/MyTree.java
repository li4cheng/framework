package com.my.framework.web.demo.Tree;

import com.my.framework.customConfig.tree.BaseTree;

public class MyTree extends BaseTree<Long> {

    private String note;

    MyTree note(String note) {
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
