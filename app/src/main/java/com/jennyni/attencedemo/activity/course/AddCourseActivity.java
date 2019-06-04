package com.jennyni.attencedemo.activity.course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jennyni.attencedemo.R;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;

    private EditText et_courcode,et_courname,et_courtime,et_courplace,et_bookteacher;
    private Button btn_saveinfo,btn_clearinfo;

    private String courCode,courName,courTime,courPlace,bookTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        initView();
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

        btn_saveinfo = (Button)findViewById(R.id.btn_saveinfo);
        btn_clearinfo = (Button)findViewById(R.id.btn_clearinfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_saveinfo:
                getEditString();        //获取控件上的字符串


                break;
            case R.id.btn_clearinfo:
                getEditClear();
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
