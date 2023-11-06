package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BoardUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //Board(int size, String[] cons, String[] vowels)
    //Board creates size 4x4 success => size 4
    //Board create size 8x8 success => size 8
    @Test
    public void board_size_ok() {
        String[] cons = {"B","C","D","F","X"};
        String[] vows = {"A","E","I","O","U"};
        //create board 4x4
        Board board4x4 = new Board(4, cons, vows );
        assertEquals( 4, board4x4.getSize() );
        //create board 8x8
        Board board8x8 = new Board(8, cons, vows );
        assertEquals( 8, board8x8.getSize() );
    }

    //Board create size 3x3 fails
    //Board create size 9x9 fails
    @Test
    public void board_size_oob() {
        String[] cons = {"B","C","D","F","X"};
        String[] vows = {"A","E","I","O","U"};
        //create board 3x3 => fails
        Board board3x3 = new Board(3, cons, vows );
        assertEquals( 4, board3x3.getSize() );

        //create board 9x9 => fails
        Board board9x9 = new Board(9, cons, vows );
        assertEquals( 4, board9x9.getSize() );
    }

    @Test
    public void vowels_ok() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        int v = 0;
        int c = 0;
        Board board = new Board(5, cons, vows );
        //assertEquals( 5, board.getSize() );
        for( int rep=0; rep < 40; ++rep ) {
            v += board.countVowels(); //vowels this time
        }
        System.out.println("vow:"+v);
        assertTrue( v >= 150 );
        assertTrue( v <= 300 );

        double vr = 1.0*v / (25*40); //vowel ratio
        System.out.println("vowel ratio:"+vr);

        assertTrue( (vr > 0.19) );
    }

    @Test
    public void consonants_ok() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        int v = 0;
        int c = 0;
        Board board = new Board(5, cons, vows );
        //assertEquals( 5, board.getSize() );
        for( int rep=0; rep < 40; ++rep ) {
            c += board.countConsonants(); //consonants this time
        }
        System.out.println("cons:"+c);
        assertTrue(c >= 750 );
        assertTrue(c <= 850 );

        double cr = 1.0*c / (1000); //consonant ratio
        System.out.println("cons ratio:"+cr);

        assertTrue( (cr < 0.82) );
    }

    //check that letter distribution near goal: 1/5:4/5
    @Test
    public void letter_distribution_ok() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        int v = 0;
        int c = 0;
        Board board = new Board(5, cons, vows );
        //assertEquals( 5, board.getSize() );
        for( int rep=0; rep < 40; ++rep ) {
            v += board.countVowels(); //vowels this time
            c += board.countConsonants(); //consonants this time
        }
        System.out.println("vow+cons:"+v+","+c);
        assertEquals(25*40, (v+c));

        double vr = 1.0*v / (v+c); //vowel ratio
        double cr = 1.0*c / (v+c); //consonant ratio
        System.out.println("vowel ratio:"+vr);
        System.out.println("cons ratio:"+cr);

        assertTrue( (vr > 0.15) );
        assertTrue( (cr < 0.85) );
    }

    //Check that pickLetter chooses the correct row&column
    @Test
    public void pickLetter_normal() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        int v = 0;
        int c = 0;
        //create board 5x5
        Board board = new Board(5, cons, vows );
        for( int rn=0; rn < 5; ++rn ) {
            board.resetCurrentWord();
            for( int cn=0; cn < 5; ++cn ) {
                boolean b = board.pickLetter(rn,cn);
                if(!b) System.out.println("r:"+rn+",c:"+cn);
                assertTrue( b );
            }
        }
        board = new Board(8, cons, vows );
        for( int rn=0; rn < 8; ++rn ) {
            board.resetCurrentWord();
            for( int cn=0; cn < 8; ++cn ) {
                boolean b = board.pickLetter(rn,cn);
                assertTrue( b );
            }
        }
    }

    //Check that pickLetter chooses the correct row&column
    @Test
    public void pickLetter_oob() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        int v = 0;
        int c = 0;
        //create board 5x5
        Board board = new Board(5, cons, vows );
        for( int rn=0; rn < 5; ++rn ) {
            boolean b = board.pickLetter(rn,6);
            assertFalse( b );
        }
        for( int cn=0; cn < 5; ++cn ) {
            boolean b = board.pickLetter(6,cn);
            assertFalse( b );
        }
        board = new Board(8, cons, vows );
        for( int rn=0; rn < 8; ++rn ) {
            boolean b = board.pickLetter(rn,8);
            assertFalse( b );
        }
        for( int cn=0; cn < 8; ++cn ) {
            boolean b = board.pickLetter(8,cn);
            assertFalse( b );
        }
    }

    //Check that previous word played is persisted
    @Test
    public void currentword_checks() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        Board board = new Board(5, cons, vows );
        board.resetCurrentWord();
        assertEquals( "", board.getCurrentWord() );
        board.setCurrentWord("TESTING");
        assertEquals( "TESTING", board.getCurrentWord() );
        board.setCurrentWord("SOFTWARE");
        assertEquals( "SOFTWARE", board.getCurrentWord() );
        board.resetCurrentWord();
        assertEquals( "", board.getCurrentWord() );
    }

}

