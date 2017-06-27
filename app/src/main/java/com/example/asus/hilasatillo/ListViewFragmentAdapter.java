package com.example.asus.hilasatillo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by asus on 16/05/2017.
 */

public class ListViewFragmentAdapter extends RecyclerView.Adapter<ListViewFragmentAdapter.ViewHolder> {
    ArrayList<PersonModel> personList;
    private OnClickListener mLiistener;
    ListViewFragment fragment;

    public ListViewFragmentAdapter(ArrayList<PersonModel> personList, OnClickListener mLiistener, ListViewFragment fragment) {
        this.personList = personList;
        this.mLiistener = mLiistener;
        this.fragment = fragment;

    }

    @Override
    public ListViewFragmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewFragmentAdapter.ViewHolder holder, int position) {
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
        public ViewHolder(final View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            bayad = (TextView)itemView.findViewById(R.id.bayranan);
            status = (TextView)itemView.findViewById(R.id.status);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {if(mLiistener !=null) {

                    mLiistener.onClick(personList.get(getAdapterPosition()).getName());
                    personList.remove(getAdapterPosition());
                    fragment.dismiss();
                }
                else Log.e("kobe","null ng mLiistener sa adapter");
                }
            });
        }
    }


    public interface OnClickListener{
        public void onClick(String name);
    }
}
