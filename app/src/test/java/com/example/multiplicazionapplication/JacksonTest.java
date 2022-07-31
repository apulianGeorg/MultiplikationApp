package com.example.multiplicazionapplication;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.multiplicazionapplication.backend.PlayerPoints;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

public class JacksonTest {
    //@Test
    public void whenSerializingUsingJsonGetter_thenCorrect()
            throws JsonProcessingException {

        PlayerPoints bean = new PlayerPoints("Hugo",3);

        String result = new ObjectMapper().writeValueAsString(bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("1"));
    }
}
