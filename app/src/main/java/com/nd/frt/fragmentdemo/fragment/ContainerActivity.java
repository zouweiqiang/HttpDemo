package com.nd.frt.fragmentdemo.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nd.frt.fragmentdemo.R;

public class ContainerActivity extends AppCompatActivity {
    private AFragment aFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        //实例化AFrangment
        aFragment =AFragment.newInstance("我是参数");
        //把AFragmeent添加到Activity中
        getFragmentManager().beginTransaction().add(R.id.fl_container,aFragment,"a").commitAllowingStateLoss();
    }
}
