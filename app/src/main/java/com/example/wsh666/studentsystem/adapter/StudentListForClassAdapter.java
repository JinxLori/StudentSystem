package com.example.wsh666.studentsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.wsh666.expandlistview.R;
import com.example.wsh666.studentsystem.bean.StudentBean;

import java.util.List;
import java.util.Map;

/**
 * Created by wsh666 on 2018/8/25.
 * 　 へ　　　　　  ／|
 * 　　/＼7　　　 ∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　│　　　　　ヽ　　 /　　〉
 * 　 Y　　　　　`　   /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class StudentListForClassAdapter extends BaseExpandableListAdapter {
    private LayoutInflater layoutInflater;
    private List<String> groups;
    private Map<String, List<StudentBean>> childs;

    public StudentListForClassAdapter(Context context, List<String> groups, Map<String, List<StudentBean>> childs) {
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
        ClassViewHolder classViewHolder;
        if(view==null){
            view = layoutInflater.inflate(R.layout.group_class_for_stu_list, null);
            classViewHolder=new ClassViewHolder(view);
            classViewHolder.classNo=view.findViewById(R.id.group_class);
            view.setTag(classViewHolder);
        }
        classViewHolder=(ClassViewHolder)view.getTag();
        classViewHolder.classNo.setText(groups.get(i)+" 班");
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String key = groups.get(i);
        List<StudentBean> childList = childs.get(key);
        StuViewHolder stuViewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.child_student_for_stu_list, null);
            stuViewHolder = new StuViewHolder(view);
            stuViewHolder.stuId = view.findViewById(R.id.stuId);
            stuViewHolder.stuName = view.findViewById(R.id.stuName);
            stuViewHolder.stuClass = view.findViewById(R.id.stuClass);
            stuViewHolder.mobile = view.findViewById(R.id.mobile);
            stuViewHolder.sex = view.findViewById(R.id.sex);
            stuViewHolder.favorite = view.findViewById(R.id.favorite);
            view.setTag(stuViewHolder);
        }
        stuViewHolder = (StuViewHolder) view.getTag();
        stuViewHolder.stuId.setText(String.valueOf(childList.get(i1).getStuId()));
        stuViewHolder.stuName.setText(childList.get(i1).getStuName());
        stuViewHolder.stuClass.setText(childList.get(i1).getStuClass());
        stuViewHolder.mobile.setText(childList.get(i1).getMobile());
        stuViewHolder.sex.setText(childList.get(i1).getSex());
        stuViewHolder.favorite.setText(childList.get(i1).getFavorite());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    public static class StuViewHolder {
        public View rootView;
        public TextView stuId;
        public TextView stuName;
        public TextView stuClass;
        public TextView mobile;
        public TextView sex;
        public TextView favorite;

        public StuViewHolder(View rootView) {
            this.rootView = rootView;
            this.stuId = (TextView) rootView.findViewById(R.id.stuId);
            this.stuName = (TextView) rootView.findViewById(R.id.stuName);
            this.stuClass = (TextView) rootView.findViewById(R.id.stuClass);
            this.mobile = (TextView) rootView.findViewById(R.id.mobile);
            this.sex = (TextView) rootView.findViewById(R.id.sex);
            this.favorite = (TextView) rootView.findViewById(R.id.favorite);
        }

    }

    public static class ClassViewHolder {
        public View rootView;
        public TextView classNo;

        public ClassViewHolder(View rootView) {
            this.rootView = rootView;
            this.classNo = (TextView) rootView.findViewById(R.id.group_class);
        }

    }
}
