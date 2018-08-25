package com.example.wsh666.studentsystem.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
 * Created bywsh666 on 2018/8/24 11:08
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
public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText stuName;
    private EditText mobile;
    private EditText stuClass;
    private RadioGroup allRadioGroup;
    private Button submits;
    private LinearLayout allCheckBox;
    private CreateDB createDB;
    private Button reset;
    private StudentDBDao studentDBDao;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();
    }

    private void initView() {
        stuName = (EditText) findViewById(R.id.stuName);
        mobile = (EditText) findViewById(R.id.mobile);
        stuClass = (EditText) findViewById(R.id.stuClass);
        allRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        submits = (Button) findViewById(R.id.submit);
        submits.setOnClickListener(this);
        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(this);
        allCheckBox = (LinearLayout) findViewById(R.id.allCheckBox);
        allCheckBox.setOnClickListener(this);

        createDB = new CreateDB().getInstance();
        db = createDB.createDB(this, "my.db", null);
        studentDBDao=new StudentDBDao();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                if (submit()) {//表单验证
                    StudentBean studentBean = getStudentFromUI();
                    studentDBDao.insertStudents(studentBean, db);
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    resetData();//清空输入
                }
                break;
            case R.id.reset:
                resetData();//清空输入
                break;
        }
    }

    public String getSex() {
        String sex = null;
        for (int i = 0; i < allRadioGroup.getChildCount(); i++) {
            RadioButton r = (RadioButton) allRadioGroup.getChildAt(i);
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
        allRadioGroup.clearCheck();
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

        // TODO validate success, do something
        return true;

    }
}
