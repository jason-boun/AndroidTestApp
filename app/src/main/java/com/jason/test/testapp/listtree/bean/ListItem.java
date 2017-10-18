package com.jason.test.testapp.listtree.bean;


import com.jason.test.testapp.R;

import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * Created by JiaBo on 2017/10/16.
 * 折叠列表叶子节点
 */

public class ListItem implements LayoutItemType {

    private String itemName;

    public ListItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_list_tree_item;
    }
}
