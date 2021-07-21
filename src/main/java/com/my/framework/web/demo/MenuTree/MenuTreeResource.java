package com.my.framework.web.demo.MenuTree;

import com.my.framework.customConfig.error.CustomException;
import com.my.framework.domain.SysMenu;
import com.my.framework.domain.enumeration.MenuStatusType;
import com.my.framework.repository.SysMenuRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("/api/tree")
@Api(tags = "树形菜单(抽象)")
public class MenuTreeResource {

    private static final Logger log = LoggerFactory.getLogger(MenuTreeResource.class);

    private final SysMenuRepository sysMenuRepository;

    public MenuTreeResource(SysMenuRepository sysMenuRepository) {
        this.sysMenuRepository = sysMenuRepository;
    }

    @PostMapping("/save-tree")
    @ApiOperation(value = "保存树")
    public ResponseEntity<List<SysMenu>> saveTree() {
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

    @GetMapping("/childrenIds")
    @ApiOperation(value = "获取子菜单id")
    public ResponseEntity<List<Long>> getChildrenIds(@RequestParam String menuName) {
        SysMenu sysMenu = sysMenuRepository.findByFullNameAndStatus(menuName, MenuStatusType.NORMAL)
            .orElseThrow(() -> new CustomException("不存在该菜单"));
        return ResponseEntity.ok(getChildrenMenuId(sysMenu));
    }

    /**
     * 获取下级菜单id列表
     *
     * @param sysMenu 菜单
     * @return 下级菜单id列表（包含自身id）
     */
    private List<Long> getChildrenMenuId(SysMenu sysMenu) {
        List<Long> ids = sysMenuRepository.findAllByParentIdsAndStatus(
            sysMenu.getParentIds() == null
                ? String.valueOf(sysMenu.getId())
                : String.join(",", Arrays.asList(sysMenu.getParentIds(), String.valueOf(sysMenu.getId()))), MenuStatusType.NORMAL)
            .stream().map(this::getChildrenMenuId)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        ids.add(sysMenu.getId());

        return ids.stream().distinct().sorted().collect(Collectors.toList());
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
}
