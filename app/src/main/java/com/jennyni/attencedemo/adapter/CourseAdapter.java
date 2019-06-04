package com.jennyni.attencedemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.apater.BaseAdapter;
import com.jennyni.attencedemo.adapter.apater.BaseHolder;
import com.jennyni.attencedemo.db.Tb_course;

/**
 * Created by 969 on 2019/6/4.
 */

public class CourseAdapter extends BaseAdapter<Tb_course, CourseAdapter.ViewHolder> {


    public CourseAdapter(Context context) {
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

    class ViewHolder extends BaseHolder<Tb_course> implements View.OnClickListener {

        private TextView tv_code;
        private TextView tv_name;

        public ViewHolder(View contentView) {
            super(contentView);
        }

        @Override
        public void initView(View contentView) {
            tv_code = contentView.findViewById(R.id.tv_code);
            tv_name = contentView.findViewById(R.id.tv_name);
            contentView.setOnClickListener(this);
        }

        @Override
        public void initData(Tb_course mode, int position, View contentView) {
            tv_name.setText(mode.getCourname());
            tv_code.setText(mode.getCourcode());
        }

        @Override
        public void onClick(View v) {
            if (childViewClickListener != null) {
                childViewClickListener.setOnChildViewClickListener(v, position);
            }
        }
    }
}
