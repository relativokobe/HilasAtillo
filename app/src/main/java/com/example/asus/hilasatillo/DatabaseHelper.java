package com.example.asus.hilasatillo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by asus on 06/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Hilas.db";
    public static final String playerTable = "player_table";
    public static final String SessionDayInfo = "session_info_table";
    public static final String duwaInfo = "duwa_info_table";
    public static final String team = "team_table";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // SQLiteDatabase db = this.getWritableDatabase();

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table "+playerTable+" (Player_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "                                 NAME TEXT," +
            "                                 BAYRANAN INTEGER," +
                 "                            PAY_STATUS TEXT);");

         db.execSQL("create table "+SessionDayInfo+" (SESSION_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "                                     GYM_NAME TEXT," +
            "                                     DATE TEXT," +
            "                                     TOTAL_AMOUNT INTEGER," +
            "                                     PLAYER_COUNT INTEGER);");

         db.execSQL("create table "+duwaInfo+" (DUWA_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
             "                                TEAM1_ID INTEGER ," +
             "                                TEAM2_ID INTEGER ," +
             "                                WINNER_ID INTEGER," +
                 "                            STATUS TEXT, " +
                 "                            FOREIGN KEY( TEAM1_ID) REFERENCES team_table(TEAM_ID)," +
                 "                            FOREIGN KEY( TEAM1_ID) REFERENCES team_table(TEAM_ID));");

        db.execSQL("create table "+team+" (TEAM_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
             "                           PLAYER_ID1 INTEGER," +
                "PLAYER_ID2 INTEGER," +
                "PLAYER_ID3 INTEGER," +
                "PLAYER_ID4 INTEGER," +
                "PLAYER_ID5 INTEGER," +
                "                        FOREIGN KEY( PLAYER_ID) REFERENCES player_table(Player_ID));");

        db.execSQL("create table Status (ID TEXT PRIMARY KEY," +
                "                          STATUS_str TEXT);");

        db.execSQL("create table SessionAttendance (SESSION_ID INTEGER, " +
                "                                    Player_ID INTEGER," +
                "                                FOREIGN KEY(PLAYER_ID) REFERENCES player_table(Player_ID)," +
                "                                 FOREIGN KEY(SESSION_ID) REFERENCES session_info_table(SESSION_ID));");

      // setDefault(db);

    }

public boolean addPlayer(String name, int amount){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    cv.put("NAME",name);
    cv.put("BAYRANAN",amount);
    cv.put("PAY_STATUS","Wala");

    long result = db.insert(playerTable,null,cv);
    if(result == -1){
        return false;
    }else{
        return true;
    }

}
public Cursor getSession(String date){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    Cursor res = db.rawQuery("Select * from session_info_table Where DATE = '"+date+"'",null);

    return res;
}
  /*  public void setDefault(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STATUS_str","Naa");
        cv.put("ID","kobe");
        Log.e("kobe","set default");

        long result = db.insert("Status",null,cv);
        if(result == -1){
            Log.e("kobe","false");
        }else{
            Log.e("kobe","success");
        }
    }
*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(db);
        if(oldVersion == 1 && newVersion == 2){
            db.execSQL("DROP TABLE IF EXISTS"+SessionDayInfo);
            db.execSQL("DROP TABLE IF EXISTS"+duwaInfo);
            db.execSQL("DROP TABLE IF EXISTS Status");
        }
    }

    public Cursor getStatus(){
     SQLiteDatabase db = this.getWritableDatabase();
     Cursor res = db.rawQuery("Select STATUS_str from Status",null);

     return res;
    }
    public Cursor getTeam(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select PLAYER_ID1, PLAYER_ID2, PLAYER_ID3, PLAYER_ID4, PLAYER_ID5 from "+team+" where TEAM_ID = '"+id+"';",null);

        return res;
    }

    public boolean naayDuwa(String gymName, int Amount, String date, int player_count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("GYM_NAME",gymName);
        cv.put("TOTAL_AMOUNT",Amount);
        cv.put("PLAYER_COUNT",player_count);
        cv.put("DATE",date);

        long result = db.insert(SessionDayInfo,null,cv);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public long addPlayersToTeam(String player1,String player2, String player3,String player4,String player5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("PLAYER_ID1",player1);
        cv.put("PLAYER_ID2",player2);
        cv.put("PLAYER_ID3",player3);
        cv.put("PLAYER_ID4",player4);
        cv.put("PLAYER_ID5",player5);

        long result = db.insert(team,null,cv);


        if(result == -1){
            Log.e("kobe","false addplayers to team");
        }
        else{
            Log.e("kobe","false addplayers to team");
        }

        return result;

    }
    public void addGame(long team1, long team2, String status, int winner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        int Team1 = (int)team1;
        int Team2 = (int)team2;

        cv.put("TEAM1_ID",Team1);
        cv.put("TEAM2_ID",Team2);
        cv.put("STATUS",status);
        cv.put("WINNER_ID",winner);

        long id = db.insert(duwaInfo,null,cv);

    }
    public Cursor tempP(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from 'team_table'",null);

        return res;
    }

    public Cursor getGames(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("Select * from "+duwaInfo,null);
        return res;

    }

    public boolean nayduwaUpdate(){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String stat = "Wala";

        cv.put("STATUS_str","Wala");
        db.update("Status",cv,"STATUS_str = 'Naa'",null);
        return true;

    }

  /*  public Integer deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Statys","ID=?",new String[]{id});
    }*/

  public Cursor temp(){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues cv = new ContentValues();

      Cursor res = db.rawQuery("Select * from "+SessionDayInfo,null);
      return res;
  }
  public Cursor getPlayers(){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues cv = new ContentValues();

      Cursor res = db.rawQuery("Select * from player_table",null);
      return res;
  }
public void deleteTemp(){
    SQLiteDatabase db = this.getWritableDatabase();
   // db.execSQL("delete from player_table");
    db.execSQL("drop table if exists team_table");
}
public void updateBayranan(int pay){
    SQLiteDatabase db = this.getWritableDatabase();
    db.execSQL("UPDATE player_table set BAYRANAN = '"+pay+"' Where BAYRANAN > 1;");
}

}
