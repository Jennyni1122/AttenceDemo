package com.jennyni.attencedemo.activity.attence;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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

    private Spinner sp_coursetype;
    private ListView lv_record;
    private String courseCode;
    private ExcleAdapter adapter;

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
        sp_coursetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseCode = list.get(position).getCourcode();
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
//        ExcelUtils.format();
//        ExcelUtils.initExcel("");
//        ExcelUtils.writeObjListToExcel();
    }

    /**
     * 查询
     *
     * @param view
     */
    public void search(View view) {
        StudentDAO studentDAO = new StudentDAO(getContentResolver());
        RecordDAO recordDAO = new RecordDAO(getContentResolver());
        List<Tb_student> tb_students = studentDAO.querByCourseCode(courseCode);
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
