package com.daily.world.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daily.see.world.SeeTheWorldActivity;
import com.zjrb.core.utils.UIUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UIUtils.init(getApplication());
        startActivity(new Intent(this, SeeTheWorldActivity.class));
    }
}
