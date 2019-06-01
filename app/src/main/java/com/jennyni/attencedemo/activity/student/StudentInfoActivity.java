package com.jennyni.attencedemo.activity.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.activity.attence.AttencemanageActivity;
import com.jennyni.attencedemo.activity.attence.DianMingActivity;
import com.jennyni.attencedemo.activity.attence.EmailActivity;
import com.jennyni.attencedemo.activity.attence.ExcelActivity;
import com.jennyni.attencedemo.activity.attence.QueryAttenceActivity;
import com.jennyni.attencedemo.activity.attence.SMSActivity;
import com.jennyni.attencedemo.adapter.pictureAdapter;

public class StudentInfoActivity extends AppCompatActivity {

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;

    GridView gvInfo;            //创建GridView对象

    String[] titles = new String[]{"添加学生信息","导入学生信息","学生信息名册"};//定义字符串数组，存储系统功能
    int[] images = new int[]{R.drawable.addstu,R.drawable.importinfo,R.drawable.stuname};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        gvInfo = (GridView)findViewById(R.id.gvInfo);                 //获取布局文件的gvInfo组件
        pictureAdapter adapter = new pictureAdapter(titles,images,this);   //创建pictureAdapter对象
        gvInfo.setAdapter(adapter);             //为GridView设置数据源
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;           //创建Intent对象
                switch (position) {
                    case 0:
                        intent = new Intent(StudentInfoActivity.this, AddStudentActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(StudentInfoActivity.this,ImportStudentActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(StudentInfoActivity.this, StudentNameActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });


        initView();
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("学生管理");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentInfoActivity.this.finish();
            }
        });
    }

}
