package com.example.multiplicationapplication.model;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreFileManagerTest {
    private final HighScoreFileManager highScoreFileManager = new HighScoreFileManager();
    private final MyFileWriter myFileWriterMock = Mockito.mock(MyFileWriter.class);
    private final MyFileReader myFileReaderMock = Mockito.mock(MyFileReader.class);

    @Before
    public void init() throws NoSuchFieldException, JsonProcessingException {
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
    public void getListWithoutSave() throws JsonProcessingException {
        assertEquals(1, highScoreFileManager.getPlayerPointsList().size());
        assertEquals("Karl", highScoreFileManager.getPlayerPointsList().get(0).getName());
        assertEquals(3, highScoreFileManager.getPlayerPointsList().get(0).getPoints());
    }

    @Test
    public void saveNull() throws JsonProcessingException {
        highScoreFileManager.savePlayerPoints(null);
    }

    @Test
    public void saveNotNull() throws JsonProcessingException {
        PlayerPoints playerPoints = new PlayerPoints("Hugo", 12);
        highScoreFileManager.savePlayerPoints(playerPoints);
        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(myFileWriterMock).writeToSDFile(captor.capture());
        List<PlayerPoints> value = captor.getValue();
        Collections.sort(value);

        assertEquals(2, value.size());
        assertEquals("Hugo", value.get(0).getName());
        assertEquals(12, value.get(0).getPoints());
        assertEquals("Karl", value.get(1).getName());
        assertEquals(3, value.get(1).getPoints());
    }
}