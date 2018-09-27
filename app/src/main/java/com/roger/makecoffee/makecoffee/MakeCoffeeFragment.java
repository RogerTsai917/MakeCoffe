package com.roger.makecoffee.makecoffee;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;

public class MakeCoffeeFragment extends Fragment {

    public MakeCoffeeFragment() {

    }

    public static MakeCoffeeFragment newInstance() {
        return new MakeCoffeeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_coffee, container, false);

        return view;
    }
}
