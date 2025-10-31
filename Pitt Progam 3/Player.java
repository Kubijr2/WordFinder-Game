// CMPINF 0401 Fall 2023
// Player class
// You must complete this class for Assignment 3.  It must run in your Assig3.java
// main program and it must also run correctly with the test program
// PlayerListTest.java.

// Some instance variables and constructors are shown.  You may add some additional 
// instance variables if you wish.  You will also need to add the methods that are 
// called in the program PlayerListTest.java.  Read over that program carefully so 
// that you know the method that you need in this class.

// You may also need some methods that are not tested in PlayerListTest.java.  Think
// about what you need to do to a Player in your Assig3 program and write the methods
// to achieve those tasks.

// Note that all of the methods in class are fairly simple since they all deal with
// a SINGLE Player.  The methods that deal with multiple players will be in the
// PlayerList class.

public class Player
{
	private String id, password;  // must store id and password for each player
	private int roundsPlayed, wordsFound, points;  // must also store these
	private double averageWords, averagePoints;					// values for the player stats

	// Default constructor 
	public Player()
	{
		id = null;
		password = null;
		roundsPlayed = 0;
		wordsFound = 0;
		points = 0;
      // averageWords = 0;
      // averagePoints = 0;
	}
	
	// Construct new Player with just ID
	public Player(String newId)
	{
		id = new String(newId);
		password = null;
		roundsPlayed = 0;
		wordsFound = 0;
		points = 0;
      // averageWords = 0;
      // averagePoints = 0;
	}
	
	// Construct new Player with all fields passed in
	public Player(String newid, String pass, int rds, int wds, int pts)
	{
		id = new String(newid);
		password = new String(pass);
		roundsPlayed = rds;
		wordsFound = wds;
		points = pts;
      averageWords = (double)wds / (double)rds;
      averagePoints = (double)pts / (double)rds;
	}

	// You must fill in the remaining methods.  See PlayerListTest.java for the
	// methods that are minimally required.  However, you may certainly add some
	// methods that you will find useful, even if they are not used in the
	// PlayerListTest.java program.
   
   
   // Accessor Methods
   public void setId(String iden)
   {
      id = iden;
   }
   public void setPass(String p)
   {
      password = p;
   }
   public void addRounds(int r)
   {
      if (roundsPlayed == 0)
      {
         roundsPlayed = r;
      }
      else
      {
         roundsPlayed += r;
      }
   }
   public void addWords(int s)
   {
      if (wordsFound == 0)
      {
         wordsFound = s;
      }
      else
      {
         wordsFound += s;
      }
   }
   public void addPoints(int score)
   {
      if (points == 0)
      {
         points = score;
      }
      else
      {
         points += score;
      }
   }
      
   // Mutator Methods
   public String getId()
   {
      return id;
   }
   public String getPass()
   {
      return password;
   }
   public int getRoundsPlayed()
   {
      return roundsPlayed;
   }
   public int getWordsFound()
   {
      return wordsFound;
   }
   public int getPoints()
   {
      return points;
   }
   public double getAverageWords()
   {
      averageWords = (double)wordsFound / (double)roundsPlayed;
      return averageWords;
   }
   public double getAveragePoints()
   {
      averagePoints = (double)points / (double)roundsPlayed;
      return averagePoints;
   }
   
   // ToString method
   public String toString()
   {
      String data = "\tId: " + getId();
      data = data + "\n\tTotal Rounds Played: " + getRoundsPlayed();
      data = data + "\n\tTotal Words Found: " + getWordsFound();
      data = data + "\n\tTotal Points Earned: " + getPoints();
      if (getRoundsPlayed() > 0)
      {
         data = data + "\n\tAve Words Found: " + getAverageWords();
         data = data + "\n\tAve Points Earned: " + getAveragePoints();
      }
      data = data + "\n";
      return data;
   }
   
}


