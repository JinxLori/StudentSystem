package com.example.wsh666.studentsystem.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wsh666.expandlistview.R;
import com.example.wsh666.studentsystem.bean.StudentBean;
import com.example.wsh666.studentsystem.dao.StudentDBDao;
import com.example.wsh666.studentsystem.util.CreateDB;

/**
 * Created bywsh666 on 2018/8/25 10:19
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
public class UpdateStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private CreateDB createDB;
    private StudentDBDao studentDBDao;
    private SQLiteDatabase db;
    private EditText stuName;
    private EditText mobile;
    private EditText stuClass;
    private RadioButton m;
    private RadioButton w;
    private RadioGroup radioGroup;
    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private CheckBox checkbox4;
    private LinearLayout allCheckBox;
    private Button update;
    private Button reset;
    private String stuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        Intent intent = getIntent();
        stuId = intent.getStringExtra("stuId");

        initView();
        StudentBean student = studentDBDao.getStudentById(db, stuId);
        writeDataToUI(student);
    }

    private void initView() {
        createDB = new CreateDB().getInstance();
        db = createDB.createDB(this, "my.db", null);
        studentDBDao = new StudentDBDao();

        stuName = (EditText) findViewById(R.id.stuName);
        mobile = (EditText) findViewById(R.id.mobile);
        stuClass = (EditText) findViewById(R.id.stuClass);
        m = (RadioButton) findViewById(R.id.m);
        w = (RadioButton) findViewById(R.id.w);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        allCheckBox = (LinearLayout) findViewById(R.id.allCheckBox);
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
    }

    private void writeDataToUI(StudentBean studentBean) {
        /*将文本框按照内容设置文字*/
        stuName.setText(studentBean.getStuName());
        stuClass.setText(studentBean.getStuClass());
        mobile.setText(studentBean.getMobile());

        /*将RadioGroup按照内容设置为选中状态*/
        if(m.getText().equals(studentBean.getSex())){
            m.setChecked(true);
        }else{
            w.setChecked(true);
        }
        /*将CheckBox按照内容设置为选中状态*/
        String checkBox[]={checkbox1.getText().toString(),checkbox2.getText().toString(),
                            checkbox3.getText().toString(),checkbox4.getText().toString()};
        String checks[]=studentBean.getFavorite().split(",");
        for(int i=0;i<checks.length;i++){
            Log.e("Up",checks[i]);
            for(int j=0;j<checkBox.length;j++){
                if(checks[i].equals(checkBox[j])){
                    ((CheckBox)allCheckBox.getChildAt(j)).setChecked(true);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                if(submit()){
                    StudentBean student=getStudentFromUI();
                    studentDBDao.updateStudent(db,student,stuId);/*更新数据*/
                    Intent intent =new Intent();
                    intent.setClass(UpdateStudentActivity.this,MainActivity.class);
                    startActivity(intent);
                    this.finish();
                }
                break;
            case R.id.reset:
                resetData();//清空输入
                break;
        }
    }

    public String getSex() {
        String sex = null;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton r = (RadioButton) radioGroup.getChildAt(i);
            if (r.isChecked()) {
                sex = r.getText().toString();
//                Toast.makeText(AddStudentActivity.this,r.getText(), Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return sex;
    }

    public String getFavorite() {
        StringBuffer sb = new StringBuffer();
        //拿到所有的子类长度
        int cNum = allCheckBox.getChildCount();
        for (int i = 0; i < cNum; i++) {
            //根据i 拿到每一个CheckBox
            CheckBox cb = (CheckBox) allCheckBox.getChildAt(i);
            //判断CheckBox是否被选中
            if (cb.isChecked()) {
                //把被选中的文字添加到StringBuffer中
                sb.append(cb.getText().toString() + ",");
            }
        }
//        Toast.makeText(AddStudentActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
        return sb.toString();
    }

    /*获取界面上用户输入的信息*/
    public StudentBean getStudentFromUI() {
        String name = stuName.getText().toString();
        String classNo = stuClass.getText().toString();
        String mobileNo = mobile.getText().toString();
        StudentBean student = new StudentBean();
        student.setStuName(name);
        student.setStuClass(classNo);
        student.setMobile(mobileNo);
        student.setSex(getSex());
        student.setFavorite(getFavorite());
//        Toast.makeText(AddStudentActivity.this,student.toString(),Toast.LENGTH_SHORT).show();
        return student;
    }

    public void resetData() {
        stuName.setText("");
        stuClass.setText("");
        radioGroup.clearCheck();
        mobile.setText("");

        int cNum = allCheckBox.getChildCount();
        for (int i = 0; i < cNum; i++) {
            //根据i 拿到每一个CheckBox
            CheckBox cb = (CheckBox) allCheckBox.getChildAt(i);
            cb.setChecked(false);
        }
    }
    private boolean submit() {
        // validate
        String stuNameString = stuName.getText().toString().trim();
        if (TextUtils.isEmpty(stuNameString)) {
            Toast.makeText(this, "stuNameString不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String mobileString = mobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobileString)) {
            Toast.makeText(this, "mobileString不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        String stuClassString = stuClass.getText().toString().trim();
        if (TextUtils.isEmpty(stuClassString)) {
            Toast.makeText(this, "stuClassString不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
        // TODO validate success, do something
    }
}
