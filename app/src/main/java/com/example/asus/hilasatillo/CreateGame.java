package com.example.asus.hilasatillo;


import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateGame extends DialogFragment implements ListViewFragment.OnCompleteListener{

    Button Team1_player1, Team1_player2, Team1_player3, Team1_player4, Team1_player5;
    Button Team2_player1, Team2_player2, Team2_player3, Team2_player4, Team2_player5;
    Button ok;
    ArrayList<PersonModel> personList;
    DatabaseHelper myDb;


    Context cont;


    public CreateGame() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_game, container, false);
        personList = new ArrayList<>();
        myDb = new DatabaseHelper(getActivity());
        initButtons(view);
        getPlayers();
        cont = view.getContext();

        return view;
    }

    public void initButtons(View view){
        Team1_player1 = (Button)view.findViewById(R.id.team1_player1);
        Team1_player2 = (Button)view.findViewById(R.id.team1_player2);
        Team1_player3 = (Button)view.findViewById(R.id.team1_player3);
        Team1_player4 = (Button)view.findViewById(R.id.team1_player4);
        Team1_player5 = (Button)view.findViewById(R.id.team1_player5);
        Team2_player1 = (Button)view.findViewById(R.id.team2_player1);
        Team2_player2 = (Button)view.findViewById(R.id.team2_player2);
        Team2_player3 = (Button)view.findViewById(R.id.team2_player3);
        Team2_player4 = (Button)view.findViewById(R.id.team2_player4);
        Team2_player5 = (Button)view.findViewById(R.id.team2_player5);
        ok = (Button)view.findViewById(R.id.start);

        Team1_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             showDialog(R.id.team1_player1);

            }
        });

        Team1_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team1_player2);

            }
        });


        Team1_player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team1_player3);
            }
        });

        Team1_player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team1_player4);
            }
        });

        Team1_player5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team1_player5);
            }
        });

        Team2_player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team2_player1);
            }
        });

        Team2_player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team2_player2);;
            }
        });

        Team2_player3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team2_player3);
            }
        });

        Team2_player4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team2_player4);
            }
        });

        Team2_player5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.id.team2_player5);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String team1player1 = Team1_player1.getText().toString();
                String team1player2 = Team1_player2.getText().toString();
                String team1player3 = Team1_player3.getText().toString();
                String team1player4 = Team1_player4.getText().toString();
                String team1player5 = Team1_player5.getText().toString();

                String team2player1 = Team2_player1.getText().toString();
                String team2player2 = Team2_player2.getText().toString();
                String team2player3 = Team2_player3.getText().toString();
                String team2player4 = Team2_player4.getText().toString();
                String team2player5 = Team2_player5.getText().toString();

                long team1, team2;


                team1 = myDb.addPlayersToTeam(team1player1,team1player2,team1player3,team1player4,team1player5);
                team2 = myDb.addPlayersToTeam(team2player1,team2player2,team2player3,team2player4,team2player5);

                myDb.addGame(team1,team2,"Playing",999);
                dismiss();


            }
        });

    }
    public interface gamestart{
        public void goGame();
    }
    public void showDialog(int buttonInt){
        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();

        ListViewFragment listViewFragment = new ListViewFragment();
        listViewFragment.setTargetFragment(this,0);
        bundle.putParcelableArrayList("list",personList);
        bundle.putInt("button",buttonInt);
        listViewFragment.setArguments(bundle);
        listViewFragment.show(fragmentManager,"List");

    }
    public void getPlayers(){

        Cursor res = myDb.getPlayers();
        while (res.moveToNext()){
            String name = res.getString(1);
            int pay = Integer.parseInt(res.getString(2));
            String status = res.getString(3);
            PersonModel person = new PersonModel(name,pay,status);

            personList.add(person);

            Log.e("kobe","player count sa cursor = ");
        }

    }

    @Override
    public void onComplete(int button, String name) {
        Log.e("kyle","but = "+button+"button para set text"+R.id.team1_player1+"name   = "+name);
      switch (button){
          case R.id.team1_player1: Team1_player1.setText(name); break;
          case R.id.team1_player2: Team1_player2.setText(name); break;
          case R.id.team1_player3: Team1_player3.setText(name); break;
          case R.id.team1_player4: Team1_player4.setText(name); break;
          case R.id.team1_player5: Team1_player5.setText(name); break;
          case R.id.team2_player1: Team2_player1.setText(name); break;
          case R.id.team2_player2: Team2_player2.setText(name); break;
          case R.id.team2_player3: Team2_player3.setText(name); break;
          case R.id.team2_player4: Team2_player4.setText(name); break;
          case R.id.team2_player5: Team2_player5.setText(name); break;

      }
    }


  /*  @Override
    public void onClick(String name, int id) {
     *//*   switch(id){
            case R.id.team1_player1: Team1_player1.setText(name);
        }*//*
     Log.e("kobe",name+" button = "+id);
    }*/


}
