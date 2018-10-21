package com.roger.makecoffee.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.HistoryArticlesAdapter;
import com.roger.makecoffee.decoration.ArticlesListDecoration;
import com.roger.makecoffee.user.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private CircleImageView mUserImage;
    private TextView mUserName;
    private TextView mUserEmail;
    private RecyclerView mRecyclerView;
    private HistoryArticlesAdapter mAdapter;

    public ProfileFragment() {
        // Requires empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mUserImage = view.findViewById(R.id.circleImageView_profile_user_picture);
        mUserName = view.findViewById(R.id.textView_profile_user_name);
        mUserEmail = view.findViewById(R.id.textView_profile_user_email);
        Glide.with(this)
                .load(UserManager.getInstance().getUserPhotoUrl())
                .into(mUserImage);
        mUserName.setText(UserManager.getInstance().getUserName());
        mUserEmail.setText(UserManager.getInstance().getUserEmail());

        mRecyclerView = view.findViewById(R.id.recyclerView_profile_history_articles);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new HistoryArticlesAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new ArticlesListDecoration(
                getResources().getDimensionPixelSize(R.dimen.make_coffee_item_space)));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void notifyAdapterDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }
}
