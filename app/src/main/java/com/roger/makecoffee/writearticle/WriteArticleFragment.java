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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.NewWriteArticleDetailAdapter;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.utils.Constants;

public class WriteArticleFragment extends Fragment implements WriteArticleContract.View, View.OnClickListener {
    RecyclerView mRecyclerView;
    NewWriteArticleDetailAdapter mAdapter;
    Button mPostArticleButton;
    private WriteArticleContract.Presenter mPresenter;
    private AlertDialog mUploadingDialog;
    private AlertDialog mChangeCoffeeFlavorDialog;

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

        mAdapter = new NewWriteArticleDetailAdapter(this);

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
                mPresenter.postArticle(mAdapter.getNewArticle());
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
        mUploadingDialog = builder.create();
        View dialogView = View.inflate(getContext(), R.layout.dialog_uploading_article, null);

        mUploadingDialog.setView(dialogView);
        mUploadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mUploadingDialog.show();
    }

    @Override
    public void hideUploadingDialog() {
        mUploadingDialog.hide();
    }

    @Override
    public void showChangeCoffeeFlavorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        mChangeCoffeeFlavorDialog = builder.create();
        View dialogView = View.inflate(getContext(), R.layout.dialog_choose_coffee_flavor, null);

        Spinner flavorBodySpinner = dialogView.findViewById(R.id.spinner_coffee_flavor_body);
        Spinner flavorAciditySpinner = dialogView.findViewById(R.id.spinner_coffee_flavor_acidity);
        Spinner flavorBitterSpinner = dialogView.findViewById(R.id.spinner_coffee_flavor_bitter);
        Spinner flavorSweetSpinner = dialogView.findViewById(R.id.spinner_coffee_flavor_sweet);
        Spinner flavorAromaSpinner = dialogView.findViewById(R.id.spinner_coffee_flavor_aroma);

        Button confirmButton = dialogView.findViewById(R.id.button_coffee_flavor_confirm);
        Button cancelButton = dialogView.findViewById(R.id.button_coffee_flavor_cancel);

        final int[] tempLevel = {0, 0, 0, 0, 0};
        final String[] level = {"0", "1", "2", "3", "4", "5"};
        ArrayAdapter<String> levelList = new ArrayAdapter<>
                (getContext(), R.layout.item_simple_spinner, level);

        flavorBodySpinner.setAdapter(levelList);
        flavorAciditySpinner.setAdapter(levelList);
        flavorBitterSpinner.setAdapter(levelList);
        flavorSweetSpinner.setAdapter(levelList);
        flavorAromaSpinner.setAdapter(levelList);

        flavorBodySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempLevel[0] = Integer.parseInt(level[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        flavorAciditySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempLevel[1] = Integer.parseInt(level[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        flavorBitterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempLevel[2] = Integer.parseInt(level[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        flavorSweetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempLevel[3] = Integer.parseInt(level[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        flavorAromaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tempLevel[4] = Integer.parseInt(level[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.refreshBarChartData(tempLevel);
                mChangeCoffeeFlavorDialog.hide();
                mAdapter.refreshBarChart();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeCoffeeFlavorDialog.hide();
            }
        });

        mChangeCoffeeFlavorDialog.setView(dialogView);
        mChangeCoffeeFlavorDialog.show();
    }

    @Override
    public void hideChangeCoffeeFlavorDialog() {
        mChangeCoffeeFlavorDialog.hide();
    }

    @Override
    public void backPress() {
        getActivity().onBackPressed();
    }

    public void scrollTORecyclerViewBottom() {

    }

    public void notifyAdapterDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    public void getImageFromAlbum() {
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

            mAdapter.getNewArticle().setImageUrl(uriToString);

            mAdapter.notifyDataSetChanged();
        }
    }
}
