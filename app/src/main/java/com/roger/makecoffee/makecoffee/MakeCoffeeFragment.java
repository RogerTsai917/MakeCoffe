package com.roger.makecoffee.makecoffee;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.MakeCoffeeAdapter;
import com.roger.makecoffee.decoration.MakeCoffeeItemDecoration;

public class MakeCoffeeFragment extends Fragment implements MakeCoffeeContract.View {

    public MakeCoffeeFragment() {

    }

    public static MakeCoffeeFragment newInstance() {
        return new MakeCoffeeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_coffee, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_make_coffee);

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        MakeCoffeeAdapter makeCoffeeAdapter = new MakeCoffeeAdapter(this);
        recyclerView.setAdapter(makeCoffeeAdapter);

        recyclerView.addItemDecoration(new MakeCoffeeItemDecoration(2,
                getResources().getDimensionPixelSize(R.dimen.make_coffee_items_space),
                true));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void setPresenter(MakeCoffeeContract.Presenter presenter) {

    }

}
