package com.ixuea.courses.mymusic.fragment;

import android.content.Context;
import android.content.Intent;

import com.ixuea.courses.mymusic.activity.BaseCommonActivity;
import com.ixuea.courses.mymusic.util.PreferenceUtil;

public abstract class BaseCommonFragment extends BaseFragment {

    private PreferenceUtil sp;

    @Override
    protected void initDatum() {
        super.initDatum();
        sp = PreferenceUtil.getInstance(getMainActivity());
    }

    protected void startActivity(Class<?> clazz){
        startActivity(new Intent(getMainActivity(),clazz));
    }

    protected void startActivityAfterFinishThis(Class<?> clazz){
        startActivity(new Intent(getMainActivity(),clazz));
        getMainActivity().finish();
    }

    private BaseCommonActivity getMainActivity() {
        return (BaseCommonActivity) getActivity();
    }
}
