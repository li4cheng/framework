package com.my.framework.customConfig.tree;

import com.my.framework.customConfig.error.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TreeService {

    private final Map<Object, List<BaseTree>> treeMap = new HashMap<>();

    /**
     * 将list转化为tree（只限BaseTree的子类）
     *
     * @param list               待转换的list对象
     * @param clazz              baseTree中key的类型变量
     * @param <T>baseTree中key的类型
     * @return 转换后的treeList
     */
    public <T> List<T> collectionToTree(Collection<? extends BaseTree> list, Class<T> clazz) {
        for (BaseTree baseTree : list) {
            List<BaseTree> value = treeMap.getOrDefault(baseTree.getParentKey(), new ArrayList<>());
            value.add(baseTree);
            treeMap.put(baseTree.getParentKey(), value);
        }
        for (BaseTree baseTree : list) {
            baseTree.setChildren(treeMap.get(baseTree.getKey()));
        }
        List<T> result = list.stream()
            .filter(baseTree -> baseTree.getParentKey() == null)
            .peek(baseTree -> setLevelAndLeaf(baseTree, 1))
            .map(baseTree -> baseTree2T(baseTree, clazz))
            .collect(Collectors.toList());

        return result;
    }

    /**
     * 为baseTree添加level和leaf数据
     *
     * @param baseTree 添加数据的baseTree对象
     * @param level    baseTree的level数据
     */
    private void setLevelAndLeaf(BaseTree baseTree, int level) {
        baseTree.setLevel(level);
        if (baseTree.getChildren() != null) {
            List<BaseTree> childrenTree = baseTree.getChildren();
            childrenTree.forEach(child -> setLevelAndLeaf(child, level + 1));
        } else {
            baseTree.setLeaf(true);
        }
    }

    /**
     * 将baseTree转化为其子类T
     *
     * @param baseTree 转换前的baseTree对象
     * @param clazz    baseTree中key的类型变量
     * @param <T>      baseTree中key的类型
     * @return 转换后的T
     */
    private <T> T baseTree2T(BaseTree baseTree, Class<T> clazz) {
        T t;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(baseTree, t);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new CustomException("转换树失败");
        }
        return t;
    }
}
