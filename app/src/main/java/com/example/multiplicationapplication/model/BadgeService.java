package com.example.multiplicationapplication.model;

import java.util.Objects;

public class BadgeService {
    private Badge lastBadge;
    public Badge getNewBadge(int points) {
        Badge badge = Badge.getBadge(points);
        if (Objects.equals(badge, lastBadge)){
            return null;
        }
        lastBadge = badge;
        return Objects.requireNonNull(badge);
    }
}
