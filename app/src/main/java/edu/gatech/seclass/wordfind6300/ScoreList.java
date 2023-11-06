package edu.gatech.seclass.wordfind6300;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreList
{


    // GET LIST OF GAMES SORTED BY SCORE.
    // SORT LIST OF SCORES, ATTACH GAME ID
    // GET GAME FROM LOOKUP
    // RETURN LIST OF GAMES

    // GLOBAL LIST OF GAMES
    List<Game> games = new ArrayList<>();
    // ID TO GAME
    Map<Integer, Game> lookup = new HashMap<>();

    // void sortStats();
    public List<Game> sortStats()
    {
       Comparator<Game> compareByScore = new Comparator<Game>() {
           @Override
           public int compare(Game Game1, Game Game2) {
               return ((Integer) Game1.getTotalScore()).compareTo((Game2.getTotalScore()));
           }
       };
       Collections.sort(games, compareByScore);
       Collections.reverse(games);
       return games;
    }

    public Game getGameByID(Integer gameID)
    {
        return lookup.get(gameID);
    }

    public Settings getGameSettings(Integer gameID)
    {
        return lookup.get(gameID).getSettings();
    }

    public void append(Game gm)
    {
        games.add(gm);
        for (Game game : games)
        {
            lookup.put(game.getID(), game);
        }
    }
}

