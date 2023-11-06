package edu.gatech.seclass.wordfind6300;

/* vim: set tabstop=4 softtabstop=4 shiftwidth=4 noexpandtab : */

public class Stats {

	private Integer finalScore = 0;
	private Integer numResets = 0;
	private Integer numWords = 0;

	//constructor
	public Stats(int score, int resets, int words) {
		this.finalScore = score;
		this.numResets = resets;
		this.numWords = words;
	}

	public Integer getScore()
	{
		return finalScore;
	}
	public Integer getResets()
	{
		return numResets;
	}
	public Integer getWords()
	{
		return numWords;
	}

	public void setNumWords(int count) {
		if ( count < 0 ) { count = 0; }
		this.numWords = count;
		return;
	}

	public void incResets() {
		this.numResets += 1;
		return;
	}

	public void setFinalScore(int score) {
		if ( score < 0 ) { score = 0; }
		this.finalScore = score;
		return;
	}

}

