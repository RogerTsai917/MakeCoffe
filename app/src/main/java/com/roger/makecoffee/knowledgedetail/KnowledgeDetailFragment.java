package com.roger.makecoffee.knowledgedetail;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.roger.makecoffee.R;
import com.roger.makecoffee.adapter.KnowledgeDetailAdapter;
import com.roger.makecoffee.decoration.KnowledgeItemDecoration;
import com.roger.makecoffee.makecoffeeactivity.MakeCoffeeActivity;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;

public class KnowledgeDetailFragment extends Fragment {
    private ImageView mToolbarBackImage;
    private CoffeeKnowledgeCollection mCollection;

    public KnowledgeDetailFragment() {

    }

    public static KnowledgeDetailFragment newInstance(CoffeeKnowledgeCollection collection) {
        KnowledgeDetailFragment fragment = new KnowledgeDetailFragment();
        fragment.setKnowledgeCollection(collection);
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
        View view = inflater.inflate(R.layout.fragment_knowledge_detail, container, false);

        mToolbarBackImage = view.findViewById(R.id.imageView_knowledge_detail_back);

        TextView toolbarTitle = view.findViewById(R.id.textView_knowledge_detail_title);
        toolbarTitle.setText(mCollection.getName());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_knowledge_detail);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);

        KnowledgeDetailAdapter adapter = new KnowledgeDetailAdapter(this, mCollection);

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new KnowledgeItemDecoration(getResources().getDimensionPixelSize(R.dimen.items_space)));


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbarBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MakeCoffeeActivity)getActivity()).showToolbarAndNavBottom();
    }

    public void setKnowledgeCollection(CoffeeKnowledgeCollection collection) {
        mCollection = collection;
    }
}
