package com.jason.test.testapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.jason.test.testapp.R;
import com.jason.test.testapp.data.event.SubEventOne;
import com.jason.test.testapp.data.event.SubEventTwo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by JiaBo on 2017/10/10.
 */

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third);
        findViewById(R.id.main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "send SubEventOne", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new SubEventOne().setSubName("SubEventOne"));
            }
        });
        findViewById(R.id.main_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThirdActivity.this, "send SubEventTwo", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new SubEventTwo().setSubName("SubEventTwo"));
            }
        });
    }

}
