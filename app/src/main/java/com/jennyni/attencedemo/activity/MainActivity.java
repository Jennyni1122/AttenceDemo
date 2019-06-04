package com.jennyni.attencedemo.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.activity.attence.AttencemanageActivity;
import com.jennyni.attencedemo.activity.course.CourseInfoActivity;
import com.jennyni.attencedemo.activity.setting.SyssetActivity;
import com.jennyni.attencedemo.activity.student.StudentInfoActivity;
import com.jennyni.attencedemo.adapter.pictureAdapter;
import com.jennyni.attencedemo.permission.PermissionsActivity;

public class MainActivity extends PermissionsActivity {

    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;

    GridView gvInfo;            //创建GridView对象
    String[] titles = new String[]{"学生信息","课程信息","考勤管理","系统设置","退出"};//定义字符串数组，存储系统功能
    int[] images = new int[]{R.drawable.studentinfo,R.drawable.courseinfo,R.drawable.attencemanage,
            R.drawable.sysset, R.drawable.exit};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        gvInfo = (GridView)findViewById(R.id.gvInfo);                 //获取布局文件的gvInfo组件
        pictureAdapter adapter = new pictureAdapter(titles,images,this);   //创建pictureAdapter对象
        gvInfo.setAdapter(adapter);             //为GridView设置数据源
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;           //创建Intent对象
                switch (i){
                    case 0:
                        intent = new Intent(MainActivity.this,StudentInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this,CourseInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this,AttencemanageActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(MainActivity.this,SyssetActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        finish();
                }
            }
        });
    }

    @Override
    public String[] getPermission() {
        return new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
    }

    @Override
    public void onPermissionRequestSuccess() {

    }

    @Override
    public void onPermissionRequestFail() {
        showMissingPermissionDialog("缺失权限！");
    }

    private void initView() {
        tv_main_title= (TextView)findViewById(R.id.tv_main_title);
        tv_main_title.setText("主页");
        rl_title_bar= (RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
    }
}
