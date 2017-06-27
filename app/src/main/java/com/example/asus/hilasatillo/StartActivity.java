package com.example.asus.hilasatillo;

import android.app.FragmentManager;
import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    String date, name, amount;
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        getSupportActionBar().hide();
        myDb = new DatabaseHelper(this);

        TabLayout tab = (TabLayout)findViewById(R.id.tab);

        tab.addTab(tab.newTab().setText("Attendance"));
        tab.addTab(tab.newTab().setText("Duwa"));

        fm = getFragmentManager();

        fm.beginTransaction().add(new Attendance_Fragment(),"Attendance").commit();
        fm.beginTransaction().replace(R.id.frame,new Attendance_Fragment()).commit();
        fm.beginTransaction().add(new Duwa_Fragment(),"Classes").commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    fm.beginTransaction().replace(R.id.frame,new Attendance_Fragment()).commit();
                }else{
                    fm.beginTransaction().replace(R.id.frame,new Duwa_Fragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        temp();
        Toast.makeText(this, " "+name+" "+date+" "+amount , Toast.LENGTH_SHORT).show();
    }
    public void temp(){
        Cursor res = myDb.temp();

        while(res.moveToNext()) {
            name = res.getString(1);
            date = res.getString(2).toString();
            amount = res.getString(3).toString();
        }
    }
}
