package com.jennyni.attencedemo.activity.student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.StudentAdaper;
import com.jennyni.attencedemo.adapter.apater.IAdapter;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_course;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.List;

public class StudentNameActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_student;
    private IAdapter<Tb_student> adapter;
    private StudentDAO studentDAO;
    private Spinner sp_coursetype;
    private String courseCode;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, StudentNameActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_name);
        initView();
        studentDAO = new StudentDAO(getContentResolver());
    }


    private void initView() {

        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        sp_coursetype = findViewById(R.id.sp_coursetype);
        tv_main_title.setText("学生信息");
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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        sp_coursetype.setAdapter(arrayAdapter);
        courseCode = list.get(0).getCourcode();
        sp_coursetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseCode = list.get(position).getCourcode();
                initData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        lv_student = findViewById(R.id.lv_student);
        adapter = new StudentAdaper(this);
        lv_student.setAdapter((ListAdapter) adapter.getAdapter());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        //数据库需要添加课程
        List<Tb_student> tb_students = studentDAO.querByCourse(courseCode);
        adapter.addAll(tb_students);
    }
}
