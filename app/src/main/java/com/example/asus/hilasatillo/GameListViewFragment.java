package com.example.asus.hilasatillo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by asus on 09/06/2017.
 */

public class GameListViewFragment extends RecyclerView.Adapter<GameListViewFragment.ViewHolder> {
    ArrayList<GameModel> game;

    public GameListViewFragment(ArrayList<GameModel> game) {
        this.game = game;
        Log.e("kobe","gamelist = "+game.size());
    }

    @Override
    public GameListViewFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_layout,parent,false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(GameListViewFragment.ViewHolder holder, int position) {
        holder.team1player1.setText(game.get(position).getTeam1_player1());
        holder.team1player2.setText(game.get(position).getTeam1_player2());
        holder.team1player3.setText(game.get(position).getTeam1_player3());
        holder.team1player4.setText(game.get(position).getTeam1_player4());
        holder.team1player5.setText(game.get(position).getTeam1_player5());

        holder.team2player1.setText(game.get(position).getTeam2_player1());
        holder.team2player2.setText(game.get(position).getTeam2_player2());
        holder.team2player3.setText(game.get(position).getTeam2_player3());
        holder.team2player4.setText(game.get(position).getTeam2_player4());
        holder.team2player5.setText(game.get(position).getTeam2_player5());

        holder.status.setText(game.get(position).getStatus());

        holder.winner.setText(game.get(position).getWinner());

    }

    @Override
    public int getItemCount() {
        return game.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView team1player1, team1player2,team1player3,team1player4,team1player5;
        TextView team2player1, team2player2, team2player3, team2player4, team2player5;

        TextView winner, status;
        Button but;
        public ViewHolder(View itemView) {
            super(itemView);

            team1player1 = (TextView)itemView.findViewById(R.id.team1_player1Game);
            team1player2 = (TextView)itemView.findViewById(R.id.team1_player2Game);
            team1player3 = (TextView)itemView.findViewById(R.id.team1_player3Game);
            team1player4 = (TextView)itemView.findViewById(R.id.team1_player4Game);
            team1player5 = (TextView)itemView.findViewById(R.id.team1_player5Game);
            team2player1 = (TextView)itemView.findViewById(R.id.team2_player1Game);
            team2player2 = (TextView)itemView.findViewById(R.id.team2_player2Game);
            team2player3 = (TextView)itemView.findViewById(R.id.team2_player3Game);
            team2player4 = (TextView)itemView.findViewById(R.id.team2_player4Game);
            team2player5 = (TextView)itemView.findViewById(R.id.team2_player5Game);

            winner = (TextView)itemView.findViewById(R.id.gameWinner);
            status = (TextView)itemView.findViewById(R.id.status);
            but = (Button)itemView.findViewById(R.id.but);

            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
