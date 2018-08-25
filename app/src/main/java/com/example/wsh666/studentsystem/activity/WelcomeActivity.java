package com.example.wsh666.studentsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.wsh666.expandlistview.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created bywsh666 on 2018/8/24 16:19
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
public class WelcomeActivity extends AppCompatActivity {

    private TextView time;
    private int t=5;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        setContentView(R.layout.activity_welcome);
        initView();
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t!=0||timer!=null){
                    timer.cancel();
                    timer=null;
                }
                Intent intent=new Intent();
                intent.setClass(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        timer = new Timer();//建立一个定时器
        /**
         * 第一个参数表示的是执行的任务体
         * 第二个参数延迟多少秒执行任务
         * 第三个参数是每隔多少秒执行任务
         */
        timer.schedule(new MyTask(),0,1000);
    }

    private void initView() {
        time = (TextView) findViewById(R.id.time);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time.setText("剩余"+t+"秒");
            t--;
            if(t==0){
                timer.cancel();
                timer= null;
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();
            }
        }
    };

    public class MyTask extends TimerTask {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
}
