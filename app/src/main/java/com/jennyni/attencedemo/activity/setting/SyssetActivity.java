package com.jennyni.attencedemo.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.activity.welcome.FindPswActivity;
import com.jennyni.attencedemo.adapter.pictureAdapter;

/**
 * 设置功能
 */
public class SyssetActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private RelativeLayout rl_modify_psw, rl_security_setting, rl_exit_login;
    public static SyssetActivity instance = null;

    GridView gvInfo;            //创建GridView对象
    String[] titles = new String[]{"扣分设置","修改密码","设置密保"};//定义字符串数组，存储系统功能
    int[] images = new int[]{R.drawable.koufen,R.drawable.changepass,R.drawable.mibao};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sysset);

        instance = this;
        init();

        gvInfo = (GridView)findViewById(R.id.gvInfo);                 //获取布局文件的gvInfo组件
        pictureAdapter adapter = new pictureAdapter(titles,images,this);   //创建pictureAdapter对象
        gvInfo.setAdapter(adapter);             //为GridView设置数据源
        gvInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;           //创建Intent对象
                switch (position) {
                    case 0:
                        intent = new Intent(SyssetActivity .this, KouFenActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(SyssetActivity .this, ModifyPswActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(SyssetActivity .this, FindPswActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });

    }

    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("设置");
        tv_back = (TextView) findViewById(R.id.tv_back);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        tv_back.setVisibility(View.VISIBLE);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SyssetActivity.this.finish();
            }
        });
    }
}




