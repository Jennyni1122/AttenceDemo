package com.jennyni.attencedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jennyni.attencedemo.R;
import com.jennyni.attencedemo.model.Picture;

import java.util.ArrayList;
import java.util.List;


/**
 * 定义一个pictureAdapter类，
 * 用来分别为ViewHolder类中的TextView组件和ImageView组件
 * 设置功能的说明性文字及图标
 * Created by Administrator on 2018-5-31.
 */

public class pictureAdapter extends BaseAdapter {         //创建基于BaseAdapter的子类
    private LayoutInflater inflater;                           //创建LayoutInflater对象
    private List<Picture> pictures;                            //创建List泛型集合

    //为类创建构造函数
    public pictureAdapter(String[] titles, int[] images, Context context) {
        super();
        pictures = new ArrayList<>();                //初始化泛型集合对象
        inflater = LayoutInflater.from(context);            //初始化LayoutInflater对象
        for (int i = 0; i<images.length;i++){               //遍历图像数组
            Picture picture = new Picture(titles[i],images[i]); //使用标题和图像生成Picture对象
            pictures.add(picture);                          //将Picture对象添加到泛型集合中
        }
    }

    @Override
    public int getCount() {                             //获取泛型集合的长度
        if (null != pictures){                          //如果泛型集合不为空
            return pictures.size();                     //返回泛型长度
        }
        else {
            return 0;                                   //返回0
        }
    }

    @Override
    public Object getItem(int i) {
        return pictures.get(i);                 //获取泛型集合指定索引处的项
    }

    @Override
    public long getItemId(int i) {
        return i;                                  //返回泛型集合的索引
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;                  //创建ViewHolder对象
        if (view == null){                      //判断图像识别是否为空
            view  = inflater.inflate(R.layout.item_gv,null);        //设置图像标识
            viewHolder = new ViewHolder();      //初始化ViewHolder对象
            viewHolder.title = (TextView)view.findViewById(R.id.txv_itemtitle);//设置图像标题
            viewHolder.image = (ImageView)view.findViewById(R.id.img_itemimage);//设置图像的二进制值
            view.setTag(viewHolder);                         //设置提示
        }else{
            viewHolder = (ViewHolder)view.getTag();         //设置提示
        }
        viewHolder.title.setText(pictures.get(i).getTitle());       //设置图像标题
        viewHolder.image.setImageResource(pictures.get(i).getImageId());//设置图像的二进制值

        return view;                    //返回图像标识
    }

    public class ViewHolder {
        public TextView title;              //创建TextView类
        public ImageView image;             //创建ImageView对象
    }
}
