package com.jennyni.attencedemo.adapter.apater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<M, V extends BaseHolder<M>> extends
        android.widget.BaseAdapter implements IAdapter<M> {

    protected List<M> list = new ArrayList<M>();
    protected Context context;
    private LayoutInflater from;
    protected ChildViewClickListener childViewClickListener;
    protected ChildViewClickListener2 childViewClickListener2;
    protected boolean isReverse;

    public BaseAdapter(Context context) {
        super();
        this.context = context;
        from = LayoutInflater.from(context);
    }

    public void add(M mode) {
        list.add(mode);
        notifyDataSetChanged();
    }

    public void remove(M mode) {
        list.remove(mode);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        list.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public void addAllLoad(List<M> t) {
        // TODO Auto-generated method stub
        if (t != null) {
            list.addAll(t);
            notifyDataSetChanged();
        }
    }

    @Override
    public void reverse() {

    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View con, ViewGroup arg2) {
        // TODO Auto-generated method stub
        int itemViewType = getItemViewType(position);
        V mHolder = null;

        if (con == null) {
            con = from.inflate(getLayout(itemViewType), arg2, false);
            mHolder = getViewHolder(con, itemViewType);
            con.setTag(mHolder);
        } else {
            mHolder = (V) con.getTag();
        }

        if (!isReverse) {
            mHolder.setPosition(position);
            mHolder.initData(list.get(position), position, con);
        } else {
            position = list.size() - position - 1;
            mHolder.setPosition(position);
            mHolder.initData(list.get(position), position, con);
        }

        return con;
    }

    public abstract V getViewHolder(View contentView, int itemViewType);

    @Override
    public void notifyDataChanged() {
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List<M> t) {
        // TODO Auto-generated method stub
//        if (t != null) {
//            list.clear();
//            list.addAll(t);
//            notifyDataSetChanged();
//        }
        list.clear();
        if (t != null) {
            list.addAll(t);
        }
        notifyDataChanged();
    }

    @Override
    public void clearList() {
        // TODO Auto-generated method stub
        list.clear();
        notifyDataSetChanged();

    }

    @Override
    public List<M> getList() {
        // TODO Auto-generated method stub
        return list;
    }

    @Override
    public BaseAdapter<M, ?> getAdapter() {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public void setOnChildViewClickListener(
            ChildViewClickListener childViewClickListener) {
        // TODO Auto-generated method stub
        this.childViewClickListener = childViewClickListener;
    }

    @Override
    public void setOnChildViewClickListener2(
            ChildViewClickListener2 childViewClickListener) {
        // TODO Auto-generated method stub
        this.childViewClickListener2 = childViewClickListener;
    }

    public abstract int getLayout(int itemViewType);

}
