package edu.gatech.seclass.wordfind6300;

public interface GameInterface {
    void generateBoard();
    Time showTime();
    void resetScore();
    boolean isGameOver();
    boolean enterWord();
    void reroll();
    void exitGame();
    int showScore();
}
