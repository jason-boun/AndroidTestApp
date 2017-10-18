package com.jason.test.testapp.listtree.bean;

import com.jason.test.testapp.R;

import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by JiaBo on 2017/10/16.
 * 折叠列表分支节点
 */

public class ListBranch implements LayoutItemType {

    private String branchName;

    public ListBranch(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchName() {
        return branchName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_list_tree_branch;
    }
}
