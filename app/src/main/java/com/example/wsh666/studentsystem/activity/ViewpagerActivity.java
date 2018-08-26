package com.example.wsh666.studentsystem.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wsh666.expandlistview.R;
import com.example.wsh666.studentsystem.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created bywsh666 on 2018/8/26 15:41
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
public class ViewpagerActivity extends Activity {

    private ViewPager viewPager;
    private List<View> viewList;
    private ViewPagerAdapter viewPagerAdapter;
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initView();
        setAdapters();

        /*第三个页面的按钮点击事件*/
        Button button=viewList.get(2).findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(ViewpagerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initView() {
        viewList=new ArrayList<>();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(pageChangeListener);
        layoutInflater=getLayoutInflater();
        viewList.add(layoutInflater.inflate(R.layout.view_pager_1,null,false));
        viewList.add(layoutInflater.inflate(R.layout.view_pager_2,null,false));
        viewList.add(layoutInflater.inflate(R.layout.view_pager_3,null,false));
    }

    private void setAdapters() {
        viewPagerAdapter=new ViewPagerAdapter(viewList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    /*底部序号变化*/
    public void changeDD(int position){
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.no);
        for (int i=0;i<viewList.size();i++){
            ImageView imageView=(ImageView)linearLayout.getChildAt(i);
            imageView.setImageResource(R.drawable.select_no);
        }
        ImageView selectImage=(ImageView)linearLayout.getChildAt(position);
        selectImage.setImageResource(R.drawable.select);
    }

    /*viewPager监听事件*/
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener(){
        //当pager拖动时调用
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }
        //当一个新的pager被选择的时调用
        @Override
        public void onPageSelected(int position) {
            changeDD(position);
        }
        //当pager状态发生变化时，调用
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
