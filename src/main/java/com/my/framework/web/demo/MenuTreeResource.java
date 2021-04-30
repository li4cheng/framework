package com.my.framework.web.demo;

import com.my.framework.domain.SysMenu;
import com.my.framework.domain.enumeration.MenuStatusType;
import com.my.framework.repository.SysMenuRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("/api/tree")
@Api(tags = "树形测试")
public class MenuTreeResource {

    private static final Logger log = LoggerFactory.getLogger(MenuTreeResource.class);

    private final SysMenuRepository sysMenuRepository;

    public MenuTreeResource(SysMenuRepository sysMenuRepository) {
        this.sysMenuRepository = sysMenuRepository;
    }

    @GetMapping("/tree")
    @ApiOperation(value = "树测试")
    public ResponseEntity<List<SysMenu>> tree() {
        MenuTree admin_market =
            new MenuTree().fullName("商城后台").href(null).siblingSort(1).childrenMenuTree(
                new MenuTree().fullName("会员管理").href(null).siblingSort(1).childrenMenuTree(
                    new MenuTree().fullName("门店管理").href(null).siblingSort(1),
                    new MenuTree().fullName("车主管理").href(null).siblingSort(2)),
                new MenuTree().fullName("商城配置").href(null).siblingSort(2).childrenMenuTree(
                    new MenuTree().fullName("基础配置").href(null).siblingSort(1),
                    new MenuTree().fullName("公告管理").href(null).siblingSort(2)),
                new MenuTree().fullName("财务管理").href(null).siblingSort(3).childrenMenuTree(
                    new MenuTree().fullName("应收管理").href(null).siblingSort(1),
                    new MenuTree().fullName("应付管理").href(null).siblingSort(2)),
                new MenuTree().fullName("系统管理").href(null).siblingSort(4).childrenMenuTree(
                    new MenuTree().fullName("数据管理").href(null).siblingSort(1),
                    new MenuTree().fullName("系统日志").href(null).siblingSort(2),
                    new MenuTree().fullName("机构管理").href(null).siblingSort(3),
                    new MenuTree().fullName("人员管理").href(null).siblingSort(4),
                    new MenuTree().fullName("角色管理").href(null).siblingSort(5)));

        return ResponseEntity.ok(tree2Menu(admin_market, null, 1, null));
    }

    /**
     * 保存菜单列表
     */
    private List<SysMenu> tree2Menu(MenuTree menuTree, String parentIds, int level, String fullName) {
        SysMenu sysMenu = new SysMenu()
            .siblingSort(menuTree.getSiblingSort())
            .fullName(fullName == null ? menuTree.getFullName() : fullName + "/" + menuTree.getFullName())
            .href(menuTree.getHref())
            .level(level)
            .leaf(menuTree.getChildrenMenuTree() == null)
            .status(MenuStatusType.NORMAL)
            .deleted(false)
            .visible(true)
            .parentIds(parentIds);
        Optional<SysMenu> sysMenuOpt = sysMenuRepository.findByFullNameAndStatus(sysMenu.getFullName(), MenuStatusType.NORMAL);
        SysMenu menu = sysMenuOpt.orElseGet(() -> sysMenuRepository.save(sysMenu));
        List<SysMenu> menus = menuTree.getChildrenMenuTree() == null ? new ArrayList<>() : menuTree.getChildrenMenuTree().stream()
            .map(tree -> this.tree2Menu(tree, listAdd(parentIds, String.valueOf(menu.getId())), level + 1, menu.getFullName()))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        menus.add(menu);
        return menus.stream()
            .sorted(Comparator.comparingInt(SysMenu::getSiblingSort))
            .collect(Collectors.toList());
    }

    private String listAdd(String parentIds, String parentId) {
        return parentIds == null ? parentId : String.join(",", Arrays.asList(parentIds, parentId));
    }

    private static class MenuTree {

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
}
