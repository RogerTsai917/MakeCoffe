package com.roger.makecoffee.following;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;

public class FollowingFragment extends Fragment {

    public FollowingFragment() {

    }

    public static FollowingFragment newInstance() {
        return new FollowingFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_following, container, false);


        return view;
    }
}
