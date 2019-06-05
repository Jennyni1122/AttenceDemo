package com.jennyni.attencedemo.activity.attence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.dao.RecordDAO;
import com.jennyni.attencedemo.dao.StasDAO;
import com.jennyni.attencedemo.db.Tb_record;
import com.jennyni.attencedemo.db.Tb_stas;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.List;

/**
 * Created by Administrator on 2019/6/5.
 */

public class EditAttResultActivity extends AppCompatActivity {

    public static final String TB_RECORD_KEY = "Tb_record";
    private static final String TB_STUDENT_KEY = "Tb_student";
    private static final String SOURCODE_KEY = "sourcode";
    public static final int REQUESTCODE = 0x12;

    private TextView tv_code;
    private TextView tv_name;
    private Spinner sp_coursetype;
    private Tb_student student;
    private Tb_record record;
    private String result;
    private RecordDAO recordDAO;
    private String sourceCode;
    private StasDAO stasDAO;

    public static void startActivity(Activity context, Tb_record record, String sourcode, Tb_student student) {
        Intent intent = new Intent(context, EditAttResultActivity.class);
        intent.putExtra(TB_RECORD_KEY, record);
        intent.putExtra(SOURCODE_KEY, sourcode);
        intent.putExtra(TB_STUDENT_KEY, student);
        context.startActivityForResult(intent, REQUESTCODE);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_result);
        initView();
        initData();
    }

    private void initData() {
        stasDAO = new StasDAO(getContentResolver());
        recordDAO = new RecordDAO(getContentResolver());
        sourceCode = getIntent().getStringExtra(SOURCODE_KEY);
        tv_code.setText(sourceCode);
        student = (Tb_student) getIntent().getSerializableExtra(TB_STUDENT_KEY);
        record = (Tb_record) getIntent().getSerializableExtra(TB_RECORD_KEY);
        if (student != null) {
            tv_name.setText(student.getName());
        }
    }

    private void initView() {
        tv_code = findViewById(R.id.tv_code);
        tv_name = findViewById(R.id.tv_name);
        sp_coursetype = findViewById(R.id.sp_coursetype);
        final String[] stringArray = getResources().getStringArray(R.array.att_result);
        result = stringArray[0];
        sp_coursetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                result = stringArray[position];
                record.setAttResult(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void edit(View view) {
        RecordDAO recordDAO = new RecordDAO(getContentResolver());
        recordDAO.updateById(record, record.getId());
        upDateStas(student);
        Intent intent = new Intent();
        intent.putExtra(TB_RECORD_KEY, record);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancle(View view) {
        finish();
    }


    void upDateStas(Tb_student student) {
        List<Tb_record> tb_records = recordDAO.queryByAttNo(Tb_record.getAttNo(sourceCode, student.getCourcode()));
        int[] nums = new int[3];
        for (int i = 0; i < tb_records.size(); i++) {
            if (tb_records.get(i).getAttResult().equals("已到")) {
                nums[0]++;
            } else if (tb_records.get(i).getAttResult().equals("旷课")) {
                nums[1]++;
            } else {
                nums[2]++;
            }
        }

        Tb_stas tb_stas = new Tb_stas(Tb_record.getAttNo(sourceCode, student.getCourcode()), student.getCourcode(), sourceCode, tb_records.size(), nums[0], nums[1], nums[2], 100);
        stasDAO.insertOrUpdateStas(tb_stas);
    }

}
