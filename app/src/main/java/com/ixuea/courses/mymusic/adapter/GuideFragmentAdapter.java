package com.ixuea.courses.mymusic.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ixuea.courses.mymusic.fragment.GuideFragment;

import java.util.ArrayList;
import java.util.List;

public class GuideFragmentAdapter extends BaseFragmentAdapter<Integer> {

    public GuideFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return GuideFragment.newInstance(getData(position));
    }


}
