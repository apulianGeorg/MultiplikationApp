package com.example.multiplicationapplication.model;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerPointsTest {
    @Test
    public void sortTest(){
        ArrayList<PlayerPoints> playerPointsList = new ArrayList<>();
        PlayerPoints playerPoints1 = new PlayerPoints("ADAM", 1);
        PlayerPoints playerPoints2 = new PlayerPoints("Bert", 3);
        PlayerPoints playerPoints3 = new PlayerPoints("Carl", 2);
        PlayerPoints playerPoints4 = new PlayerPoints();
        playerPoints4.setPoints(1);
        playerPoints4.setName("Doris");
        playerPointsList.add(playerPoints1);
        playerPointsList.add(playerPoints2);
        playerPointsList.add(playerPoints3);
        playerPointsList.add(playerPoints4);

        Collections.sort(playerPointsList);
        assertEquals("Bert", playerPointsList.get(0).getName());
        assertEquals("Carl", playerPointsList.get(1).getName());
        assertEquals("ADAM", playerPointsList.get(2).getName());
        assertEquals("Doris", playerPointsList.get(3).getName());
    }

}