package com.jennyni.attencedemo.activity.course;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.db.Tb_course;

import java.util.List;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TB_COURSE_KEY = "Tb_course";

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;

    private EditText et_courcode, et_courname, et_courtime, et_courplace, et_bookteacher;
    private Button btn_saveinfo, btn_clearinfo;

    private String courCode, courName, courTime, courPlace, bookTeacher;
    private Tb_course course;
    private boolean isEdit; //判断是否是编辑课程

    public static void startActivity(Context context, Tb_course course) {
        Intent intent = new Intent(context, AddCourseActivity.class);
        intent.putExtra(TB_COURSE_KEY, course);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        initView();
        initData();
    }

    private void initData() {
        course = (Tb_course) getIntent().getSerializableExtra(TB_COURSE_KEY);
        if (course != null) {
            et_courcode.setText(course.getCourcode());
            et_courname.setText(course.getCourname());
            et_courtime.setText(course.getCourtime());
            et_courplace.setText(course.getCourplace());
            et_bookteacher.setText(course.getTeacher());
            btn_clearinfo.setText("删除");
            btn_saveinfo.setText("修改");
        }
        isEdit = course != null;
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("添加课程");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCourseActivity.this.finish();
            }
        });

        et_courcode = (EditText) findViewById(R.id.et_courcode);
        et_courname = (EditText) findViewById(R.id.et_courname);
        et_courtime = (EditText) findViewById(R.id.et_courtime);
        et_courplace = (EditText) findViewById(R.id.et_courplace);
        et_bookteacher = (EditText) findViewById(R.id.et_bookteacher);

        btn_saveinfo = (Button) findViewById(R.id.btn_saveinfo);
        btn_clearinfo = (Button) findViewById(R.id.btn_clearinfo);
        btn_saveinfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        CourseDAO courseDAO = new CourseDAO(getContentResolver());
        switch (v.getId()) {
            case R.id.btn_saveinfo:
                getEditString();        //获取控件上的字符串

                Tb_course tb_course = new Tb_course(courCode, courName, courTime, courPlace, bookTeacher);
                if (isEdit) {
                    courseDAO.updateByCourCode(tb_course);
                    Toast.makeText(this, "修改成功~", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (!courseDAO.isHasCourseCode(courCode)) {
                        courseDAO.insert(tb_course);
                        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "该课程已存在", Toast.LENGTH_SHORT).show();
                    }

                }

//                List<Tb_course> list = courseDAO.queryAll();
//                Log.e("onClick: ", list.size() + "");
                break;
            case R.id.btn_clearinfo:
                if (!isEdit) {
                    getEditClear();
                } else {
                    courseDAO.deleteByCourCode(course);
                }

                break;
        }

    }

    private void getEditClear() {
        et_courcode.setText("");
        et_courname.setText("");
        et_courtime.setText("");
        et_courplace.setText("");
        et_bookteacher.setText("");
    }

    /**
     * 获取控件上的字符串
     */
    private void getEditString() {
        courCode = et_courcode.getText().toString();
        courName = et_courname.getText().toString();
        courTime = et_courtime.getText().toString();
        courPlace = et_courplace.getText().toString().trim();
        bookTeacher = et_bookteacher.getText().toString().trim();
    }

}
