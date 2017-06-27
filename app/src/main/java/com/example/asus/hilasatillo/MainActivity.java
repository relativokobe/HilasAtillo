package com.example.asus.hilasatillo;

import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button nayDuwa;
    DatabaseHelper myDb;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        nayDuwa = (Button)findViewById(R.id.nayDuwa);
        //myDb.deleteTemp();
       // myDb.nayduwaUpdate();
        currentStatus();

      nayDuwa.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              FragmentManager fragmentManager = getFragmentManager();
              NaayDuwa nayduwa = new NaayDuwa();
              nayduwa.show(fragmentManager,"nayduwa");
          }
      });

    }

    public String status(){
        String status = "";
        Cursor res = myDb.getStatus();
        if(res.getCount() == 0){
            Log.e("kobe","0 daw");
        }
        else {
            Log.e("kobe","res sa else = "+res.getCount());
            while(res.moveToNext()) {
                status = res.getString(0);
                Log.e("kobe","status function = "+res.getString(0));
            }
        }
        return status;
    }

    public void currentStatus(){

        Log.e("kobe","status here ="+status());

        if(status().equals("Naa")){

        }else{
            Log.e("kobe","current Status Method");
            Intent intent = new Intent(this,StartActivity.class);
            startActivity(intent);
        }
    }

    public void temp(){
        myDb.temp();
    }


}
