package com.example.asus.hilasatillo;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Attendance_Fragment extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    TextView bayranan;
    TextView pila;
    Button button;
    EditText pangan;
    ArrayList<PersonModel> personList;
    DatabaseHelper myDb;
     RecyclerView listView;


    int amount;
    int playerCount = 0;

    public Attendance_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_attendance_, container, false);
        personList = new ArrayList<>();
        myDb = new DatabaseHelper(getActivity());

        listView = (RecyclerView)view.findViewById(R.id.listView);
        bayranan = (TextView)view.findViewById(R.id.bayranan);
        pila = (TextView)view.findViewById(R.id.numOfPlayers);
        pangan = (EditText)view.findViewById(R.id.player);
        button = (Button)view.findViewById(R.id.but);

        initList(view);
        getSessionID();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = pangan.getText().toString();
                getSessionID();
                int pilaD = amount/(++playerCount);
                Boolean stat = myDb.addPlayer(name,pilaD);
                pila.setText(playerCount+"");


                if(stat == true){
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    Log.e("kobe","pila ="+pila);
                    Log.e("kobe","count ="+playerCount);
                    Log.e("kobe","amuont ="+amount);
                    myDb.updateBayranan(pilaD);


                    listView.setAdapter(null);
                    personList.clear();

                    initList(view);

                }else{
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void initList(View view){

        getPlayers();
        layoutManager = new LinearLayoutManager(view.getContext());
        listView.setLayoutManager(layoutManager);
        adapter = new Attendance_Adapter(personList,view.getContext());
        listView.setAdapter(adapter);
        pila.setText(playerCount+"");


    }

    public void getPlayers(){
    playerCount = 0;
    Cursor res = myDb.getPlayers();
        while (res.moveToNext()){
            String name = res.getString(1);
            int pay = Integer.parseInt(res.getString(2));
            String status = res.getString(3);
            PersonModel person = new PersonModel(name,pay,status);

            personList.add(person);
            playerCount++;
            Log.e("kobe","player count sa cursor = "+playerCount);
        }

    }

    public void getSessionID(){

      Cursor res =   myDb.getSession(getDate());
        while(res.moveToNext()){
           amount  = Integer.parseInt(res.getString(3));
            bayranan.setText(amount+"");
          //  playerCount = Integer.parseInt(res.getString(4));
        }

    }

    public String getDate(){
        Calendar calendar = Calendar.getInstance();
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        int y = calendar.get(Calendar.YEAR);

        String [] dates = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"
        };

        String month = dates[m];
        String fullDate = month + "-" + d + "-" + y;

        return fullDate;
    }
}
