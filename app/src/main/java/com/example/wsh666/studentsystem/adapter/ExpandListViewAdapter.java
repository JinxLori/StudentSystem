package com.example.wsh666.studentsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wsh666.expandlistview.R;
import com.example.wsh666.studentsystem.bean.UserBean;

import java.util.List;
import java.util.Map;

/**
 * Created bywsh666 on 2018/8/23 15:37
 　 へ　　　　　  ／|
　　/＼7　　　 ∠＿/
　 /　│　　 ／　／
　│　Z ＿,＜　／　　 /`ヽ
　│　　　　　ヽ　　 /　　〉
　 Y　　　　　`　   /　　/
　ｲ●　､　●　　⊂⊃〈　　/
　()　 へ　　　　|　＼〈
　　>ｰ ､_　 ィ　 │ ／／
　 / へ　　 /　ﾉ＜| ＼＼
　 ヽ_ﾉ　　(_／　 │／／
　　7　　　　　　　|／
　　＞―r￣￣`ｰ―＿
 */

public class ExpandListViewAdapter extends BaseExpandableListAdapter {

    private LayoutInflater layoutInflater;
    private List<String> groups;
    private Map<String, List<UserBean>> childs;

    public ExpandListViewAdapter(Context context, List<String> groups, Map<String, List<UserBean>> childs) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groups = groups;
        this.childs = childs;
    }


    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int i) {
        String key = groups.get(i);
        return childs.get(key).size();
    }

    @Override
    public Object getGroup(int i) {
        return groups.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        /*String group=groups.get(i);
        List<UserBean> child=childs.get(group);
        UserBean c=child.get(i1);
        return c;*/
        return childs.get(groups.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view == null) {
            groupViewHolder = new GroupViewHolder();
            view = layoutInflater.inflate(R.layout.group_item_layout, null);
            groupViewHolder.className = view.findViewById(R.id.class_name);
            view.setTag(groupViewHolder);
        }
        groupViewHolder = (GroupViewHolder) view.getTag();
        groupViewHolder.className.setText(groups.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder;
        String key = groups.get(i);
        List<UserBean> childList = childs.get(key);
        if (view == null) {
            childViewHolder = new ChildViewHolder();
            view = layoutInflater.inflate(R.layout.child_item_layout, null);
            childViewHolder.imageId = view.findViewById(R.id.image);
            childViewHolder.username = view.findViewById(R.id.username);
            childViewHolder.password = view.findViewById(R.id.password);
            view.setTag(childViewHolder);
        }
        childViewHolder = (ChildViewHolder) view.getTag();
        childViewHolder.imageId.setImageResource(R.mipmap.ic_launcher);
        childViewHolder.username.setText(childList.get(i1).getUsername());
        childViewHolder.password.setText(childList.get(i1).getPassword());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class GroupViewHolder {
        private TextView className;
    }

    class ChildViewHolder {
        private ImageView imageId;
        private TextView username;
        private TextView password;
    }
}
