// CS 0401 Fall 2023
// Program to test PlayerList and Player classes.  Note the sample output shown
// for this program in file playertest.txt -- two runs are shown as well as some
// other important information (with comments).  In the first run some new players
// are added but the second time none are added since the updated list was saved 
// back to the file.

// Your Player and PlayerList classes can be implemented in various ways and must
// have additional methods to implement your word finder game effectively.  However, the
// required methods below demonstrate how the classes should fundamentally work, and
// your Player and PlayerList classes must produce the same results given the same
// players.txt file.

// For some help with these files you should look at Lab 6 and, in particular the
// Movie class and the MovieDB class.

import java.util.*;
import java.io.*;

public class PlayerListTest
{
	public static void main(String [] args) throws IOException
	{
		System.out.println("Testing PlayerList and Player classes\n");
		
		// Create a PlayerList from a file of players.  The arguments to the constructor
		// are the name of the file and an int representing the initial capacity of the
		// array.  If there is no more space to add a new player during initialization (or
		// at any time), the array size should be doubled.  Note the call to the methods
		// size() and capacity() after the constructor call.  Note also the format of the 
		// file (see players.txt) and that after this constructor all of the player 
		// information is stored within the allPlayers object.  
		PlayerList allPlayers = new PlayerList("players.txt", 2);
		System.out.println("There are: " + allPlayers.size() + " players ");
		System.out.println("The current capacity is: " + allPlayers.capacity());
		System.out.println();
		
		// Note the effect of the toString() method.
		// Parse the output carefully in order to figure out what it entails.  Note that
		// the information for each player is shown, followed by the aggregate
		// statistics for all of the players together -- you will need accessor methods
		// for each Player to get this information for your PlayerList.  Think about
		// what you will need to do.
		System.out.println("Here are the player stats: ");
		System.out.println(allPlayers.toString());
		
		// Here I am making two parallel arrays of ids and passwords to use to test the
		// PlayerList.
		String [] ids = {"Marge", "Fezzik", "Ingmar", "Inigo"};
		String [] passes = {"IHeartCS401", "Sportsmanlike", "Programming!", "YouKeepUsingThatWord"};
		for (int i = 0; i < ids.length; i++)
		{
			String S = ids[i];  // Get the next id and password from the arrays
			String P = passes[i];
			// containsId will return true if the Player's id is within the PlayerList and
			// false otherwise.  This will be useful in your main program.
			boolean found = allPlayers.containsId(S);
			if (found)
			{
				System.out.println(S + " is an id in the PlayerList\n");
			}
			else
			{
				System.out.println(S + " is not in the list -- will be added:");
				// Create a new player with just a String for the id.  The remaining
				// fields should be null or 0
				Player onePlayer = new Player(S);
				// Set the password for the player
				onePlayer.setPass(passes[i]);
				
				// Show player info using the toString() method.  Note that for a new
				// Player (when rounds == 0), no averages are shown via toString(), but
				// for a Player with 1+ rounds, the averages are shown.  Think how you
				// can implement this in the toString() method.
				System.out.println("\tNew player info: ");
				System.out.println(onePlayer.toString());
				// Add the player to the PlayerList
				if (allPlayers.addPlayer(onePlayer))
					System.out.println("has been added to the PlayerList");
				else
					System.out.println("Not added!");
			}
		}
		
		System.out.println("Checking for valid Players");
		for (int i = 0; i < ids.length; i++)
		{
			String S = ids[i];  // Again get ids and passwords from the arrays
			String P = passes[i];
			Player temp = new Player(S);  // Make a new Player and set the password
			temp.setPass(P);
			
			// authenticate() takes a Player argument and checks to see if a Player within
			// the PlayerList matches that Player (by both ID and password).  If a
			// Player matches, return that Player; otherwise return null.  The idea is that
			// we can ask a user to enter an id and password and create a simple Player
			// object out of those.  However, in order to get the rest of the information
			// (game stats) we need to retrieve the Player in question from the PlayerList.
			// authenticate() will do that, provided that the id and password are valid.  If
			// they are not the authenticate() method will return null.  
			
			// The big picture here is that in your main program (game) you will ask a new
			// player to enter their id and password, and will allow them to play if
			// authenticate returns a valid Player.
			Player play = allPlayers.authenticate(temp);
			if (play != null)
			{
				// The authenticate() method will return the reference of a Player within the
				// PlayerList and NOT a copy of the Player.  We discussed this issue in lecture.
				// It may not always be the best idea to do this, but since we have a reference into
				// the same Player that is in the PlayerList, we can mutate it and the effect will
				// occur within the PlayerList.
				System.out.println("Player " + play.toString() + "  has been authenticated and can play!");
				
				// the getId() method allows us to get the id from the Player -- which can be
				// useful during the game.
				System.out.println("Updating Player " + play.getId());
				System.out.println();
				
				// The methods below can be used to update a Player during the game (after each
				// completed round).  You may want some additional mutators as well.  Note
				// carefully what each mutator takes for an argument and see the test output for
				// the effect of each mutator.  In this case I have just made up some constant
				// values, but in your game it will be the actual information for that Player's
				// round.
				play.addRounds(1);
				play.addWords(4);
				play.addPoints(15);
			}
			else // In this example Inigo will not be valid since the password we have
			{	 // for him in the passes[] array does not match the password in the file
				System.out.println("Player " + temp.getId() + " is not in the PlayerList\n");
			}	
		}
		
		// Since two new Players have been added, note that the capacity has once
		// again doubled.
		System.out.println("There are: " + allPlayers.size() + " players ");
		System.out.println("The current capacity is: " + allPlayers.capacity());
		System.out.println();
		
		// Note the format and information returned by the toString() method.  Your
		// output should contain the same, well formatted information.  Note that this
		// call shows ALL of the Players -- this is because the toString() method in the
		// PlayerList class will iterate through all of the Player objects and call the
		// toString() method for each, appending them all into one (very big) StringBuilder.
		// It will then return the single string representing the entire list.  Note also
		// that the toString() method does NOT show the Players' passwords. These are stored
		// in the Player objects but not shown in toString() for security purposes.
		System.out.println("Here are the player stats: ");
		System.out.println(allPlayers.toString());
		
		// Write the players back to a text file so that they can be retrieved later.
		// Clearly to write the Player information to the file, the PlayerList class must
		// be able to get the information from each of the Player objects.  Use this to
		// help you design both the Player and PlayerList classes.  The idea of this method
		// is as follows:  Iterate through the list of players, writing all of the information
		// for each player (including the password) back to the file.  This can be done by
		// defining a method in the Player class such as
		//     public String saveString() {}
		// which will return all of the information from the Player appended together as
		// a single comma separated String.  Note that this is NOT the same as toString(), 
		// since toString() has formatting in it.  saveString() is simply the raw data in
		// the format necessary for the file.
		allPlayers.saveList();
	}		
}