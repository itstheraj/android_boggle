package edu.gatech.seclass.wordfind6300;

import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class Board implements BoardInterface {

    private int size;
    private String[][] letters;
    private boolean[][] usedCells; //move to Game?
    private String[] consBank;
    private String[] vowelBank;
    private String currentWord;
    private int lastMoveRow, lastMoveCol;

    public Board(int size, String[] cons, String[] vowels) {
        //System.out.println("new board:"+size);
        if( (size < 4 ) || (size > 8) ) { size = 4; }
        System.out.println("new board:"+size);
        this.size = size;
        this.consBank = cons;
        this.vowelBank = vowels;
        this.letters = new String[this.size][this.size];
        this.usedCells = new boolean[this.size][this.size];
        this.currentWord = "";
        this.lastMoveCol = -2;
        this.lastMoveRow = -2;
        getLetters();
    }

    public String[][] getLetterArr()
    {
        return letters;
    }

    public void getLetters() {
        // add 1/5 (ceiling) vowels, rest consonants and place into letters 2d array
        /*
        int v = 0, c = 0;
        int totalCells = this.size * this.size;
        int vowelCount = (int) Math.ceil(totalCells);
        int consCount = totalCells - vowelCount;
        for (int i = 0; i < this.letters.length - 1; i++)
            for (int j = 0; j < this.letters[i].length - 1; j++) {
                if ((int) (Math.random() * 5) == 0 && vowelCount > 0 || consCount == 0) {
                    this.letters[i][j] = this.vowelBank[v++];
                    vowelCount--;
                } else {
                    this.letters[i][j] = this.consBank[c++];
                    consCount--;
                }
            }

        */
        // Want to find total cells, divide by 5 to get total vc
        int totalC = size * size;
        int vCount = ((totalC + 4)/ 5); //round up
        int cCount = totalC - vCount;

        // WT = 1 get A E I O U for vowelbank
        // board size 64 / 5 = 12 vowels, which is more than is in bank.
        // how to manage? pick random index in vowel list that many times.

        // Can I assume VowelBank and ConsBank have right num letters? Yes.

        // Now want to uniformly sample from the vowel bank with removal
        // to do this we have A E E I O U U potentially, want to pick random one, and remove it
        // from the bank, dont have to use all of them but have to go up to vCount

        String[] totalLetters = new String[totalC];

        for (int i = 0; i < vCount; i++)
        {
            int randIndex = (int) (Math.random() * vowelBank.length);
            totalLetters[i] = vowelBank[randIndex];
        }
        for (int i = vCount; i <  totalC; i++)
        {
            int randIndex = (int) (Math.random() * consBank.length);
            totalLetters[i] = consBank[randIndex];
        }

        //for (String str : totalLetters)
        //{
        //    System.out.println("c:"+str);
        //}

        System.out.println("letters.total:"+totalLetters.length);

        List<String> finalList = Arrays.asList(totalLetters);
        Collections.shuffle(finalList);

        for (int i = 1; i < letters.length + 1; i++)
        {
            for (int j = 1; j < letters[0].length + 1; j++)
            {
                String symbol = finalList.get((j-1) + letters.length * (i - 1));
//finalList.get((j-1) + letters.length * (i - 1));
                letters[i - 1][j - 1] = symbol;
                System.out.print(symbol+" ");
            }
            System.out.println("");
        }

    }

    public boolean pickLetter(int row, int col){
        if( row>= size || col >= size ) {
            return false; //OOB
        }
        if(!this.checkValid(row, col)) {
            //System.out.println("r:"+row+",c:"+col);
            //System.out.println("prev, r:"+this.lastMoveRow+",c:"+this.lastMoveCol);
            return false; // Don't allow picking oob (row,column)
        }
        if(this.usedCells[row][col]) {
            return false; //don't let them select it... un-select it??
        }
        if(!this.usedCells[row][col]) {
            //highlight cell?
            this.currentWord += this.letters[row][col];
            this.usedCells[row][col] = true;
            this.lastMoveRow = row;
            this.lastMoveCol = col;
            return true;
        }
        return false;
    }

    //are cells adjacent (neighbors)
    public boolean adjacent( int row, int col, int prow, int pcol ) {
        int drow = Math.abs(row-prow);
        int dcol = Math.abs(col-pcol);
        if( (row == prow) && (col == pcol) ) return false;
        if( drow == 0 ) {
            if( dcol == 1 ) return true;
            return false;
        }
        if( row == prow + 1 ) {
            if( dcol == 0 ) return true;
            if( dcol == 1 ) return true;
            return false;
        }
        if( row == prow - 1 ) {
            if( dcol == 0 ) return true;
            if( dcol == 1 ) return true;
            return false;
        }
        return false;
    }
    private boolean checkValid(int row, int col) {
        //check OOB
        if( row < 0 || col < 0 ) {
            return false;
        }
        if( row >= size || col >= size ) {
            return false;
        }
        if( (this.lastMoveRow < 0) && (this.lastMoveCol < 0) )
            return true;
        if( this.adjacent( row, col, this.lastMoveRow, this.lastMoveCol ) ) {
            return true;
        }
        return false;
    }

    public void setCurrentWord(String curr)
    {
        currentWord = curr;
    }

    public String getCurrentWord() {
        return this.currentWord;
    }

    public void resetCurrentWord(){
        this.currentWord = "";
        for (int i = 0; i < this.usedCells.length; i++) {
            for (int j = 0; j < this.usedCells[i].length; j++) {
                this.usedCells[i][j] = false;
            }
        }
        this.lastMoveRow = -2;
        this.lastMoveCol = -2;
    }

    // Testing interface

    //for automated testing
    public int getSize() {
        return this.size;
    }

    //for automated testing
    public int countVowels() {
        int count = 0;
        //String bank = String.join(this.vowelBank);
        String bank = "";
        for( int x = 0; x < this.vowelBank.length; x++ ) {
            bank = bank + this.vowelBank[x];
        }
        for( int rn = 0; rn < this.size; rn++ ) {
            for( int cn = 0; cn < this.size; cn++ ) {
                if( bank.indexOf(this.letters[rn][cn]) >= 0 ) {
                    ++count;
                }
            }
        }
        return count;
    }

    //for automated testing
    public int countConsonants() {
        int count = 0;
        //String bank = String.join(this.consBank);
        String bank = "";
        for( int x = 0; x < this.consBank.length; x++ ) {
            bank = bank + this.consBank[x];
        }
        for( int rn = 0; rn < this.size; rn++ ) {
            for( int cn = 0; cn < this.size; cn++ ) {
                if( bank.indexOf(this.letters[rn][cn]) >= 0 ) {
                    ++count;
                }
            }
        }
        return count;
    }

}

