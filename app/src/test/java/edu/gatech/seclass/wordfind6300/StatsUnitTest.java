package edu.gatech.seclass.wordfind6300;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StatsUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkStats(){
        Stats checker = new Stats(53, 4, 13);

    }

    @Test
    public void checkGetScore(){
        Stats checker = new Stats(53, 4, 13);
        assertEquals(53, checker.getScore().intValue()  );
    }

    @Test
    public void checkGetResets(){
        Stats checker = new Stats(53, 4, 13);
        assertEquals(4, checker.getResets().intValue()  );

    }

    @Test
    public void checkGetWords(){
        Stats checker = new Stats(53, 4, 13);
        assertEquals(13, checker.getWords().intValue()  );
    }

    @Test
    public void checksetNumWords(){
        Stats checker = new Stats(53, 4, 13);
        checker.setNumWords(16);
        assertEquals(16, checker.getWords().intValue());
    }

    @Test
    public void checkIncResets(){
        Stats checker = new Stats(53, 4, 13);
        checker.incResets();;
        assertEquals(5, checker.getResets().intValue());
    }

    @Test
    public void checkSetFinalScore(){
        Stats checker = new Stats(53, 4, 13);
        checker.setFinalScore(100);
        assertEquals(100, checker.getScore().intValue());

    }


}

