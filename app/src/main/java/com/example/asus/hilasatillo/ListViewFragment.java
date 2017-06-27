package com.example.asus.hilasatillo;


import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends DialogFragment implements ListViewFragmentAdapter.OnClickListener{
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView listView;
    ArrayList<PersonModel> personModel;
    Button butt;
    Context cont;
    private OnCompleteListener mListener;

    int button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_view, container, false);
        personModel = new ArrayList<>();
        listView = (RecyclerView)view.findViewById(R.id.listView);
        butt = (Button)view.findViewById(R.id.but);

        mListener = (OnCompleteListener)getTargetFragment();

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onComplete(button,"kobe");
                dismiss();

            }
        });

        Bundle extras = this.getArguments();
        if(extras != null){
            personModel = extras.getParcelableArrayList("list");
            button = extras.getInt("button");
            Log.e("kobe","not null = "+personModel.size());
            Log.e("kobe","button = "+button);
        }else{
            Log.e("kobe","null");
        }

       initList(view);

        return view;
    }



    public void initList(View view){
        adapter(view);
    }
public void adapter(View view){
    layoutManager = new LinearLayoutManager(view.getContext());
    listView.setLayoutManager(layoutManager);
    adapter = new ListViewFragmentAdapter(personModel,this,this);
    listView.setAdapter(adapter);
}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            this.mListener = (OnCompleteListener)this.cont;
        }catch (final ClassCastException e ){

        }
    }

    @Override
    public void onClick(String name) {
        mListener.onComplete(button,name);
    }


    public static interface OnCompleteListener{
        public void onComplete(int button, String name);

    }


}
