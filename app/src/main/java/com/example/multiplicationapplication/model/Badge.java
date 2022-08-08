package com.example.multiplicationapplication.model;

import com.example.multiplicationapplication.R;

public enum Badge {
    CALCULATOR(R.drawable.calculator, "Du hast den Status Taschenrechner erreicht", 50, 99),
    MATH_FOX(R.drawable.mathfox, "Du bist ein echter Mathefuchs", 100, 149),
    SCHLAUBI(R.drawable.schlaubi, "Dein Name ist Schlaubi", 150, 199),
    KING(R.drawable.king, "Du bist der Mathe König", 200, 299),
    PROFESSOR(R.drawable.professor, "Du musst ein Mathe Professor sein", 300, 399),
    CHECKER(R.drawable.matheverstanden, "Du checkst es einfach", 400, 499),
    SMOKING_HEAD(R.drawable.rauchenderkopf, "Dir raucht der Kopf", 500, 599),
    EINSTEIN(R.drawable.einstein, "Du hast den Status Einstein erreicht", 600, 699),
    SUPER_COMPUTER(R.drawable.supercomputer, "Du rechnest wie ein Supercomputer", 700, 999);

    private final int file;
    private final String fileText;
    private final int minPoints;
    private final int maxPoints;

    Badge(int file, String fileText, int minPoints, int maxPoints) {
        this.file = file;
        this.fileText = fileText;
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
    }

    public int getFile(){
        return file;
    }

    public String getFileText(){
        return fileText;
    }

    public static Badge getBadge(int points) {
        for (Badge badge : Badge.values()) {
            if (badge.minPoints <= points && badge.maxPoints >= points) {
                return badge;
            }
        }
        return null;
    }
}
