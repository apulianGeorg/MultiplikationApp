package com.example.multiplicationapplication.model;

import static com.example.multiplicationapplication.Constants.FILENAME;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFileReader {

    public List<PlayerPoints> readFile() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String fileInput = readFileInput();

        if (fileInput.length()==0){
            return new ArrayList<>();
        }

        PlayerPoints[] playerPoints = mapper.readValue(fileInput, PlayerPoints[].class);
        return Arrays.asList(playerPoints);
    }

    private String readFileInput() {
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/download");
        File file = new File(dir, FILENAME);
        try {
            java.io.FileReader fileReader = new java.io.FileReader(file);
            char[] chars = new char[(int) file.length()];
            //noinspection ResultOfMethodCallIgnored
            fileReader.read(chars);
            return new String(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
