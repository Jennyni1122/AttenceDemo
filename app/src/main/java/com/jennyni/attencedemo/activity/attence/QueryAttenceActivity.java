package com.jennyni.attencedemo.activity.attence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.dao.RecordDAO;
import com.jennyni.attencedemo.db.Tb_course;
import com.jennyni.attencedemo.db.Tb_record;

import java.util.List;

public class QueryAttenceActivity extends AppCompatActivity {
    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private Spinner sp_coursetype;
    private EditText et_courcode;
    private String courseCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_attence);

        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("考勤查询");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryAttenceActivity.this.finish();
            }
        });

        sp_coursetype = findViewById(R.id.sp_coursetype);
        et_courcode = findViewById(R.id.et_courcode);

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        sp_coursetype.setAdapter(adapter);
        courseCode = list.get(0).getCourcode();
        sp_coursetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseCode = list.get(position).getCourcode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void search(View view) {
        AttResultSearchResultActivity.startActivity(this, et_courcode.getText().toString().trim(), courseCode);
    }


}
