package com.example.asus.hilasatillo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by asus on 12/05/2017.
 */

public class Attendance_Adapter extends RecyclerView.Adapter<Attendance_Adapter.ViewHolder> {
    ArrayList<PersonModel> personList;
    Context mContext;

    public Attendance_Adapter(ArrayList<PersonModel> personList, Context mContext) {
        this.personList = personList;
        this.mContext = mContext;
        Log.e("kobe","list = "+personList.size());
    }

    @Override
    public Attendance_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Attendance_Adapter.ViewHolder holder, int position) {
     holder.name.setText(personList.get(position).getName());
        holder.bayad.setText(personList.get(position).getToPay()+"");
        holder.status.setText(personList.get(position).getStatus());
        Log.e("kobe","status giatay = "+personList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView bayad;
        TextView status;

        public ViewHolder(final View itemView){
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            bayad = (TextView)itemView.findViewById(R.id.bayranan);
            status = (TextView)itemView.findViewById(R.id.status);

        }
    }
}
