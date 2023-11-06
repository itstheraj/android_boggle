package edu.gatech.seclass.wordfind6300;

public class Letter implements LetterInterface {

    //variables
    String letter;
    int weight;


    //constructor
    public Letter()
    {
        letter = "";
        weight = 1;
    }

    public Letter(String value, int letterWeight){
        if( letterWeight < 1 || letterWeight > 5) { letterWeight = 1; }
        this.letter = value;
        this.weight = letterWeight;

    }

    //methods
    //@Override
    public void changeWeight(int newWeight) {
        weight = newWeight;
    }

    public int getWeight(){
        return weight;
    }

    public String getLetter(){
        return letter;
    }

}
