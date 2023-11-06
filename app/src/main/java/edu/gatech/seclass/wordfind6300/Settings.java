package edu.gatech.seclass.wordfind6300;

import java.util.HashMap;
import java.util.Map;

public class Settings implements SettingsInterface {

    //variables
    //not defined yet
    //constants
    public final int time_MIN = 1;
    public final int time_MAX = 5;

    int gameLength;
    //public Time gameLength;
    int boardSize;

    Map<String, Integer> wtMap;

    public Letter[] vowelLetters = new Letter[5];
    public Letter[] consonantLetters = new Letter[21];

    int totalVowelWeight;
    int totalConsWeight;

    //constructor

    public Settings(int gl, int bs, Map<String, Integer> wts)
    {
        gameLength = gl;
        boardSize = bs;
        wtMap = wts;
        createLetters();
    }

    public Settings() {
        // setting game length (in minutes) to default value of 3
        gameLength = 3;
        //setting boardSize to default value of 4
        boardSize = 4;
        //make the letter array and value all letters with weight 1 to start
        wtMap = createDefMap();
        createLetters();
    }

    public Map<String, Integer> getMap()
    {
        return wtMap;
    }

    public Map<String, Integer> createDefMap()
    {
        Map<String, Integer> tempMap = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; ++c)
        {
            tempMap.put(Character.toString(c), 1);
        }

        return tempMap;
    }

    private void createLetters() {
        int tempVowel = 0;
        int tempConsonent = 0;
        String value;

        for(char c = 'A'; c <= 'Z'; ++c) {
            //isVowel = false;
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                //isVowel = true;
                vowelLetters[tempVowel] = new Letter(String.valueOf(c), 1);
                tempVowel++;
            }
            else if (c=='Q') {
                //System.out.println(c + "u " + isVowel);
                value = c + "u";
                consonantLetters[tempConsonent] = new Letter(value, 1);
                tempConsonent++;
            }
            else {
                //System.out.println(c + " " + isVowel);
                value = String.valueOf(c);
                consonantLetters[tempConsonent] = new Letter(value, 1);
                tempConsonent++;
            }
            System.out.println(c);

        }
    }

    public int getTotalVowelWeight() {
        return getTotalWeight(vowelLetters);
    }

    public int getTotalConsWeight() {
        return getTotalWeight(consonantLetters);
    }

    private int getTotalWeight(Letter[] array) {
        int runningTotal =0;
        for (int i = 0; i < array.length; i ++) {
            runningTotal += array[i].getWeight();
        }
        return runningTotal;
    }

    public void changeLetterWeight(Letter letter, int weight) {
        letter.changeWeight(weight);
    }

    public Letter[] getVowels() {
        return vowelLetters;
    }

    public Letter[] getCons() {
        return consonantLetters;
    }

    public void changeTime(int time) {
        if( time < time_MIN || time > time_MAX ) return;
        gameLength = time;
    }

    public void changeBoardSize(int size) {
        boardSize = size;
    }

    public int getGameLength() {
        return gameLength;
    }

    public int getBoardSize(){
        return boardSize;
    }
}

