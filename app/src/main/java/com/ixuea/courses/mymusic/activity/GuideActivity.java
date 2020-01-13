package com.ixuea.courses.mymusic.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.viewpager.widget.ViewPager;

import com.ixuea.courses.mymusic.MainActivity;
import com.ixuea.courses.mymusic.R;
import com.ixuea.courses.mymusic.adapter.GuideFragmentAdapter;
import com.ixuea.courses.mymusic.fragment.GuideFragment;
import com.ixuea.courses.mymusic.util.PreferenceUtil;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class GuideActivity extends BaseCommonActivity implements View.OnClickListener {

    private Button bt_login_or_register;
    private Button bt_enter;
    private ViewPager viewPager;
    private GuideFragmentAdapter adapter;
    private CircleIndicator ci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

    @Override
    protected void initViews() {
        super.initViews();
        //隐藏状态栏
        hideStatusBar();
        bt_login_or_register = (Button) findViewById(R.id.bt_login_or_register);
        bt_enter = (Button) findViewById(R.id.bt_enter);
        viewPager = findViewById(R.id.vp);
        ci = findViewById(R.id.ci);

        bt_login_or_register.setOnClickListener(this);
        bt_enter.setOnClickListener(this);

    }

    @Override
    protected void initDatum() {
        super.initDatum();

        //给ViewPager设置数据
        adapter = new GuideFragmentAdapter(getSupportFragmentManager());
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.drawable.guide1);
        integers.add(R.drawable.guide2);
        integers.add(R.drawable.guide3);
        integers.add(R.drawable.guide4);
        integers.add(R.drawable.guide5);
        adapter.setDatum(integers);
        viewPager.setAdapter(adapter);

        ci.setViewPager(viewPager);

        adapter.registerDataSetObserver(ci.getDataSetObserver());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_or_register:
                setShowGuide();
                startActivityAfterFinishThis(LoginOrRegisterActivity.class);
                break;
            case R.id.bt_enter:
                setShowGuide();
                startActivityAfterFinishThis(MainActivity.class);
                break;
        }
    }

    //改变引导界面查看状态
    public void setShowGuide(){
        sp.getInstance(getMainActivity()).setShowGuide(false);
    }

}
