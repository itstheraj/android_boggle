package edu.gatech.seclass.wordfind6300;

public class Player implements PlayerInterface
{
    private Settings settings;
    public static WordList wordStats;
    public static ScoreList scoreStats;
    private Game gm;

    public Player(){
        this.settings = new Settings();
        wordStats = new WordList();
        scoreStats = new ScoreList();
        gm = new Game(settings);
    }

    public Game getGame()
    {
        return gm;
    }

    public void playGame(){
        gm = new Game();
        //open game UI
    }

    public void viewStats(){
        //call menu item
    }

    public Settings getSettings(){
        return settings;
    }

    public void setSettings(Settings st)
    {
        settings = st;
        gm = new Game(settings);
    }

    public void changeSettings()
    {
        return;
    }
}

