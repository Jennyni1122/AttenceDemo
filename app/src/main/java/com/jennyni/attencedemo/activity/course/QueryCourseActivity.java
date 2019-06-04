package com.jennyni.attencedemo.activity.course;

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
import com.jennyni.attencedemo.activity.student.AddStudentActivity;
import com.jennyni.attencedemo.adapter.CourseAdapter;
import com.jennyni.attencedemo.adapter.apater.IAdapter;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_course;

import java.util.List;

public class QueryCourseActivity extends AppCompatActivity implements IAdapter.ChildViewClickListener {
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private ListView lv_course;
    private IAdapter<Tb_course> adapter;
    private CourseDAO courseDAO;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, QueryCourseActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_course);
        initView();
        courseDAO = new CourseDAO(getContentResolver());
    }

    private void initData() {
        adapter.addAll(courseDAO.queryAll());
    }


    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        lv_course = findViewById(R.id.lv_course);
        tv_main_title.setText("课程查询");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryCourseActivity.this.finish();
            }
        });
        adapter = new CourseAdapter(this);
        lv_course.setAdapter((ListAdapter) adapter.getAdapter());
        adapter.setOnChildViewClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    @Override
    public void setOnChildViewClickListener(View v, int position) {
        Tb_course tb_course = adapter.getList().get(position);
        AddCourseActivity.startActivity(this, tb_course);

    }
}
