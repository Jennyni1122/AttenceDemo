package com.jennyni.attencedemo.activity.student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.activity.course.CourseInfoActivity;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_course;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.List;

/**
 * 添加学生信息    。。。。。。。。。。。。。。。。。。。。。。 数据库写的有问题
 */
public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TB_STUDENT_KEY = "Tb_student";
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;

    private Spinner sp_coursetype;
    private EditText et_stunum, et_stuname, et_stumac, et_stuclass, et_stuacademy, et_stuphone, et_teaemail;
    private Button btn_saveinfo, btn_clearinfo;

    private String courseType, stuNum, stuName, stuMac, stuClass, stuAcademy, stuPhone, teaEmail;
    private StudentDAO studentDAO;
    private Tb_student tb_student;
    private boolean isEdit; //是否是编辑学生信息
    private String courseCode;


    public static void startActivity(Context context, Tb_student student) {
        Intent intent = new Intent(context, AddStudentActivity.class);
        intent.putExtra(TB_STUDENT_KEY, student);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();
        initData();
    }

    private void initData() {

        tb_student = (Tb_student) getIntent().getSerializableExtra(TB_STUDENT_KEY);

        if (tb_student != null) {
            et_stunum.setText(tb_student.getMajor());
            et_stunum.setClickable(false);
            et_stunum.setEnabled(false);
            et_stuname.setText(tb_student.getName());
            et_stumac.setText(tb_student.getMac());
            et_stuclass.setText(tb_student.getCourcode());
            et_stuacademy.setText(tb_student.getAcademy());
            et_stuphone.setText(tb_student.getPhone());
            et_teaemail.setText(tb_student.getAssemail());
        }
        isEdit = tb_student != null;

    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("添加学生信息");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sp_coursetype = (Spinner) findViewById(R.id.sp_coursetype);
        et_stunum = (EditText) findViewById(R.id.et_stunum);
        et_stuname = (EditText) findViewById(R.id.et_stuname);
        et_stumac = (EditText) findViewById(R.id.et_stumac);
        et_stuclass = (EditText) findViewById(R.id.et_stuclass);
        et_stuacademy = (EditText) findViewById(R.id.et_stuacademy);
        et_stuphone = (EditText) findViewById(R.id.et_stuphone);
        et_teaemail = (EditText) findViewById(R.id.et_teaemail);
        btn_saveinfo = (Button) findViewById(R.id.btn_saveinfo);
        btn_clearinfo = (Button) findViewById(R.id.btn_clearinfo);


        CourseDAO courseDAO = new CourseDAO(getContentResolver());
        final List<Tb_course> list = courseDAO.queryAll();
        if (list.size() == 0) {
            Toast.makeText(this, "请先添加课程~", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Tb_course tb_course = list.get(i);
            arr[i] = String.format("%s | %s", tb_course.getCourcode(), tb_course.getCourname());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        sp_coursetype.setAdapter(adapter);
        courseCode = list.get(0).getCourcode();
        sp_coursetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseCode = list.get(position).getCourcode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_saveinfo.setOnClickListener(this);
        btn_clearinfo.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        StudentDAO studentDAO = new StudentDAO(getContentResolver());
        switch (v.getId()) {
            case R.id.btn_saveinfo:
                getEditString();        //获取控件上的字符串
                if (!TextUtils.isEmpty(stuNum)) {
                    //创建studentDao对象
//                    studentDAO = new StudentDAO(AddStudentActivity.this);
                    if (!isEdit) {
                        //课程code暂时没弄
                        tb_student = new Tb_student(stuNum, stuName, stuMac, courseCode, stuClass, stuAcademy, stuPhone, teaEmail, courseCode);
//                    studentDAO.addStudentInfo(tb_student);
                        if (!studentDAO.isHasCourCode(stuNum, courseCode)) {
                            studentDAO.addStudentInfo(tb_student);
                            Toast.makeText(this, "添加成功~", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "改课程已经存在该学号~", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        studentDAO.updateStudentInfo(tb_student);
                        Toast.makeText(this, "修改成功~", Toast.LENGTH_SHORT).show();
                    }
//                    List<Tb_student> list = studentDAO.queryAll();
//                    Log.e("onClick: ", list.size() + "");
                } else {
                    Toast.makeText(this, "填写信息不完整~", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_clearinfo:
                if (!isEdit) {
                    getEditClear();
                } else {
                    studentDAO.deteleStudentInfo(tb_student.getCourcode());
                    Toast.makeText(this, "删除成功~", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

        }

    }

    private void getEditClear() {
        et_stunum.setText("");
        et_stuname.setText("");
        et_stumac.setText("");
        et_stuclass.setText("");
        et_stuacademy.setText("");
        et_stuphone.setText("");
        et_teaemail.setText("");
        //下拉框
        sp_coursetype.setSelection(0);

    }

    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        stuNum = et_stunum.getText().toString();
        stuName = et_stuname.getText().toString();
        stuMac = et_stumac.getText().toString();
        stuClass = et_stuclass.getText().toString().trim();
        stuAcademy = et_stuacademy.getText().toString().trim();
        stuPhone = et_stuphone.getText().toString().trim();
        teaEmail = et_teaemail.getText().toString().trim();
        //下拉框
        courseType = sp_coursetype.getSelectedItem().toString().trim();

    }
}
