package com.example.asus.hilasatillo;


import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Duwa_Fragment extends Fragment {

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView listView;
    FloatingActionButton fab;
    ArrayList<GameModel> gameModels;
    DatabaseHelper myDb;

    public Duwa_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_duwa_, container, false);
        myDb = new DatabaseHelper(getActivity());
        fab = (FloatingActionButton)view.findViewById(R.id.Fab);
        fab.show();
        temp();
        gameModels = new ArrayList<>();
        initGameList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                CreateGame createGame = new CreateGame();
                createGame.show(fragmentManager,"Create Game");
            }
        });
        listView = (RecyclerView)view.findViewById(R.id.listView);
        initGames(view);

        return view;
    }
    public void temp(){
        Cursor res = myDb.tempP();
        Log.e("kobe", "temp = assds "+res.getCount());
    }

    public void initGames(View view){
        layoutManager = new LinearLayoutManager(view.getContext());
        listView.setLayoutManager(layoutManager);
        adapter = new GameListViewFragment(gameModels);
        listView.setAdapter(adapter);
    }
    public void initGameList(){
        Cursor res = myDb.getGames();
        String team1player1="",team1player2="", team1player3="", team1player4="", team1player5="";
        String team2player1="",team2player2="", team2player3="", team2player4="", team2player5="";

        Log.e("kobe","res = "+res.getCount());

        while(res.moveToNext()){
            int duwaId = res.getInt(0);
            int team1Id = res.getInt(1);
            int team2Id = res.getInt(2);
            int winner = res.getInt(3);
            String status = res.getString(4);

            Cursor team1 = myDb.getTeam(team1Id);
            Cursor team2 = myDb.getTeam(team2Id);

            while(team1.moveToNext()){
                team1player1 = res.getString(0);
                team1player2 = res.getString(1);
                team1player3 = res.getString(2);
                team1player4 = res.getString(3);
                team1player5 = res.getString(4);
            }

            while(team2.moveToNext()){
                team2player1 = res.getString(0);
                team2player2 = res.getString(1);
                team2player3 = res.getString(2);
                team2player4 = res.getString(3);
                team2player5 = res.getString(4);
            }

            GameModel gameModel = new GameModel(team1player1, team1player2,team1player3,team1player4,team1player5,team2player1,
                    team2player2,team2player3,team2player4,team2player5,status,winner);

            gameModels.add(gameModel);
            Log.e("kobe","pisteng yawa = "+gameModel.getTeam1_player1()+"size = "+gameModels.size());


        }
    }

}
