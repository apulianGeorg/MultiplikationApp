package com.example.multiplicationapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPoints implements Comparable <PlayerPoints>{
    private String name;
    private int points;

    @Override
    public int compareTo(PlayerPoints o) {
        if (this.points > o.points){
            return -1;
        }
        if (this.points < o.points){
            return 1;
        }
        return this.name.compareTo(o.name);
    }
}
