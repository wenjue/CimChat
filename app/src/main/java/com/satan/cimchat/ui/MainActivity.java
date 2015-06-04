package com.satan.cimchat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.satan.cimchat.R;
import com.satan.cimchat.core.android.CIMPushManager;
import com.satan.cimchat.ui.fragment.AllFragment;
import com.satan.cimchat.ui.fragment.MyFragment;

public class MainActivity extends FragmentActivity {

    private TextView tvTitle;
    private RadioGroup rgTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        rgTab = (RadioGroup) findViewById(R.id.rg_tab);
        tvTitle.setText(getSharedPreferences("config", MODE_PRIVATE).getString("username", ""));
        switchContent(MyFragment.newInstance());
    }

    private void initListener() {
        rgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_my:
                        switchContent(MyFragment.newInstance());
                        break;

                    case R.id.rb_all:
                        switchContent(AllFragment.newInstance());
                        break;
                }
            }
        });
    }


    private void switchContent(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.ll_content, fragment);
        ft.commit();
    }


    @Override
    public void onBackPressed() {

        CIMPushManager.stop(this);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

}