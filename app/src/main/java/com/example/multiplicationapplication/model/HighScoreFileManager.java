package com.example.multiplicationapplication.model;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreFileManager {

    private List<PlayerPoints> playerPointsList;
    private final MyFileReader myFileReader = new MyFileReader();
    private final MyFileWriter myFileWriter = new MyFileWriter();

    public void savePlayerPoints(PlayerPoints playerPoints) throws JsonProcessingException {
        if (playerPoints == null) {
            return;
        }
        playerPointsList = new ArrayList<>(myFileReader.readFile());
        playerPointsList.add(playerPoints);
        save(playerPointsList);
    }

    public List<PlayerPoints> getPlayerPointsList() throws JsonProcessingException {
        playerPointsList = myFileReader.readFile();
        if (playerPointsList == null) {
            return new ArrayList<>();
        }
        Collections.sort(playerPointsList);
        return playerPointsList;
    }

    private void save(List<PlayerPoints> playerPointsList) throws JsonProcessingException {
        myFileWriter.writeToSDFile(playerPointsList);
    }
}
