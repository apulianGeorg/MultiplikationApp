package com.example.multiplicazionapplication.backend;

import com.example.multiplicazionapplication.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MyFileWriter {
    public void writeToSDFile(List<PlayerPoints> playerPointsList) throws JsonProcessingException {
        String result = new ObjectMapper().writeValueAsString(playerPointsList);
        writeToSDFile(result);
    }

    private void writeToSDFile(String inpStr) {
        ///storage/emulated/0/downloadTippFile.txt
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/download");
        //noinspection ResultOfMethodCallIgnored
        dir.mkdirs();
        File file = new File(dir, Constants.FILENAME);

        try {
            FileOutputStream f = new FileOutputStream(file, false);
            f.write(inpStr.getBytes());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
