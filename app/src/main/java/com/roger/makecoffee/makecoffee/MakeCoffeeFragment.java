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
    private MakeCoffeeContract.Presenter mpresenter;
    private RecyclerView mRecyclerView;
    private MakeCoffeeAdapter mMakeCoffeeAdapter;

    public MakeCoffeeFragment() {

    }

    public static MakeCoffeeFragment newInstance() {
        return new MakeCoffeeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_coffee, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView_make_coffee);

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mMakeCoffeeAdapter = new MakeCoffeeAdapter(this);
        mRecyclerView.setAdapter(mMakeCoffeeAdapter);

        mRecyclerView.addItemDecoration(new MakeCoffeeItemDecoration(2,
                getResources().getDimensionPixelSize(R.dimen.make_coffee_item_space),
                true));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void setPresenter(MakeCoffeeContract.Presenter presenter) {
        mpresenter = presenter;
    }
}
