package com.my.framework.web.demo.Tree;

import com.my.framework.customConfig.tree.BaseTree;
import com.my.framework.customConfig.tree.TreeService;
import io.swagger.annotations.Api;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/tree")
@Api(tags = "list转换树")
public class TreeResource {

    private final TreeService treeService;

    public TreeResource(TreeService treeService) {
        this.treeService = treeService;
    }

    @GetMapping("/demoNew")
    public List<MyTree> tree() {
        List<BaseTree<Long>> list = new ArrayList<>();
        list.add(new MyTree().note("爷").key(1L).parentKey(null));
        list.add(new MyTree().note("父").key(21L).parentKey(1L));
        list.add(new MyTree().note("父.子1").key(211L).parentKey(21L));
        list.add(new MyTree().note("父.子2").key(212L).parentKey(21L));
        list.add(new MyTree().note("父.子3").key(213L).parentKey(21L));
        list.add(new MyTree().note("母").key(22L).parentKey(1L));
        list.add(new MyTree().note("母.子").key(221L).parentKey(22L));
        list.add(new MyTree().note("爷2").key(2L).parentKey(null));
        return treeService.collectionToTree(list, MyTree.class);
    }
}
