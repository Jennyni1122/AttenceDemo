package com.jennyni.attencedemo.adapter.apater;

import android.view.View;

import java.util.List;

public interface IAdapter<M> {

    void remove(M t);

    void remove(int index);

    void add(M t);

    void addAllLoad(List<M> t);

    void notifyDataChanged();

    void addAll(List<M> t);

    void clearList();

    List<M> getList();

    Object getAdapter();

    void reverse();

    void setOnChildViewClickListener(ChildViewClickListener childViewClickListener);

    void setOnChildViewClickListener2(ChildViewClickListener2 childViewClickListener);

    interface ChildViewClickListener {

        void setOnChildViewClickListener(View v, int position);


    }

    interface ChildViewClickListener2 {

        void setOnChildViewClickListener(View v, int parPosition, int position);

    }


}
