package com.jennyni.attencedemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.adapter.apater.BaseAdapter;
import com.jennyni.attencedemo.adapter.apater.BaseHolder;
import com.jennyni.attencedemo.db.Tb_record;
import com.jennyni.attencedemo.db.Tb_student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/6/6.
 */

public class ExcleAdapter extends BaseAdapter<Tb_record, ExcleAdapter.ViewHolder> {


    private Map<String, Tb_student> map = new HashMap<>();

    public ExcleAdapter(Context context) {
        super(context);
    }


    public Map<String, Tb_student> getMap() {
        return map;
    }

    public void setSudents(List<Tb_student> list) {
        if (list.size() == 0) return;
        map.clear();
        for (Tb_student student : list) {
            map.put(Tb_record.getAttNo(student.getCourseCode(), student.getCourcode()), student);
        }
    }


    @Override
    public ViewHolder getViewHolder(View contentView, int itemViewType) {
        return new ViewHolder(contentView);
    }

    @Override
    public int getLayout(int itemViewType) {
        return R.layout.item_excle;
    }

    class ViewHolder extends BaseHolder<Tb_record> {

        private TextView tv_name;
        private TextView tv_result;
        private TextView tv_date;

        public ViewHolder(View contentView) {
            super(contentView);
        }

        @Override
        public void initView(View contentView) {
            tv_name = contentView.findViewById(R.id.tv_name);
            tv_result = contentView.findViewById(R.id.tv_result);
            tv_date = contentView.findViewById(R.id.tv_date);
        }

        @Override
        public void initData(Tb_record mode, int position, View contentView) {
            tv_result.setText(mode.getAttResult());
            tv_date.setText(mode.getArrData());
            Tb_student student = map.get(mode.getAttNo());
            tv_name.setText(student == null ? "未知" : student.getName());
        }
    }
}
