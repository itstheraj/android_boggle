package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LetterUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //public void changeWeight(int newWeight) { weight = newWeight; }
    //public int getWeight(){ return weight; }
    //public String getLetter(){ return letter; }

    //Letter vowel create
    //Letter consonant create
    @Test
    public void letter_create() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        for( int x = 0; x < vows.length; x ++ ) {
            Letter v = new Letter( vows[x], 2 );
            assertEquals( 2, v.getWeight() );
        }
        for( int x = 0; x < cons.length; x ++ ) {
            Letter c = new Letter( cons[x], 1 );
            assertEquals( 1, c.getWeight() );
        }
    }

    //Letter vowel change weight
    //Letter consonant change weight
    @Test
    public void letter_change() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        String[] vows = {"A","E","I","O","U"};
        for( int x = 0; x < vows.length; x ++ ) {
            Letter v = new Letter( vows[x], 1 );
            assertEquals( 1, v.getWeight() );
            v.changeWeight( 3 );
            assertEquals( 3, v.getWeight() );
        }
        for( int x = 0; x < cons.length; x ++ ) {
            Letter c = new Letter( cons[x], 1 );
            assertEquals( 1, c.getWeight() );
            c.changeWeight( 5 );
            assertEquals( 5, c.getWeight() );
        }
    }

    //set vowel weight to valid
    @Test
    public void letter_vows_weight() {
        String[] vows = {"A","E","I","O","U"};
        for( int x = 0; x < vows.length; x ++ ) {
            Letter v = new Letter( vows[x], 5 );
            assertEquals( 5, v.getWeight() );
        }
    }

    //set vowel weight to valid
    @Test
    public void letter_cons_weight() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        for( int x = 0; x < cons.length; x ++ ) {
            Letter c = new Letter( cons[x], 3 );
            assertEquals( 3, c.getWeight() );
        }
    }

    //set vowel weight oob
    @Test
    public void letter_vows_weight_oob() {
        String[] vows = {"A","E","I","O","U"};
        for( int x = 0; x < vows.length; x ++ ) {
            Letter v = new Letter( vows[x], 9 );
            assertEquals( 1, v.getWeight() );
        }
    }

    //set cons weight oob
    @Test
    public void letter_cons_weight_oob() {
        String[] cons = {"B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"};
        for( int x = 0; x < cons.length; x ++ ) {
            Letter c = new Letter( cons[x], 9 );
            assertEquals( 1, c.getWeight() );
        }
    }

}
