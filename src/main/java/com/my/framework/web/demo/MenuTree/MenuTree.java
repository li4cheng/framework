package com.my.framework.web.demo.MenuTree;

import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

public class MenuTree {

    @ApiModelProperty(value = "菜单编码")
    private String code;

    @ApiModelProperty(value = "同级排序")
    private Integer siblingSort;

    @ApiModelProperty(value = "简称")
    private String shortName;

    @ApiModelProperty(value = "全称")
    private String fullName;

    @ApiModelProperty(value = "链接")
    private String href;

    @ApiModelProperty(value = "所有父级id")
    private List<Long> parentIds;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "子菜单树")
    private List<MenuTree> childrenMenuTree;

    public String getCode() {
        return code;
    }

    public MenuTree code(String code) {
        this.code = code;
        return this;
    }

    public Integer getSiblingSort() {
        return siblingSort;
    }

    public MenuTree siblingSort(Integer siblingSort) {
        this.siblingSort = siblingSort;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public MenuTree shortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public MenuTree fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getHref() {
        return href;
    }

    public MenuTree href(String href) {
        this.href = href;
        return this;
    }

    public List<Long> getParentIds() {
        return parentIds;
    }

    public String getIcon() {
        return icon;
    }

    public MenuTree icon(String icon) {
        this.icon = icon;
        return this;
    }

    public List<MenuTree> getChildrenMenuTree() {
        return childrenMenuTree;
    }

    public MenuTree childrenMenuTree(MenuTree... childrenMenuTree) {
        this.childrenMenuTree = Arrays.asList(childrenMenuTree);
        return this;
    }
}
