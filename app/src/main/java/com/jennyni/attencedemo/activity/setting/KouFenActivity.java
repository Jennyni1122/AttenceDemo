package com.jennyni.attencedemo.activity.setting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.utils.SPUtils;

public class KouFenActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private EditText ed_percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kou_fen);
        initView();
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("添加学生信息");
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
        ed_percent = findViewById(R.id.ed_percent);
    }


    public void onSure(View view) {
        SPUtils.save(Integer.valueOf(ed_percent.getText().toString()), SPUtils.PERCENT_KEY);
        Toast.makeText(this, "设置成功！", Toast.LENGTH_SHORT).show();
    }

    public void onCancle(View view) {
        ed_percent.setText("");
    }


}
