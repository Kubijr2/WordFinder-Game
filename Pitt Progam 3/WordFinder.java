// CMPINF 0401 Fall 2023
// Assignment 2 WordFinder class
// You must complete the implementation of this class.  You will need
// to use an instance of the Dictionary class within this class.  See
// Dictionary.java for details on the Dictionary class.

public class WordFinder
{
	private Dictionary DD;
   private String word;
   
   // Think about the instance variables that you will need for this class.
	// Minimally you will need a Dictionary and a String.
	
	// Initialize a WordFinder object.  String fileName is the name of a
	// dictionary file from which the Dictionary instance variable will be
	// initialized.
	public WordFinder(String fileName)
	{
      DD = new Dictionary(fileName);
	}
	
	// Obtain and store (with this WordFinder object) a random word from the
	// Dictionary of "size" or more letters.
	public void nextWord(int size)
	{
      word = DD.randWord(size);
	}
	
	// Return the word that was obtained from the last call to nextWord().
	// This method is necessary since the word itself will be stored in a 
	// private instance variable.
	public String showWord()
	{
	   return word;
	}
	
	// This is the most challenging method in this class.  The test argument is
	// a String that will be checked for validity within the current word that was
	// obtained from the Dictionary.  This method should
	//		 
	// return 0 if all of the characters in test are found within the word 
	//		 (such that each letter in the word is used at most one time) and if 
	//		 test is also a valid word in the Dictionary.  
	// return 1 if test cannot be generated in a valid way from the letters in
	//		 the current word
	// return 2 if test can be generated in a valid way from the letters in the
	//		 current word, BUT test is not a real word in the Dictionary
	//
	// Think about how you will do this and consult the Java API for
	// some ideas (in particular look at methods in the StringBuilder class)
	public int goodWord(String test)
	{   
      boolean each;
      boolean contains = true;
      char c;
      String currentGuess = word;
      
      // Test each character in the guess with each character in the word that it is guess from 
      for (int i = 0; i < test.length(); i ++)
      {
         c = test.charAt(i);
         each = false;
         
         for(int x = 0; x < currentGuess.length(); x++)
         {
            char compareC = currentGuess.charAt(x);
            if (Character.compare(c, compareC) == 0)
            {
               each = true;
               currentGuess = currentGuess.substring(0, x) + currentGuess.substring(x+1, currentGuess.length());
               break;
            }
         }
         
         if (!each)
         {
            contains = false;
         }
      }
      
      // If contained in word and dictionary return 0
      if (contains)
      {
         if (DD.contains(test))
         {
            return 0;
         }
         
         // If contained in word but not dictionary return 2
         else
         {
            return 2;
         }
      }
      
      // If not contained in word return 1
      else
      {
         return 1;
      }
	}
}