package edu.gatech.seclass.wordfind6300;

import java.util.*;
//import edu.gatech.seclass.wordfind6300.GameInterface;
//import java.lang.Object.*;
//import android.os.Handler;

public class Game implements GameInterface {
    private Settings gameSettings;
    private Board board;
    private Time time;
    //Handler handler = new Handler();
    private int state = 0;
    private Stats currentStats;
    private List <String> wordsPlayed;
    private String bestWord = "";
    private static Integer gameID = 1;
    private Integer myGameID;
    private Integer totalScore = 0;
    private String[] cons;
    private String[] vowels;
    private Integer numCons;
    private Integer numVowels;

    public Game(){
        this.gameSettings = new Settings();
        this.currentStats = new Stats(0, 0, 0);
        this.wordsPlayed = new ArrayList<>();
        numCons = gameSettings.getTotalConsWeight();
        numVowels = gameSettings.getTotalVowelWeight();
        this.cons = new String[this.numCons];
        this.vowels = new String[this.numVowels];
        this.generateBoard();
        this.time = new Time();
        this.time.setTime(this.gameSettings.getGameLength());
        this.TimerHandler();
        this.myGameID = Game.gameID;
    }

    public Game(Settings st)
    {
        this.wordsPlayed = new ArrayList<>();
        gameSettings = st;
        this.currentStats = new Stats(0, 0, 0);
        numCons = gameSettings.getTotalConsWeight();
        numVowels = gameSettings.getTotalVowelWeight();
        this.cons = new String[this.numCons];
        this.vowels = new String[this.numVowels];
        this.generateBoard();
        this.time = new Time();
        this.time.setTime(st.getGameLength());
        this.TimerHandler();
        this.myGameID = Game.gameID;
    }

    public Stats getStats()
    {
        return currentStats;
    }

    private void TimerHandler()
    {
final Game mygame = this;
final Time timer = this.time;
        long begin = this.time.getTime();
/*
        //Handler handler = new Handler();
        this.handler = new Handler();
        final int delay = 1000; //milliseconds
        handler.postDelayed(new Runnable(){
            public void run(){
                //do something
                if( (mygame.state>0) && (mygame.time.Remain() >= 0) ) {
                    mygame.time.Change();
System.out.println("tick:"+(timer.getTime()/1000));
System.out.println("remain:"+(timer.Remain()));
                    handler.postDelayed(this, delay);
                }
            }
        }, delay);
*/
    }

    public Settings getSettings()
    {
        return gameSettings;
    }

    public int getTotalScore()
    {
        return totalScore;
    }

    public int getID()
    {
        return this.myGameID;
    }


    public void generateBoard(){
        this.generateLetters();
        //this.randomizeLetters(this.vowels);
        //this.randomizeLetters(this.cons);
        for (String st : this.vowels) {
            System.out.println(st);
        }
        this.board = new Board(gameSettings.getBoardSize(), this.cons, this.vowels);
    }

    public Time showTime(){
        //display on board
        //System.out.println("time:"+this.time.getTime());
        return this.time;
    }

    public void resetScore(){
        this.totalScore = 0;
    }

    public String getBestWord()
    {
        return bestWord;
    }

    public boolean isGameOver(){
        //logic for when games ends... when is game over?
System.out.println("remain:"+this.time.Remain());
        if( this.time.Remain() <= 0 ) {
            return true;
        }
        return false;
    }

    public boolean enterWord(){
        String word = this.board.getCurrentWord();
        word = word.trim();


//word.equalsIgnoreCase("Qu")
        if( (word.length() < 2)
         || word.equalsIgnoreCase("Qu") || word.equalsIgnoreCase("QU") ) {
            //display "word already played" and clear
            System.out.println("word:["+word+"].("+word.length()+") too short");
            this.board.resetCurrentWord();
            return false;
        }
        if(this.checkDuplicate(word)) {
            //display "word already played" and clear
            System.out.println("word:["+word+"].("+word.length()+") duplicate");
            this.board.resetCurrentWord();
            return false;
        }
        if(!this.checkDuplicate(word)) {
            System.out.println("word:["+word+"].("+word.length()+")");
            this.updateScore(word.length());
            this.updateBestWord(word);
            this.wordsPlayed.add(word);
            this.board.resetCurrentWord();
            return true;
        }
        return false;
    }

    public Board getBoard()
    {
        return board;
    }

    public void reroll(){
        System.out.println("randomize");
        this.randomizeLetters(this.vowels);
        this.randomizeLetters(this.cons);
        System.out.println("reroll");
        this.board = new Board(gameSettings.getBoardSize(), this.cons, this.vowels);
        this.updateScore(-5);
        this.currentStats.incResets(); // add this method to Stats class totalResets++
    }

    public void exitGame(){
        this.currentStats.setNumWords(this.wordsPlayed.size()); //add to Stats
        this.currentStats.setFinalScore(this.totalScore); //add this to Stats class
        this.incrementGameId();

        //return to Player class
    }

    public int showScore(){
        return this.totalScore;
    }

    private void generateLetters(){
        //generate list of vowels and consonants
        //randomize all vowels and all cons and place vowelSize and consSize into vowels and cons\
        System.out.println(this.numVowels);
        for(int i = 0; i < this.numVowels; i++){
            int num = this.gameSettings.getVowels()[i].getWeight();
            for(int j = 0; j < num; j++){
                i += j;
                this.vowels[i] = this.gameSettings.getVowels()[i].getLetter();
            }
        }
        for(int i = 0; i < this.numCons; i++){
            int num = this.gameSettings.getCons()[i].getWeight();
            for(int j = 0; j < num; j++){
                i += j;
                this.cons[i] = this.gameSettings.getCons()[i].getLetter();
            }
        }
    }

    private void randomizeLetters(String[] letters){
        for (int i = letters.length - 1; i > 0; i--)
        {
            int index = (int)(Math.random() * letters.length);
            String temp = letters[index % letters.length];
            letters[index] = letters[i];
            letters[i] = temp;
        }
    }

    private void resetTime(){
        //this.time.setTime(this.gameSettings.gameLength());
        this.time = new Time();
    }

    public boolean checkDuplicate(String word){
        if(this.wordsPlayed.contains(word))
            return true;
        return false;
    }

    private void updateScore(int score){
        this.totalScore += score;
    }

    private void updateBestWord(String word){
        if(word.length() > this.bestWord.length())
            this.bestWord = word;
    }

    private void incrementGameId(){
        Game.gameID++;
    }

///// Unit Test interface
    public boolean CheckWordUsed(String word){
        return this.wordsPlayed.contains(word);
    }

    public int TimePasses(int wait){
        System.out.println("now:"+this.time.getTime());
        System.out.println("wait:"+wait);
        this.time.add(wait);
        System.out.println("now:"+this.time.getTime());
        return this.time.getTime();
    }

    public int TimeNow(){
        //System.out.println("now:"+this.time.getTime());
        return this.time.getTime();
    }

    public int gameLength(){
        return this.gameSettings.getGameLength();
    }
}
