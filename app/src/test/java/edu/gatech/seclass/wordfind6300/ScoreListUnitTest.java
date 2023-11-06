package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ScoreListUnitTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkSortStats() {
        ///TO DO
        assertEquals(4, 2 + 2);
    }

    @Test
    public void CheckGetGameByID() {
        //TO DO
        ScoreList boogle = new ScoreList();
        Player player = new Player();

        Game checker = player.getGame();
        assertEquals(1, checker.getID());

        checker.exitGame();
        player.playGame();

        Game checker2 = player.getGame();
        assertEquals(2, checker2.getID());
    }

    @Test
    public void CheckGetGameSettings() {
        ScoreList boogle = new ScoreList();
        Map<String, Integer> tempMap = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; ++c)
        {
            tempMap.put(Character.toString(c), 1);
        }
        Settings tempSettings = new Settings(3, 5, tempMap);

        Game checker = new Game(tempSettings);
        boogle.append(checker);

        assertEquals(tempSettings, boogle.getGameSettings(checker.getID()));

    }

    @Test
    public void CheckAppend() {
        //TO DO
        ScoreList boogle = new ScoreList();

        Game checker = new Game();
        Game checker2 = new Game();
        boogle.append(checker);
        boogle.append(checker2);
        assertEquals(2, boogle.games.size());
    }



}
