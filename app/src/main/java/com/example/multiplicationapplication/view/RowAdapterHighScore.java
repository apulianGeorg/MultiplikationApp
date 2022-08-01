package com.example.multiplicationapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.multiplicationapplication.R;
import com.example.multiplicationapplication.model.PlayerPoints;

import java.util.List;


public class RowAdapterHighScore extends ArrayAdapter<PlayerPoints> {
    List<PlayerPoints> playerPointsList;
    Context context;

    public RowAdapterHighScore(Context context, int textViewResourceId, List<PlayerPoints> playerPointsList) {
        super(context, textViewResourceId, playerPointsList);
        this.playerPointsList = playerPointsList;
        this.context = context;
    }

    static class ViewHolder {
        TextView playerName;
        TextView playerPoints;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_row_highscore, parent, false);

            holder = new ViewHolder();
            holder.playerName = convertView.findViewById(R.id.high_score_player_name);
            holder.playerPoints = convertView.findViewById(R.id.high_score_points);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.playerName.setText(playerPointsList.get(position).getName());

        holder.playerPoints.setText(String.valueOf(playerPointsList.get(position).getPoints()));

        return convertView;
    }
}
