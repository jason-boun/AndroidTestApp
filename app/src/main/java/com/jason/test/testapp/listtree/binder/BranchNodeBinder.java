package com.jason.test.testapp.listtree.binder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.test.testapp.R;
import com.jason.test.testapp.listtree.bean.ListBranch;

import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewBinder;

/**
 * Created by JiaBo on 2017/10/16.
 * 折叠列表分支节点-数据视图绑定类
 */

public class BranchNodeBinder extends TreeViewBinder<BranchNodeBinder.ViewHolder> {

    @Override
    public ViewHolder provideViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(ViewHolder viewHolder, int i, TreeNode treeNode) {
        viewHolder.ivArrow.setRotation(0);
        viewHolder.ivArrow.setImageResource(R.drawable.icon_arrow_right);
        int rotateDegree = treeNode.isExpand() ? 90 : 0;
        viewHolder.ivArrow.setRotation(rotateDegree);
        ListBranch branchNode = (ListBranch) treeNode.getContent();
        viewHolder.tvName.setText(branchNode.getBranchName());
        if (treeNode.isLeaf()) {
            viewHolder.ivArrow.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.ivArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_list_tree_branch;
    }

    public class ViewHolder extends TreeViewBinder.ViewHolder {

        private ImageView ivArrow;
        private TextView tvName;

        public ViewHolder(View rootView) {
            super(rootView);
            this.ivArrow = (ImageView) rootView.findViewById(R.id.iv_arrow);
            this.tvName = (TextView) rootView.findViewById(R.id.tv_name);
        }

        public ImageView getIvArrow() {
            return ivArrow;
        }

        public TextView getTvName() {
            return tvName;
        }
    }
}
