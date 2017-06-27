package com.example.asus.hilasatillo;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class NaayDuwa extends DialogFragment {

 Button button;
 EditText nameOfGym;
 EditText amount;
    DatabaseHelper myDb;
    public NaayDuwa() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_naay_duwa, container, false);
        button = (Button)view.findViewById(R.id.ok);
        nameOfGym = (EditText) view.findViewById(R.id.Gym);
        amount = (EditText)view.findViewById(R.id.Amount);
        myDb = new DatabaseHelper(getActivity());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameOfGym.getText().toString();
                int vAmount = Integer.parseInt(amount.getText().toString());
                String date = getDate();
                myDb.naayDuwa(name,vAmount,date,0);
                myDb.nayduwaUpdate();



                Intent intent = new Intent(getActivity(),StartActivity.class);
                startActivity(intent);

                dismiss();
            }
        });

        return view;
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
