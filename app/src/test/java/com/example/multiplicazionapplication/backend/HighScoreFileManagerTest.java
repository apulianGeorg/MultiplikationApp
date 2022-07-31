package com.example.multiplicazionapplication.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.ArrayList;

public class HighScoreFileManagerTest {
    private final HighScoreFileManager highScoreFileManager = new HighScoreFileManager();
    private final MyFileWriter myFileWriterMock = Mockito.mock(MyFileWriter.class);
    private final MyFileReader myFileReaderMock = Mockito.mock(MyFileReader.class);

    @Before
    public void init() throws NoSuchFieldException, JsonProcessingException {
        String fileStr = "[{\"name\":\"Karl\",\"points\":3}]";
        PlayerPoints mockPlayer = new PlayerPoints("Karl", 3);
        ArrayList<PlayerPoints> mockPlayerList = new ArrayList<>();
        mockPlayerList.add(mockPlayer);

        Mockito.when(myFileReaderMock.readFile()).thenReturn(mockPlayerList);

        new FieldSetter(highScoreFileManager, highScoreFileManager.getClass().getDeclaredField("myFileReader"))
                .set(myFileReaderMock);
        new FieldSetter(highScoreFileManager, highScoreFileManager.getClass().getDeclaredField("myFileWriter"))
                .set(myFileWriterMock);
    }

    @Test
    public void getListWithoutSave() {
        assertEquals(0, highScoreFileManager.getPlayerPointsList().size());
    }

    @Test
    public void saveNull() throws JsonProcessingException {
        highScoreFileManager.savePlayerPoints(null);
    }

    @Test
    public void saveNotNull() throws JsonProcessingException {
        PlayerPoints playerPoints = new PlayerPoints("Hugo", 12);
        highScoreFileManager.savePlayerPoints(playerPoints);
        assertEquals(2, highScoreFileManager.getPlayerPointsList().size());
        assertEquals("Hugo", highScoreFileManager.getPlayerPointsList().get(0).getName());
        assertEquals(12, highScoreFileManager.getPlayerPointsList().get(0).getPoints());
        assertEquals("Karl", highScoreFileManager.getPlayerPointsList().get(1).getName());
        assertEquals(3, highScoreFileManager.getPlayerPointsList().get(1).getPoints());
    }
}