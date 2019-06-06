package com.jennyni.attencedemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.apater.BaseAdapter;
import com.jennyni.attencedemo.adapter.apater.BaseHolder;
import com.jennyni.attencedemo.db.Tb_student;

/**
 * Created by 969 on 2019/6/4.
 */

public class StudentAdaper extends BaseAdapter<Tb_student, StudentAdaper.ViewHolder> {

    public StudentAdaper(Context context) {
        super(context);
    }

    @Override
    public ViewHolder getViewHolder(View contentView, int itemViewType) {
        return new ViewHolder(contentView);
    }

    @Override
    public int getLayout(int itemViewType) {
        return R.layout.item_course;
    }

    class ViewHolder extends BaseHolder<Tb_student> implements View.OnClickListener {

        private TextView tv_code;
        private TextView tv_name;
        private TextView tv_course;

        public ViewHolder(View contentView) {
            super(contentView);
        }

        @Override
        public void initView(View contentView) {
            tv_code = contentView.findViewById(R.id.tv_code);
            tv_name = contentView.findViewById(R.id.tv_name);
            tv_course = contentView.findViewById(R.id.tv_result);
            contentView.setOnClickListener(this);
        }

        @Override
        public void initData(Tb_student mode, int position, View contentView) {
            tv_code.setText(mode.getCourcode());
            tv_name.setText(mode.getName());
            tv_course.setText(mode.getClassNo()); //课程名字
        }

        @Override
        public void onClick(View v) {
            if (childViewClickListener != null) {
                childViewClickListener.setOnChildViewClickListener(v, position);
            }
        }
    }
}
