package com.roger.makecoffee.writearticle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.WriteArticleDetailAdapter;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.utils.Constants;

public class WriteArticleFragment extends Fragment implements WriteArticleContract.View, View.OnClickListener {
    RecyclerView mRecyclerView;
    WriteArticleDetailAdapter mAdapter;
    Button mPostArticleButton;
    private WriteArticleContract.Presenter mPresenter;
    private int imagePosition;
    private AlertDialog mDialog;

    public WriteArticleFragment() {

    }

    public static WriteArticleFragment newInstance() {
        WriteArticleFragment fragment = new WriteArticleFragment();

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
        View view = inflater.inflate(R.layout.fragment_write_article, container, false);

        mPostArticleButton = view.findViewById(R.id.button_write_article_post);
        mPostArticleButton.setOnClickListener(this);

        mRecyclerView = view.findViewById(R.id.recyclerView_write_article_detail);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new WriteArticleDetailAdapter(this);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new WriteArticlePresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MakeCoffeeActivity)getActivity()).showToolbarAndNavBottom();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_write_article_post:
                mPresenter.postArticle(mAdapter.getArticle());
                break;

            default:
        }
    }

    @Override
    public void setPresenter(WriteArticleContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Activity returnActivity() {
        return getActivity();
    }

    @Override
    public void showUploadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        mDialog = builder.create();
        View dialogView = View.inflate(getContext(), R.layout.dialog_uploading_article, null);

        mDialog.setView(dialogView);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.show();
    }

    @Override
    public void hideUploadingDialog() {
        mDialog.hide();
    }

    @Override
    public void backPress() {
        getActivity().onBackPressed();
    }

    public void scrollTORecyclerViewBottom() {
        mRecyclerView.smoothScrollToPosition(mAdapter.getRecyclerViewSize() - 1);
    }

    public void notifyAdapterDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public void getImageFromAlbum(int position) {
        imagePosition = position;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Constants.GET_IMAGE_FROM_ALBUM);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.GET_IMAGE_FROM_ALBUM && data != null && data.getData() != null) {
            String uriToString = data.getData().toString();

            if (imagePosition == 0) {
                mAdapter.getArticle().setImageUrl(uriToString);
            }   else {
                mAdapter.getArticle().getArticleStepArrayList()
                        .get(imagePosition - 1).setPhotoUrl(uriToString);
            }

            mAdapter.notifyDataSetChanged();
        }
    }
}
