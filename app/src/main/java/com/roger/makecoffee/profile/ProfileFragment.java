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
import com.roger.makecoffee.objects.define.NewArticle;
import com.roger.makecoffee.user.UserManager;

import de.hdodenhof.circleimageview.CircleImageView;

import java.util.ArrayList;

public class ProfileFragment extends Fragment implements ProfileContract.View {
    private HistoryArticlesAdapter mAdapter;
    private ProfileContract.Presenter mPresenter;
    private ArrayList<NewArticle> mArticlesList;

    public ProfileFragment() {
        mArticlesList = new ArrayList<>();
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mPresenter = new ProfilePresenter(this);

        CircleImageView userImage = view.findViewById(R.id.circleImageView_profile_user_picture);
        TextView userName = view.findViewById(R.id.textView_profile_user_name);
        TextView userEmail = view.findViewById(R.id.textView_profile_user_email);
        Glide.with(this)
                .load(UserManager.getInstance().getUserPhotoUrl())
                .into(userImage);
        userName.setText(UserManager.getInstance().getUserName());
        userEmail.setText(UserManager.getInstance().getUserEmail());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_profile_history_articles);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        mAdapter = new HistoryArticlesAdapter(this, mArticlesList);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(new ArticlesListDecoration(
                getResources().getDimensionPixelSize(R.dimen.items_space)));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getArticlesDataFromFireStore(mArticlesList);
    }

    @Override
    public void notifyAdapterDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
