package com.jennyni.attencedemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.apater.BaseAdapter;
import com.jennyni.attencedemo.adapter.apater.BaseHolder;
import com.jennyni.attencedemo.db.Tb_record;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.List;

/**
 * Created by 969 on 2019/6/4.
 */

public class RecordAdaper extends BaseAdapter<Tb_student, RecordAdaper.ViewHolder> {

    private List<Tb_record> data;

    public RecordAdaper(Context context, List<Tb_record> list) {
        super(context);
        this.data = list;
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
        private TextView tv_result;

        public ViewHolder(View contentView) {
            super(contentView);
        }

        @Override
        public void initView(View contentView) {
            tv_code = contentView.findViewById(R.id.tv_code);
            tv_name = contentView.findViewById(R.id.tv_name);
            tv_result = contentView.findViewById(R.id.tv_result);
            contentView.setOnClickListener(this);
        }

        @Override
        public void initData(Tb_student mode, int position, View contentView) {
            tv_code.setText(mode.getCourcode());
            tv_name.setText(mode.getName());
            tv_result.setText(data.get(position).getAttResult());
        }

        @Override
        public void onClick(View v) {
            if (childViewClickListener != null) {
                childViewClickListener.setOnChildViewClickListener(v, position);
            }
        }
    }
}
