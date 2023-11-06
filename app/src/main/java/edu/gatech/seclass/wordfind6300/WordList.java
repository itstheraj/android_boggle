package edu.gatech.seclass.wordfind6300;

//import java.util.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

/* vim: set tabstop=4 softtabstop=4 shiftwidth=4 noexpandtab : */

public class WordList
{

	public class WordFreq
	{
		public String word;
		public int freak;
		public WordFreq(String word, int freq) { this.word = word; this.freak = freq; }
	};

	HashMap<String,Integer> wordsPlayed;

	//ArrayList 
	//Vector words;
	List<String> words;
	HashMap<String,WordFreq> wordmap;


	public WordList() {
		wordmap = new HashMap<String,WordFreq>();
		words = new ArrayList<>();
		//words = new Vector<WordFreq>(16, 16);
	}

	// void showStats();
	// void sortStats();
	public void sortStats()
	{
		return;
	}

	public List<String> getWords()
	{
		return words;
	}

	public HashMap<String, WordFreq> getWordMap()
	{
		return wordmap;
	}

	public String getMostPlayed()
	{
		String mostly = "";
		int max = 0;

		Set wordset = wordmap.entrySet();
		Iterator iter = wordset.iterator();
		// for each element of words
		for (HashMap.Entry<String,WordFreq> entry : wordmap.entrySet()) {
		//for(iter.hasNext()) {
			if( entry.getValue().freak > max ) {
				mostly = entry.getKey();
				max = entry.getValue().freak;
			}
			//Map.Entry entry = (Map.Entry)iter.next();
			System.out.print("Word: "+ entry.getKey() + ", Count: " + entry.getValue());
		}
		return mostly;
	}

	public void append(String word)
	{
		WordFreq val;
		if ( this.wordmap.containsKey(word) ) {
			val = this.wordmap.get(word);
			val.freak += 1;
		}
		else
		{
			val = new WordFreq(word, 1);
		}
		//words.addElement(new WordFreq(word,freq));
		System.out.println("Value: "+word+" is: "+ val.freak);
		this.wordmap.put(word, val);

		words.add(word);
		return;
	}


//Set set = hmap.entrySet();
//Iterator iterator = set.iterator();
//while(iterator.hasNext()) {
//	Map.Entry mentry = (Map.Entry)iterator.next();
//	System.out.print("key is: "+ mentry.getKey() + " & Value is: " + mentry.getValue());
//}

}

