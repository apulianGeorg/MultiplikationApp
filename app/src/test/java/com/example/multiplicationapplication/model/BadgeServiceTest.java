package com.example.multiplicationapplication.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BadgeServiceTest {
    private final BadgeService badgeService = new BadgeService();

    @Test
    public void notNullTest(){
        assertNotNull(badgeService);
    }

    @Test
    public void getNewBadgeTest(){
        Badge badge1 = badgeService.getNewBadge(3);
        Badge badge2 = badgeService.getNewBadge(50);
        Badge badge3 = badgeService.getNewBadge(52);
        Badge badge4 = badgeService.getNewBadge(100);

        assertNull(badge1);
        assertEquals(Badge.CALCULATOR, badge2);
        assertNull(badge3);
        assertEquals(Badge.MATH_FOX, badge4);
    }
}