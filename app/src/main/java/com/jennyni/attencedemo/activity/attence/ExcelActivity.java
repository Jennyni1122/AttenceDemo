package com.jennyni.attencedemo.activity.attence;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.ExcleAdapter;
import com.jennyni.attencedemo.dao.CourseDAO;
import com.jennyni.attencedemo.dao.RecordDAO;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_course;
import com.jennyni.attencedemo.db.Tb_record;
import com.jennyni.attencedemo.db.Tb_student;
import com.jennyni.attencedemo.utils.ExcelUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelActivity extends AppCompatActivity {


    private TextView tv_main_title, tv_back;
    private RelativeLayout rl_title_bar;
    private Spinner sp_coursetype;
    private ListView lv_record;
    private String courseCode;
    private ExcleAdapter adapter;
    private List<Tb_student> tb_students;
    private String courseName;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, ExcelActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        initView();
    }

    private void initView() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("信息导出");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.rdTextColorPress));
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExcelActivity.this.finish();
            }
        });
        lv_record = findViewById(R.id.lv_record);
        sp_coursetype = findViewById(R.id.sp_coursetype);
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
        courseName = list.get(0).getCourname();
        sp_coursetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseCode = list.get(position).getCourcode();
                courseName = list.get(position).getCourname();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.adapter = new ExcleAdapter(this);
        lv_record.setAdapter(this.adapter);

    }


    /**
     * 导出
     *
     * @param view
     */
    public void leadOut(View view) {
        ExcelUtils.format();
        String fileName = Environment.getExternalStorageDirectory() + "/" + courseName + courseCode + "考勤情况.xls";
        ExcelUtils.initExcel(fileName, new String[]{"姓名", "学号", "考勤情况", "时间"});
        List<List<String>> writeList = new ArrayList<>();
        List<Tb_record> recordList = adapter.getList();
        for (int i = 0; i < recordList.size(); i++) {
            Tb_record record = recordList.get(i);
            List<String> childList = new ArrayList<>();
            Tb_student student = adapter.getMap().get(record.getAttNo());
            childList.add(student == null ? "未知" : student.getName());
            childList.add(student == null ? "未知" : student.getCourcode());
            childList.add(record.getAttResult());
            childList.add(record.getArrData());
            writeList.add(childList);
        }
        ExcelUtils.writeObjListToExcel(writeList, fileName,this);

    }

    /**
     * 查询
     *
     * @param view
     */
    public void search(View view) {
        StudentDAO studentDAO = new StudentDAO(getContentResolver());
        RecordDAO recordDAO = new RecordDAO(getContentResolver());
        tb_students = studentDAO.querByCourseCode(courseCode);
        if (tb_students.size() == 0) {
            Toast.makeText(this, "改课程下并无学生~", Toast.LENGTH_SHORT).show();
            return;
        }
        List<Tb_record> list = new ArrayList<>();
        Toast.makeText(this, "正在搜索~", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < tb_students.size(); i++) {
            Tb_student tb_student = tb_students.get(i);
            List<Tb_record> records = recordDAO.queryByAttNo(Tb_record.getAttNo(courseCode, tb_student.getCourcode()));
            list.addAll(records);
        }
        adapter.setSudents(tb_students);
        adapter.addAll(list);

    }
}
