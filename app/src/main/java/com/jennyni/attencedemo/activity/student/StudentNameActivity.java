package com.jennyni.attencedemo.activity.student;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.StudentAdaper;
import com.jennyni.attencedemo.adapter.apater.IAdapter;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_student;

public class StudentNameActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_student;
    private IAdapter<Tb_student> adapter;
    private StudentDAO studentDAO;

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
        studentDAO.queryAll();
    }
}
