package com.jennyni.attencedemo.activity.attence;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.db.Tb_record;
import com.jennyni.attencedemo.db.Tb_student;

/**
 * Created by Administrator on 2019/6/5.
 */

public class ChangeAttResultActivity extends AppCompatActivity {

    private static final String Tb_record_key = "Tb_record";

    public static void startActivity(Context context, Tb_record record, Tb_student student) {

        Intent intent = new Intent(context, ChangeAttResultActivity.class);
        intent.putExtra("", "");
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_result);
    }
}
