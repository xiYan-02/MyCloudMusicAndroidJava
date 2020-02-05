package com.ixuea.courses.mymusic.activity;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.ixuea.courses.mymusic.R;

import butterknife.BindView;

public class BaseTitleActivity extends BaseCommonActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void initViews() {
        super.initViews();

        //初始化Toolbar
        setSupportActionBar(toolbar);

        //是否显示返回按钮
        if(isShowBackMenu()){
            showBackMenu();
        }

    }

    /**
     * 显示返回按钮
     */
    private void showBackMenu() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 是否显示返回按钮
     */
    protected boolean isShowBackMenu() {
        return true;
    }


    /**
     * 按钮点击回调事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
