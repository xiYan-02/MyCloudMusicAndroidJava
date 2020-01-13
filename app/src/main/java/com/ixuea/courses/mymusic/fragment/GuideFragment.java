package com.ixuea.courses.mymusic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.activity.BaseCommonActivity;
import com.ixuea.courses.mymusic.util.Constant;

public class GuideFragment extends BaseCommonFragment {

    private ImageView imageView;

    public GuideFragment() {
        // Required empty public constructor
    }

    public static GuideFragment newInstance(int imgId) {
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.ID,imgId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews() {
        super.initViews();
        imageView = findViewById(R.id.iv);
    }

    @Override
    protected void initDatum() {
        super.initDatum();
        int imgId = getArguments().getInt(Constant.ID,-1);
        //如果图片径路不存在则关闭当前的Activity
        if(imgId == -1){
            getActivity().finish();
            return;
        }
        imageView.setImageResource(imgId);
    }

    @Override
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide,container,false);
    }
}
