package com.nd.frt.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.adapter.UserAdapter;
import com.nd.frt.fragmentdemo.model.UserInfo;
import com.nd.frt.fragmentdemo.service.UserInfoService;

import java.util.List;

public class ListFragment extends Fragment {

    private UserAdapter mUserAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UserInfoService userInfoService = new UserInfoService();
        List<UserInfo> userInfos = userInfoService.getUserInfos(getContext());
        mUserAdapter = new UserAdapter(userInfos);
        View view = getView();
        assert view != null;
        RecyclerView recyclerView = view.findViewById(R.id.rvRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mUserAdapter);
    }

    public void edit(UserInfo userInfo, int index) {
        mUserAdapter.edit(userInfo, index);
    }
}
