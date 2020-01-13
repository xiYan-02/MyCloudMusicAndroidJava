package com.ixuea.courses.mymusic.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用FragmentPagerAdapter
 * 主要是创建了列表
 * 实现了通用的方法
 * 直接用FragmentPagerAdapter有可能有内存泄漏
 * 所以使用FragmentStatePagerAdapter
 */
public abstract class BaseFragmentAdapter<T> extends FragmentStatePagerAdapter {

    private List<T> datum = new ArrayList<T>();

    public BaseFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return datum.size();
    }

    public void setDatum(List<T> datum){
        if(datum != null && datum.size() > 0){
            this.datum.clear();
            this.datum.addAll(datum);
        }
        notifyDataSetChanged();
    }

    public T getData(int position){
        return datum.get(position);
    }

}
