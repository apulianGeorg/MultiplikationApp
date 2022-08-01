package com.example.multiplicationapplication;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.multiplicationapplication.model.PlayerPoints;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
