package com.example.wsh666.studentsystem.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wsh666.expandlistview.R;
import com.example.wsh666.studentsystem.adapter.StudentListAdapter;
import com.example.wsh666.studentsystem.adapter.StudentListForClassAdapter;
import com.example.wsh666.studentsystem.bean.StudentBean;
import com.example.wsh666.studentsystem.dao.StudentDBDao;
import com.example.wsh666.studentsystem.util.CreateDB;

import java.util.List;
import java.util.Map;

/**
 * Created bywsh666 on 2018/8/23 15:37
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
public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    private ExpandableListView studentListByGroupView;
    private List<String> groups;
    private Map<String, List<StudentBean>> childs;
    private StudentListForClassAdapter studentListForClassAdapter;

    private ListView studentListView;
    private List<StudentBean> stuList;
    private StudentListAdapter studentListAdapter;
    private CreateDB createDB;

    private SQLiteDatabase db;

    private Button change;

    private StudentDBDao studentDBDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        db = createDB.createDB(this, "my.db", null);
        stuList=studentDBDao.getStudentFromDB(db);

        groups = studentDBDao.getClassFromTableStudent(db);
        childs = studentDBDao.getStudentForEveryClass(db, groups);

        setAdapters();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
                /*Toast.makeText(this, "点击了第一个菜单", Toast.LENGTH_SHORT).show();*/
                break;

            default:
                break;
        }
        return true;
    }

    private void initView() {
        studentListView = (ListView) findViewById(R.id.studentList);
        studentListView.setOnItemClickListener(this);
        studentListView.setOnItemLongClickListener(this);
        studentListView.setVisibility(View.VISIBLE);

        studentListByGroupView = (ExpandableListView) findViewById(R.id.studentListByGroup);
        studentListByGroupView.setVisibility(View.GONE);



        createDB = new CreateDB().getInstance();
        studentDBDao=new StudentDBDao();

        change = (Button) findViewById(R.id.change);
        change.setOnClickListener(this);
    }

    public void setAdapters() {
        /*普通列表适配器配置*/
        studentListAdapter = new StudentListAdapter(stuList, MainActivity.this);
        studentListView.setAdapter(studentListAdapter);
        /*分级列表适配器配置*/
        studentListForClassAdapter = new StudentListForClassAdapter(MainActivity.this, groups, childs);
        studentListByGroupView.setAdapter(studentListForClassAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                changeList();
                break;
        }
    }

    /*列表子项点击事件*/
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String stuId=((TextView)view.findViewById(R.id.stuId)).getText().toString();
        Intent intent =new Intent(MainActivity.this,UpdateStudentActivity.class);
        intent.putExtra("stuId",stuId);
        startActivity(intent);
    }

    /*长按删除*/
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        stuList.remove(i);
        studentListAdapter.notifyDataSetChanged();
        String stuId=String.valueOf(stuList.get(i).getStuId());
        studentDBDao.deleteStudent(db,stuId);
        Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
        return true;//如果是false 会和点击事件冲突
    }

    /*布局切换*/
    public void changeList() {
        if (studentListView.getVisibility() == View.VISIBLE) {
            studentListView.setVisibility(View.GONE);
            studentListByGroupView.setVisibility(View.VISIBLE);
            change.setText("切换至全部显示");
            /*默认全部展开*/
            int n=studentListForClassAdapter.getGroupCount();
            /*int n=studentListByGroupView.getCount();//该方法每次重新加载界面之后count都会叠加,*/
            for(int i=0;i<n;i++){
                studentListByGroupView.expandGroup(i);
            }
        } else {
            studentListView.setVisibility(View.VISIBLE);
            studentListByGroupView.setVisibility(View.GONE);
            change.setText("切换至分班显示");
        }
    }
    /*双击退出*/
    private long exitTime=0;
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return false;
        }
        return super.onKeyDown(keyCode,event);
    }

    private void exit(){
        if((System.currentTimeMillis()-exitTime)>2000) {
            Toast.makeText(getApplicationContext(),
                    "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else{
                finish();
                System.exit(0);
            }
    }

    /*重写onResume()方法,进行返回此界面之后的操作，实现界面刷新*/
    @Override
    protected void onResume() {
        super.onResume();
        initView();
        SQLiteDatabase db = createDB.createDB(this, "my.db", null);
        stuList=studentDBDao.getStudentFromDB(db);
        groups = studentDBDao.getClassFromTableStudent(db);
        childs = studentDBDao.getStudentForEveryClass(db, groups);
        setAdapters();
    }

}
