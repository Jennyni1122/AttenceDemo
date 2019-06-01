package com.jennyni.attencedemo.activity.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.pictureAdapter;

public class CourseInfoActivity extends AppCompatActivity {

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;

    GridView gvInfo;            //创建GridView对象
    String[] titles = new String[]{"添加课程","课程查询"};//定义字符串数组，存储系统功能
    int[] images = new int[]{R.drawable.addcourse,R.drawable.querycourse};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseinfo);

        initView();

        gvInfo = (GridView)findViewById(R.id.gvInfo);                 //获取布局文件的gvInfo组件
        pictureAdapter adapter = new pictureAdapter(titles,images,this);   //创建pictureAdapter对象
        gvInfo.setAdapter(adapter);             //为GridView设置数据源
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;           //创建Intent对象
                switch (position) {
                    case 0:
                        intent = new Intent(CourseInfoActivity.this, AddCourseActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(CourseInfoActivity.this, QueryCourseActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("课程管理");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseInfoActivity.this.finish();
            }
        });
    }

}
