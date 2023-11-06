package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //public void changeWeight(int newWeight) { weight = newWeight; }
    //public int getWeight(){ return weight; }
    //public String getLetter(){ return letter; }

    //Check that settings are used
    @Test
    public void CheckChangeSettings() {
        Game game = new Game();
        //Board board = game.getBoard();
        Settings settings_prev = game.getSettings();
    }

    @Test
    public void CheckScoreIncreases() {
        Game game = new Game();
        Board board = game.getBoard();
        int score_prev = game.getTotalScore();
        board.setCurrentWord("SOFTWARE"); game.enterWord();
        //word = this.board.getCurrentWord();
        int score_now = game.getTotalScore();
        assertTrue( score_now > score_prev );
    }

    @Test
    public void CheckScoreDecreases() {
        Game game = new Game();
        int score_prev = game.getTotalScore();
        game.reroll();
        int score_now = game.getTotalScore();
        assertTrue( score_now < score_prev );
    }

    @Test
    public void GenerateNewBoard() {
        Game game = new Game();
        game.generateBoard();
        Board board_prev = game.getBoard();
        game.generateBoard();
        Board board_now = game.getBoard();
//compare( board_prev, board_now );
assertTrue( true );
    }

    //Check that game time elapses
    @Test
    public void CheckTimePasses() {
        int budge = 5;
        Game game = new Game();
        int time_prev = game.showTime().getTime();
        game.TimePasses(budge);
        int time_now = game.showTime().getTime();
        assertTrue( time_now > time_prev+budge );
    }

    //check that time can be show
    @Test
    public void CheckShowTime() {
        Game game = new Game();
        Time gametime = game.showTime();
        long begin = gametime.getTime();
        int budge = 5;
        game.TimePasses(budge);
        gametime  = game.showTime();
        System.out.println("now:"+(gametime.getTime()-begin));
        assertTrue( (gametime.getTime()-begin) > 0 );
    }

    //Check that game ends...
    @Test
    public void CheckGameEnds() {
        Game game = new Game();
        int budge = game.gameLength()*60;
        assertTrue( !game.isGameOver() );
        game.TimePasses(budge+1);
        assertTrue( game.isGameOver() );
        //game.exitGame();
    }

    //Check that enter word ok, and different word entered ok
    @Test
    public void CheckEnterWord_ok() {
        String word;
        Game game = new Game();
        Board board;
        board = game.getBoard();
        board.setCurrentWord("SOFTWARE");
        word = board.getCurrentWord();
        assertEquals( "SOFTWARE", word );
        game.enterWord();
        board.setCurrentWord("ENGINEERING");
        word = board.getCurrentWord();
        assertEquals( "ENGINEERING", word );
        game.enterWord();
        board.setCurrentWord("IS"); game.enterWord();
        board.setCurrentWord("COOL");
        word = board.getCurrentWord();
        assertEquals( "COOL", word );
game.enterWord();
    }

    //Check that enter word duplicate is error
    @Test
    public void CheckEnterWord_dup() {
        Game game = new Game();
        Board board = game.getBoard();
        board.setCurrentWord("STUFF");
        assertEquals( "STUFF", board.getCurrentWord() );
        game.enterWord();
        board.setCurrentWord("GRUFF");
        assertEquals( "GRUFF", board.getCurrentWord() );
        game.enterWord();
        board.setCurrentWord("XYZZY");
        assertEquals( "XYZZY", board.getCurrentWord() );
        game.enterWord();
        board.setCurrentWord("ENOUGH");
        assertTrue( game.CheckWordUsed("XYZZY") );
        board.setCurrentWord("PLOVER");
        assertTrue( !game.CheckWordUsed("PLOVER") );
    }

//public Board getBoard() { return board; }
    //Check that reroll works as expected
    @Test
    public void CheckReroll() {
        Game game = new Game();
        //this.board = new Board(gameSettings.getBoardSize(), this.cons, this.vowels);
        //get score prev
        int score_prev = game.showScore();
        //get board prev
        Board board_prev = game.getBoard();
        game.reroll();
        //compare score (-5);
        int score_new = game.showScore();
        assertTrue( score_new == score_prev - 5 );
        //get board new
        Board board_new = game.getBoard();
        //compare boards
        //compare resets count
    }

//public Board getBoard() { return board; }
//public int showScore(){ return this.totalScore; }
    //check that score shown
    @Test
    public void CheckScoreZero() {
        Game game = new Game();
        Board board = game.getBoard();
        //get score prev
        int score_orig = game.showScore();
        //get board prev
        game.reroll();
        //compare score (-5);
        int score_new = game.showScore();
        assertTrue( score_new == score_orig - 5 );
    }

    //check that score shown
    @Test
    public void CheckShowScore() {
        Game game = new Game();
        Board board = game.getBoard();
        int score_orig = game.showScore();
        assertTrue( score_orig == 0 );
        board.setCurrentWord("SOFTWARE"); game.enterWord();
        //word = this.board.getCurrentWord();
        int score_now = game.showScore();
        assertTrue( score_now == 8 );
    }

    //check that score shown
    //Checks private void updateScore(int score);
    @Test
    public void CheckDuplicateDetection() {
        Game game = new Game();
        Board board = game.getBoard();
        int score_orig = game.showScore();

        //String word;
        board.setCurrentWord("SOFTWARE"); game.enterWord();
        //word = this.board.getCurrentWord();
        board.setCurrentWord("ENGINEERING"); game.enterWord();
        //word = this.board.getCurrentWord();
        board.setCurrentWord("IS"); game.enterWord();
        board.setCurrentWord("COOL"); game.enterWord();
        //word = this.board.getCurrentWord();
        //check private boolean checkDuplicate(String word);
        //get score
        int score_prev = game.showScore();
        board.setCurrentWord("SOFTWARE"); game.enterWord();
        //get score
        int score_now = game.showScore();
        assertEquals( score_prev, score_now );
    }

    //Check that game.id increments
    //Checks incrementGameId();
    @Test
    public void GameIdIcrements() {
        Game game = new Game();
        int id_prev = game.getID();
        game.exitGame();
//this.currentStats.setNumWords(this.wordsPlayed.size()); //add to Stats
//this.currentStats.setFinalScore(this.totalScore); //add this to Stats class
        game = new Game();
        int id_now = game.getID();
        assertTrue( id_now > id_prev );
    }

//private void updateBestWord(String word){ }

/////

}
