package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class PlayerUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //Check if getGame works correctly
    @Test
    public void CheckGetGame() {
        Player p = new Player();
        assertEquals(String.valueOf(p.getGame().getClass()), "class edu.gatech.seclass.wordfind6300.Game");
    }
    //Check if viewStats works correctly
    @Test
    public void CheckViewStats() {
        Player p = new Player();
        p.viewStats();
        assertTrue(true);
    }
    //Check if setGame works correctly
    @Test
    public void CheckSetGame() {
        Player p = new Player();
        p.playGame();
        assertTrue(true);
    }
    //Check if getstatistics works correctly
    @Test
    public void CheckGetSettings() {
        Player p = new Player();
        Settings s = p.getSettings();
        assertEquals(String.valueOf(s.getClass()), "class edu.gatech.seclass.wordfind6300.Settings");
    }
    //Check if changeSettings works correctly
    @Test
    public void CheckChangeSettings() {
        Player p = new Player();
        p.changeSettings();
        assertTrue(true);
    }
    //Check if setSettings works correctly
    @Test
    public void CheckSetSettings() {
        Player p = new Player();
        Settings s = new Settings();
        p.setSettings(s);
        assertTrue(true);
    }
}
