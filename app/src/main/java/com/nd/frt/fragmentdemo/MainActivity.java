package com.nd.frt.fragmentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.nd.frt.fragmentdemo.fragment.ContainerActivity;
import com.nd.frt.fragmentdemo.fragment.DetailFragment;
import com.nd.frt.fragmentdemo.fragment.ListFragment;
import com.nd.frt.fragmentdemo.model.UserInfo;

public class MainActivity extends AppCompatActivity implements DetailFragment.OnEditUserInfo {

    private ListFragment mListFragment;
    private ImageView mImgFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListFragment = new ListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, mListFragment)
                .commit();
        mImgFragment =(ImageView) findViewById(R.id.ivAvatar);
    }
    private void setmImgFragment(){
        View.OnLongClickListener onClick = null;
        mImgFragment.setOnLongClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.ivAvatar:
                intent = new Intent(MainActivity.this, ContainerActivity.class);
                break;
            }
            startActivity(intent);
        }
    }


    @Override
    public void onSuccess(UserInfo userInfo, int index) {
        mListFragment.edit(userInfo, index);
    }
}
