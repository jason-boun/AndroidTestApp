package com.jason.test.testapp.listtree.binder;

import android.view.View;
import android.widget.TextView;


import com.jason.test.testapp.R;
import com.jason.test.testapp.listtree.bean.ListItem;

import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Created by JiaBo on 2017/10/16.
 * 折叠列表叶子节点-数据视图绑定类
 */

public class ItemNodeBinder extends TreeViewBinder<ItemNodeBinder.ViewHolder> {


    @Override
    public ViewHolder provideViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(ViewHolder viewHolder, int i, TreeNode treeNode) {
        ListItem fileNode = (ListItem) treeNode.getContent();
        viewHolder.tvName.setText(fileNode.getItemName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_list_tree_item;
    }

    public class ViewHolder extends TreeViewBinder.ViewHolder {

        public TextView tvName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }
    }
}
