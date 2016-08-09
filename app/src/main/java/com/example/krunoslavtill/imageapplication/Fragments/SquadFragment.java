package com.example.krunoslavtill.imageapplication.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krunoslavtill.imageapplication.Adapters.ImageAdapter;
import com.example.krunoslavtill.imageapplication.Models.Models.FragmentModels.SquadModel;
import com.example.krunoslavtill.imageapplication.Models.Models.ReadingListModels.Players;
import com.example.krunoslavtill.imageapplication.R;
import com.example.krunoslavtill.imageapplication.Utils.RecyclerItemClickListener;
import com.example.krunoslavtill.imageapplication.object.carriers.Registry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krunoslavtill on 04/08/16.
 */
public class SquadFragment extends android.support.v4.app.Fragment {
    List<Players> listOfAllPlayersAtributes = (List<Players>) Registry.getInstance().get("Players");
    List<String> playerPicturesUrl= new ArrayList<>();
    List<String> playerNames= new ArrayList<>();
    List<Integer> playersNumber= new ArrayList<>();

    String imageFramework;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context=getActivity().getApplicationContext();
        View view = inflater.inflate(R.layout.fragment_squad, container, false);



        SquadModel sm = (SquadModel) Registry.getInstance().get("SquadModel");
        RecyclerView mRecyclerView;
        mRecyclerView = (RecyclerView) view.findViewById(R.id.squad_recycle_view);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("EmilCl","kliknuo si broj"+position);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        //addPlayersAtributes();
        //ImageAdapter imageAdapter = new ImageAdapter(context,listOfAllPlayersAtributes,sm.getImageFramework());


        //mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(sm.getItemSpace()));
        // here i can manage how many columms i will have
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);

        ImageAdapter imageAdapter = new ImageAdapter(context,listOfAllPlayersAtributes,sm.getImageFramework(),layoutManager);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setAdapter(imageAdapter);

        return view;
    }
    private void addPlayersAtributes() {

        listOfAllPlayersAtributes = (List<Players>) Registry.getInstance().get("Players");
        // List<AllPlayerPictures> allPlayerPicturesList = (List<AllPlayerPictures>) Registry.getInstance().get("playerPictures");


        for (int i=0;i<listOfAllPlayersAtributes.size();i++){
            playerPicturesUrl.add(listOfAllPlayersAtributes.get(i).getPlayerPicture());
            playerNames.add(listOfAllPlayersAtributes.get(i).getPlayerName());
            playersNumber.add(listOfAllPlayersAtributes.get(i).getPlayerNumber());
        }
    }
}
