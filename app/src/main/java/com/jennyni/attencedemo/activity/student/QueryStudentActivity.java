package com.jennyni.attencedemo.activity.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.StudentAdaper;
import com.jennyni.attencedemo.adapter.apater.IAdapter;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.List;

public class QueryStudentActivity extends AppCompatActivity implements IAdapter.ChildViewClickListener {

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;


    private EditText et_stunum;
    private ListView lv_student;
    private StudentAdaper adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_student);
        initView();

    }

    private void initData() {
        String courCode = et_stunum.getText().toString().trim();
        if (courCode.length() == 0) {
            return;
        }


        StudentDAO studentDAO = new StudentDAO(getContentResolver());
        List<Tb_student> tb_students = studentDAO.querByCourCode(courCode);
        adapter.addAll(tb_students);

    }


    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("查询学生信息");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        et_stunum = findViewById(R.id.et_stunum);
        lv_student = findViewById(R.id.lv_student);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new StudentAdaper(this);
        lv_student.setAdapter(adapter);
        adapter.setOnChildViewClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void setOnChildViewClickListener(View v, int position) {
        Tb_student tb_student = adapter.getList().get(position);
        AddStudentActivity.startActivity(this, tb_student);
    }
}
