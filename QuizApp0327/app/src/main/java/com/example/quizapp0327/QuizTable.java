package com.example.quizapp0327;

import java.util.StringTokenizer;

public class QuizTable {
    public static final String DELIMITER = ":";
    public static final String YES = "O", NO = "X";

    public static final String[] DATAS = {
            "Quiz1:O", "Quiz2:X", "Quiz3:O",
            "Quiz4:X", "Quiz5:O", "Quiz6:X",
            "Quiz7:O", "Quiz8:X", "Quiz9:O",
    };

    public static StringTokenizer getRandomDataStringTokenizer(){
        String data = DATAS[(int)(Math.random()*DATAS.length)];

        StringTokenizer st = new StringTokenizer(data, DELIMITER);

        return st;
    };
}
