package com.example.wsh666.studentsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wsh666.expandlistview.R;
import com.example.wsh666.studentsystem.bean.StudentBean;

import java.util.List;

/**
 * Created by wsh666 on 2018/8/24.
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

public class StudentListAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<StudentBean> stuList;
    private Context mContext;

    public StudentListAdapter(List<StudentBean> stuList, Context mContext) {
        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.stuList = stuList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return stuList.size();
    }

    @Override
    public Object getItem(int i) {
        return stuList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = layoutInflater.inflate(R.layout.student_item_layout, null);
            viewHolder=new ViewHolder(view);
            viewHolder.stuId=view.findViewById(R.id.stuId);
            viewHolder.stuName=view.findViewById(R.id.stuName);
            viewHolder.stuClass=view.findViewById(R.id.stuClass);
            viewHolder.mobile=view.findViewById(R.id.mobile);
            viewHolder.sex=view.findViewById(R.id.sex);
            viewHolder.favorite=view.findViewById(R.id.favorite);
            view.setTag(viewHolder);
        }
        viewHolder=(ViewHolder) view.getTag();
        viewHolder.stuId.setText(String.valueOf(stuList.get(i).getStuId()));
        viewHolder.stuName.setText(stuList.get(i).getStuName());
        viewHolder.stuClass.setText(stuList.get(i).getStuClass());
        viewHolder.mobile.setText(stuList.get(i).getMobile());
        viewHolder.sex.setText(stuList.get(i).getSex());
        viewHolder.favorite.setText(stuList.get(i).getFavorite());
        return view;
    }

    public static class ViewHolder {
        public View rootView;
        public TextView stuId;
        public TextView stuName;
        public TextView stuClass;
        public TextView mobile;
        public TextView sex;
        public TextView favorite;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.stuId = (TextView) rootView.findViewById(R.id.stuId);
            this.stuName = (TextView) rootView.findViewById(R.id.stuName);
            this.stuClass = (TextView) rootView.findViewById(R.id.stuClass);
            this.mobile = (TextView) rootView.findViewById(R.id.mobile);
            this.sex = (TextView) rootView.findViewById(R.id.sex);
            this.favorite = (TextView) rootView.findViewById(R.id.favorite);
        }

    }
}
