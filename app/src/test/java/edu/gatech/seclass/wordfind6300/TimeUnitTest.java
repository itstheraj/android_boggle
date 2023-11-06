package edu.gatech.seclass.wordfind6300;

import org.junit.Test;
import java.lang.Thread.*;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TimeUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

//public void changeWeight(int newWeight) { weight = newWeight; }
//public int getWeight(){ return weight; }
//public String getLetter(){ return letter; }

    //Check Time creation works
    @Test
    public void CheckTimeCreate() {
        long begin = 0;
        for( int duration = 1; duration <= 5; duration++ ) {
            Time time = new Time();
            time.setTime(duration);
            begin = time.getTime();
            assertTrue( (time.getTime()-begin) < 1 );
            for( int x = duration; x < duration; x++ ) {
                time.add(60);
                assertTrue( (time.getTime()-begin) >= x*60 );
            }
        }
    }

    //Check time duration works
    @Test
    public void CheckTimeCreateDuration() {
        long begin = 0;
        for( int duration = 1; duration <= 5; duration++ ) {
            Time time = new Time(duration);
            begin = time.getTime();
            for( int x = duration; x < duration; x++ ) {
                time.add(60);
                assertTrue( (time.getTime()-begin) >= x*60 );
            }
        }
    }

    //Check Time elapses...
    @Test
    public void CheckTimePasses() {
        long begin = 0;
        Time time = new Time();
        begin = time.getTime();
        assertTrue( (time.getTime()-begin) < 1 );
        for( int x = 0; x < 300; x++ ) {
            time.add(1);
            assertTrue( (time.getTime()-begin) >= x );
        }
    }

    //Check time expires (remain drops < 0)
    @Test
    public void CheckTimeRemain() {
        long begin = 0;
        for( int mins = 1; mins <= 5; mins++ ) {
            Time time = new Time();
            assertTrue( time.getTime() < 1 );
            time.setTime(mins);
            System.out.println("mins:"+mins);
            begin = time.getTime();
            for( int x = 1; x <= mins; x++ ) {
                time.add(60);
                System.out.println("time:"+(time.getTime()-begin));
                assertTrue( (time.getTime()-begin) >= x*60 );
            }
            time.add(1);
            System.out.println("time:"+(time.getTime()-begin));
            System.out.println("remain:"+(time.Remain()));
            assertTrue( time.Remain() < 0 );
        }
    }
/* not working
    //Check that time changes...
    @Test
    public void CheckTimeChanges() {
        for( int mins = 1; mins <= 5; mins++ ) {
            Time time = new Time(mins);
            for( int x = 1; x < mins; x++ ) {
                time.add(1);
                assertTrue( time.getTime() >= x );
            }
            try { Thread.sleep(2000); } catch (Exception e) { }
            time.Change();
            assertTrue( time.getTime() >= 1 );
        }
    }
 */

}

