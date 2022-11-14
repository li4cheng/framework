package com.my.framework.customConfig.tree;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@ApiModel(description = "自定义树")
public class BaseTree<T extends Serializable> {

    @NotEmpty
    private T key;

    private T parentKey;

    private boolean isLeaf;

    private Integer level;

    @NotEmpty
    private List<BaseTree<T>> children;

    public T getKey() {
        return key;
    }

    public BaseTree<T> key(T key) {
        this.key = key;
        return this;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public T getParentKey() {
        return parentKey;
    }

    public BaseTree<T> parentKey(T parentKey) {
        this.parentKey = parentKey;
        return this;
    }

    public void setParentKey(T parentKey) {
        this.parentKey = parentKey;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<BaseTree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<BaseTree<T>> children) {
        this.children = children;
    }
}
