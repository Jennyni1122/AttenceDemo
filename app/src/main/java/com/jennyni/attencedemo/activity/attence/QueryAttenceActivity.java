package com.jennyni.attencedemo.activity.attence;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.dao.RecordDAO;
import com.jennyni.attencedemo.db.Tb_record;

import java.util.List;

public class QueryAttenceActivity extends AppCompatActivity {

    private EditText et_coursecode;
    private EditText et_courcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_attence);
        et_coursecode = findViewById(R.id.et_coursecode);
        et_courcode = findViewById(R.id.et_courcode);
    }


    public void search(View view) {
        AttResultSearchResultActivity.startActivity(this, et_courcode.getText().toString().trim(), et_coursecode.getText().toString().trim());
    }


}
