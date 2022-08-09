package com.example.multiplicationapplication.model;

import static org.junit.Assert.*;

import com.example.multiplicationapplication.R;

import org.junit.Test;

public class BadgeTest {

    @Test
    public void badgeTest(){
        assertNull(Badge.getBadge(0));
        assertNull(Badge.getBadge(1000));
        assertNull(Badge.getBadge(49));
        assertEquals(Badge.CALCULATOR, Badge.getBadge(50));
        assertEquals(Badge.CALCULATOR, Badge.getBadge(99));
        assertEquals(Badge.SUPER_COMPUTER, Badge.getBadge(702));
        assertEquals(R.drawable.supercomputer, Badge.SUPER_COMPUTER.getFile());
        assertEquals("Du rechnest wie ein Supercomputer", Badge.SUPER_COMPUTER.getFileText());
    }

}