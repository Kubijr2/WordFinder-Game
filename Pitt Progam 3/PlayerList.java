// CMPINF 0401 Fall 2023
// PlayerList class.
// This class represents a collection of Players -- a very simple database.  The
// collection can be represented in various ways but in Assignment 3 you are required
// to use a Java array of Player, as shown below.  You may add other instance variables
// but you may NOT use an ArrayList (or other collection) for your data.

// Note the import below.  java.io.* is needed for IOException and Files. 
import java.io.*;
import java.util.Scanner;

public class PlayerList
{
	// Keep an array of Player objects to store the players within this PlayerList and
	// an int to maintain the logical size (number of actual players stored).
	// Also keep track of the file name associated with the PlayerList so that it can
	// be saved back.
	private Player [] players;
	private int num;
	private String fileName;
	
	// Initialize the list from a file.  Note that the file name is a parameter.  You
	// must open the file, read the data, making a new Player object for each line and
	// adding them into the array (resizing the array when necessary).  The second
	// argument is the initial capacity of the underlying array (i.e. the length of
	// the array before any adds are done).  
	
	// Note that this method throws IOException. Because of this, any method that CALLS
	// this method must also either catch IOException or throw IOException.  Note that
	// the main() in PlayerListTest.java throws IOException.  Keep this in mind for your 
	// main program (Assig3.java).  Note that your saveList() method will also need
	// "throws IOException" in its header.
	public PlayerList(String fName, int cap) throws IOException
	{
		fileName = fName;
      File tFile = new File(fileName);
      Scanner begin = new Scanner(tFile);
      int totalLines = 0;
      while (begin.hasNext())
      {
         String test = begin.nextLine();
         totalLines += 1;
      }
      int fullCapacity = cap + totalLines;
      players = new Player[fullCapacity];
      begin.close();
      File tFile2 = new File(fileName);
      Scanner begin2 = new Scanner(tFile2);
      int amount = 0;
      while (begin2.hasNext())
      {
         String line = begin2.nextLine();
         String[] t = line.split(",");
         players[amount] = new Player(t[0], t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3]), Integer.parseInt(t[4]));
         amount += 1;
      }
      num = amount;
      begin2.close();
	}
   

	// See program PlayerListTest.java to see the other methods that you will need for
	// your PlayerList class.  There are a lot of comments in that program explaining
	// the required effects of the methods.  Read that program very carefully before
	// completing the PlayerList class.  You will also need to complete the Player class
	// before the PlayerList class will work, since your array is an array of
	// Player objects.
	
	// You may also need some methods that are not tested in PlayerListTest.java.  Think
	// about what you need to do to a PlayerList in your Assig3 program and write the methods
	// to achieve those tasks.
   
   // Size method returns number of players
   public int size()
   {
      return num;
   }
   
   // Capacity method returns length of array
   public int capacity()
   {
      return players.length;
   }
   
   // Make super super long string
   // Strings can be String = String + something else
   public String toString()
   {
      String display = "Total players: " + num;
      for (int x = 0; x < num; x++)
      {
         // Print out all the stats of players in the player list
         display = display + "\n\tId: " + players[x].getId();
         display = display + "\n\tTotal Rounds Played: " + players[x].getRoundsPlayed();
         display = display + "\n\tTotal Words Found: " + players[x].getWordsFound();
         display = display + "\n\tTotal Points Earned: " + players[x].getPoints();
         display = display + "\n\tAve Words Found: " + players[x].getAverageWords();
         display = display + "\n\tAve Points Earned: " + players[x].getAveragePoints() + "\n";
      }
      // Get overall statistics
      display = display + "\nOverall statistics: ";
      int totalR = 0, totalW = 0, totalP = 0;
      for (int i = 0; i < num; i++)
      {
         totalR += players[i].getRoundsPlayed();
         totalW += players[i].getWordsFound();
         totalP += players[i].getPoints();
      }
      double aveW = (double)totalW / (double)totalR;
      double aveP = (double)totalP / (double)totalR;
      
      // Print overall statistics
      display = display + "\n\tTotal Rounds: " + totalR;
      display = display + "\n\tTotal Words: " + totalW;
      display = display + "\n\tTotal Points: " + totalP;
      display = display + "\n\tAverage Words: " + aveW;
      display = display + "\n\tAverage Points: " + aveP + "\n";
      
      // Return the whole long string
      return display;
   }
      
   
   // Contains ID method to return true or false if the existing list contains the presented ID
   public boolean containsId(String idCheck)
   {
      boolean contains = false;
      for (int x = 0; x < num; x++)
      {
         String nextID = players[x].getId();
         if (idCheck.equals(nextID))
         {
            contains = true;
         }
      }
      return contains;
   }
   
   // Returns true when it adds a new player to the list
   public boolean addPlayer(Player p)
   {
      boolean confirm = false;
      players[num] = p;
      num += 1;
      if (num == players.length)
      {
         // Resize the array if the array maxes out with players
         Player[] resizedPlayers = new Player[(num - 1) * 2];
         for (int i = 0; i < players.length; i++)
         {
            resizedPlayers[i] = players[i];
         }
         players = resizedPlayers;
      }
      if (containsId(p.getId()) == true)
      {
         confirm = true;
      }
      return confirm;
   }
   
   // Checks to see if the password matches the ID
   public Player authenticate(Player passed)
   {
      Player authenticated = null;
      for (int i = 0; i < num; i++)
      {
         if (players[i].getId().equals(passed.getId()) && players[i].getPass().equals(passed.getPass()))
         {
            authenticated = players[i];
         }
      }
      return authenticated;
      }
   
   // Saves any updates into the file
   public void saveList() throws IOException
   {
      PrintWriter outputFile = new PrintWriter(fileName);
      for (int x = 0; x < num; x++)
      {
         outputFile.println(players[x].getId() + "," + players[x].getPass() + "," + players[x].getRoundsPlayed() + "," + 
         players[x].getWordsFound() + "," + players[x].getPoints());
      }
      outputFile.close();
   }
}

// id + "," + password + "," + roundsPlayed + "," + wordsFound + "," + points