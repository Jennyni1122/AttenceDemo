package com.jennyni.attencedemo.activity.attence;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.SearchResultAdaper;
import com.jennyni.attencedemo.adapter.apater.IAdapter;
import com.jennyni.attencedemo.dao.RecordDAO;
import com.jennyni.attencedemo.dao.StudentDAO;
import com.jennyni.attencedemo.db.Tb_record;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.List;

/**
 * Created by 969 on 2019/6/6.
 */

public class AttResultSearchResultActivity extends AppCompatActivity implements IAdapter.ChildViewClickListener {

    private static final String COURCODE_KEY = "courCode";
    private static final String COURSECODE_KEY = "courseCode";

    private ListView lv_result;
    private SearchResultAdaper adapter;
    private Tb_student student;
    private String courseCode;
    private String courCode;
    private RecordDAO recordDAO;

    public static void startActivity(Context context, String courCode, String courseCode) {
        Intent intent = new Intent(context, AttResultSearchResultActivity.class);
        intent.putExtra(COURCODE_KEY, courCode);
        intent.putExtra(COURSECODE_KEY, courseCode);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        lv_result = findViewById(R.id.lv_result);
        adapter = new SearchResultAdaper(this);
        lv_result.setAdapter(adapter);
        adapter.setOnChildViewClickListener(this);
        initData();
    }

    private void initData() {

        courCode = getIntent().getStringExtra(COURCODE_KEY);
        courseCode = getIntent().getStringExtra(COURSECODE_KEY);
        recordDAO = new RecordDAO(getContentResolver());
        List<Tb_record> tb_records = recordDAO.queryByAttNo(Tb_record.getAttNo(courseCode, courCode));
        if (tb_records.size() == 0) {
            Toast.makeText(this, "无相关记录", Toast.LENGTH_SHORT).show();
            return;
        }
        adapter.addAll(tb_records);
        StudentDAO studentDAO = new StudentDAO(getContentResolver());
        List<Tb_student> list = studentDAO.querByCourseCode(courseCode);
        for (int i = 0; i < list.size(); i++) {
            Tb_student student = list.get(i);
            if (student.getCourcode().equals(courCode)) {
                this.student = student;
            }
        }
    }

    @Override
    public void setOnChildViewClickListener(View v, int position) {
        EditAttResultActivity.startActivity(this, adapter.getList().get(position), courseCode, student);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EditAttResultActivity.REQUESTCODE && resultCode == RESULT_OK) {
            List<Tb_record> tb_records = recordDAO.queryByAttNo(Tb_record.getAttNo(courseCode, courCode));
            adapter.addAll(tb_records);
        }
    }
}
