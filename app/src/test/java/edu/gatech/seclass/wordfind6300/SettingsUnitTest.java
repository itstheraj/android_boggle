package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SettingsUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //public void changeWeight(int newWeight) { weight = newWeight; }
    //public int getWeight(){ return weight; }
    //public String getLetter(){ return letter; }

    //Check Settings creation works
    @Test
    public void CheckSettingsCreate() {
        Settings settings = new Settings();
        assertEquals( settings.getGameLength(), 3 );
        assertEquals( settings.getBoardSize(), 4 );
    }

    //Check Settings creation parameters works

    @Test
    public void CheckSettingsCreateParams() {
        Settings base = new Settings();
        Map<String, Integer> basemap = base.createDefMap();
        Settings settings = new Settings(5, 7, basemap);
        assertEquals( settings.getGameLength(), 5 );
        assertEquals( settings.getBoardSize(), 7 );
    }

    //Check Settings vowel weight
    @Test
    public void CheckSettingsVowelWeight() {
        Settings base = new Settings();
        //Map<String, Integer> basemap = base.createDefMap();
        assertEquals( 5, base.getTotalVowelWeight() );
    }

    //Check Settings cons weight
    @Test
    public void CheckSettingsConsWeight() {
        Settings base = new Settings();
        //Map<String, Integer> basemap = base.createDefMap();
        assertEquals( 21, base.getTotalConsWeight() );
    }

    //Check Settings change time
    @Test
    public void CheckChangeTime() {
        Settings settings = new Settings();
        for( int x = 1; x <= 5; x++ ) {
            settings.changeTime(x);
            assertEquals( x, settings.getGameLength() );
        }
    }

    //Check Settings change board size
    @Test
    public void CheckChangeBoardSize() {
        Settings settings = new Settings();
        for( int x = 1; x <= 5; x++ ) {
            settings.changeBoardSize(x);
            assertEquals( x, settings.getBoardSize() );
        }
    }

    //Check getBoardSize
    @Test
    public void CheckGetBoardSize() {
        Settings base = new Settings();
        Map<String, Integer> basemap = base.createDefMap();
        for( int x = 4; x <= 8; x++ ) {
            Settings settings = new Settings(2, x, basemap);
            assertEquals( x, settings.getBoardSize() );
        }
    }

    //Check getGameLength
    @Test
    public void CheckGetGameLength() {
        Settings base = new Settings();
        Map<String, Integer> basemap = base.createDefMap();
        for( int x = 4; x <= 8; x++ ) {
            Settings settings = new Settings(x, 4, basemap);
            assertEquals( x, settings.getGameLength() );
        }
    }

    //Check getVowels has 5 vowels
    @Test
    public void CheckGetVowels() {
        Settings settings = new Settings();
        Letter[] val = settings.getVowels();
        assertEquals( 5, val.length );
    }

    //Check getCons has 21 consonants
    @Test
    public void CheckGetCons() {
        Settings settings = new Settings();
        Letter[] val = settings.getCons();
        assertEquals( 21, val.length );
    }

    //Check that time changes...
    @Test
    public void xCheckTimeChanges() {
    }

}
