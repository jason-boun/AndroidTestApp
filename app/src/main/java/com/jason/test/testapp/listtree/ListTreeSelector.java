package com.jason.test.testapp.listtree;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.jason.test.testapp.R;
import com.jason.test.testapp.base.BaseApplication;
import com.jason.test.testapp.listtree.bean.ListBranch;
import com.jason.test.testapp.listtree.bean.ListItem;
import com.jason.test.testapp.listtree.binder.BranchNodeBinder;
import com.jason.test.testapp.listtree.binder.ItemNodeBinder;
import com.jason.test.testapp.utils.ViewUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tellh.com.recyclertreeview_lib.TreeNode;
import tellh.com.recyclertreeview_lib.TreeViewAdapter;

/**
 * Created by JiaBo on 2017/10/16.
 */

public class ListTreeSelector {


    public static List<TreeNode> buildActionData() {
        TreeNode<ListBranch> actionChoiceList = new TreeNode<>(new ListBranch("可选动作"));
        actionChoiceList.addChild(new TreeNode<>(new ListItem("action1")));
        actionChoiceList.addChild(new TreeNode<>(new ListItem("action2")));
        actionChoiceList.addChild(new TreeNode<>(new ListItem("action3")));
        actionChoiceList.addChild(new TreeNode<>(new ListItem("action4")));
        actionChoiceList.addChild(new TreeNode<>(new ListItem("action5")));

        TreeNode<ListBranch> actionSelectedList = new TreeNode<>(new ListBranch("已选动作"));
        actionSelectedList.addChild(new TreeNode<>(new ListItem("selected action1")));
        actionSelectedList.addChild(new TreeNode<>(new ListItem("selected action2")));
        actionSelectedList.addChild(new TreeNode<>(new ListItem("selected action3")));
        actionSelectedList.addChild(new TreeNode<>(new ListItem("selected action4")));
        actionSelectedList.addChild(new TreeNode<>(new ListItem("selected action5")));

        List<TreeNode> rootList = new ArrayList<>();
        rootList.add(actionChoiceList);
        rootList.add(actionSelectedList);
        return rootList;
    }

    public static void buildActionWindow(View anchor, final List<TreeNode> data, String title) {
        if (anchor == null || anchor.getContext() == null) {
            return;
        }
        final Context context = anchor.getContext();
        View rootView = View.inflate(context, R.layout.window_simple_recyclerview, null);
        final PopupWindow window = new PopupWindow(context);
        window.setWidth(ViewUtil.getHeight(BaseApplication.application) / 3);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setFocusable(true);
        window.setBackgroundDrawable(new ColorDrawable(0xffffff));

        window.setContentView(rootView);
        window.showAsDropDown(anchor, 0, 3, Gravity.CENTER | Gravity.BOTTOM);


        TextView titleView = (TextView) rootView.findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(title)) {
            titleView.setText(title);
            titleView.setVisibility(View.VISIBLE);
        } else {
            titleView.setVisibility(View.GONE);
        }
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        TreeViewAdapter adapter = new TreeViewAdapter(data, Arrays.asList(new ItemNodeBinder(), new BranchNodeBinder()));

        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    onToggle(!node.isExpand(), holder);
                } else {
                    Object obj = node.getContent();
                    if (!(obj instanceof ListItem)) {
                        return false;
                    }
                    ListItem f = (ListItem) obj;
//                    if (window != null) {
//                        window.dismiss();
//                    }
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                BranchNodeBinder.ViewHolder dirViewHolder = (BranchNodeBinder.ViewHolder) holder;
                final ImageView ivArrow = dirViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 90 : -90;
                ivArrow.animate().rotationBy(rotateDegree).start();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
