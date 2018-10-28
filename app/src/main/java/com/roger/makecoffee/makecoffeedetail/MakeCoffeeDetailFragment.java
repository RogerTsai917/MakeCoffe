package com.roger.makecoffee.makecoffeedetail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.MakeCoffeeDetailAdapter;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.define.MakeCoffeeTeaching;

public class MakeCoffeeDetailFragment extends Fragment implements MakeCoffeeDetailContract.View {
    private MakeCoffeeTeaching mTeaching;
    private RecyclerView mRecyclerView;
    private MakeCoffeeDetailAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private Button mFirstNextStepButton;
    private Button mPreviousStepButton;
    private Button mNextStepButton;
    private Button mCompletedStepButton;

    public MakeCoffeeDetailFragment() {

    }

    public static MakeCoffeeDetailFragment newInstance(MakeCoffeeTeaching teaching) {
        MakeCoffeeDetailFragment fragment = new MakeCoffeeDetailFragment();
        fragment.setMakeCoffeeTeaching(teaching);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MakeCoffeeActivity)getActivity()).hideToolbarAndNavBottom();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_coffee_detail, container, false);

        mFirstNextStepButton = view.findViewById(R.id.button_make_coffee_first_next);
        mPreviousStepButton = view.findViewById(R.id.button_make_coffee_previous);
        mNextStepButton = view.findViewById(R.id.button_make_coffee_next);
        mCompletedStepButton = view.findViewById(R.id.button_make_coffee_completed);

        mFirstNextStepButton.setOnClickListener(onClickListener);
        mPreviousStepButton.setOnClickListener(onClickListener);
        mNextStepButton.setOnClickListener(onClickListener);
        mCompletedStepButton.setOnClickListener(onClickListener);

        mRecyclerView = view.findViewById(R.id.recyclerView_make_coffee_detail);

        mLinearLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new MakeCoffeeDetailAdapter(this, mTeaching);

        mRecyclerView.setAdapter(mAdapter);

        new PagerSnapHelper().attachToRecyclerView(mRecyclerView);

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_make_coffee_first_next:
                    mRecyclerView.smoothScrollToPosition(mLinearLayoutManager.findFirstVisibleItemPosition() + 1);
                    break;

                case R.id.button_make_coffee_previous:
                    mRecyclerView.smoothScrollToPosition(mLinearLayoutManager.findFirstVisibleItemPosition() - 1);
                    break;

                case R.id.button_make_coffee_next:
                    mRecyclerView.smoothScrollToPosition(mLinearLayoutManager.findFirstVisibleItemPosition() + 1);
                    break;

                case R.id.button_make_coffee_completed:
                    ((MakeCoffeeActivity)getActivity()).transToMakeCoffee();
                    break;

                default:
            }
        }
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int position = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (position == mTeaching.getMakeCoffeeStepsArrayList().size() - 1) {
                    showCompletedButton();
                } else if (position == 0) {
                    showFirstNextStepButton();
                } else {
                    showPreviousAndNextButton();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.destroyTimer();
        ((MakeCoffeeActivity)getActivity()).showToolbarAndNavBottom();
    }

    public void setMakeCoffeeTeaching(MakeCoffeeTeaching teaching) {
        mTeaching = teaching;
    }

    public void showFirstNextStepButton() {
        mFirstNextStepButton.setVisibility(View.VISIBLE);
        mPreviousStepButton.setVisibility(View.GONE);
        mNextStepButton.setVisibility(View.GONE);
        mCompletedStepButton.setVisibility(View.GONE);
    }

    public void showPreviousAndNextButton() {
        mFirstNextStepButton.setVisibility(View.GONE);
        mPreviousStepButton.setVisibility(View.VISIBLE);
        mNextStepButton.setVisibility(View.VISIBLE);
        mCompletedStepButton.setVisibility(View.GONE);
    }

    public void showCompletedButton() {
        mFirstNextStepButton.setVisibility(View.GONE);
        mPreviousStepButton.setVisibility(View.GONE);
        mNextStepButton.setVisibility(View.GONE);
        mCompletedStepButton.setVisibility(View.VISIBLE);
    }


    @Override
    public void setPresenter(MakeCoffeeDetailContract.Presenter presenter) {

    }
}
