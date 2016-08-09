package com.example.krunoslavtill.imageapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.krunoslavtill.imageapplication.Adapters.ImageAdapter;
import com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels.SquadModel;
import com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels.Players;
import com.example.krunoslavtill.imageapplication.R;
import com.example.krunoslavtill.imageapplication.Utils.VerticalSpaceItemDecoration;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunoslavtill on 08/08/16.
 */
public class NewsFragment extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        SquadModel sm = (SquadModel) Registry.getInstance().get("SquadModel");
        // this position is for header in recycleview, but its -1 because i dont want to show it here
        int sectionPosition=-1;
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.galleryRecyclerView);

        Context context=getActivity().getApplicationContext();


        ImageAdapter imageAdapter = new ImageAdapter(context,sm.getPicturesList(),sm.getNewsURL(),sm.getImageFramework(),sectionPosition);

        //mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(sm.getItemSpace()));
        // here i can manage how many columms i will have
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(imageAdapter);

        return view;
    }


}
