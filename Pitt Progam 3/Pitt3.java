// Cole Kubistek Pd. 9
// Pitt Assigntment #3

import java.util.Scanner;
import java.io.*;

public class Pitt3
{
   public static void main(String[] args) throws IOException
   {
      // Utilize other classes
      Scanner keyboard = new Scanner(System.in);
      WordFinder WF = new WordFinder("dictionary.txt");
      MyTimer time = new MyTimer();
      PlayerList players = new PlayerList("players.txt", 2);
      Player currentPlayer = new Player();
      time.set(60000);
      
      // Establish variables for the final stats
      int totalUsers = 0;
      int fullTotalRounds = 0;
      int fullTotalWords = 0;
      int fullTotalPoints = 0;
      double fullAverageWords = 0;
      double fullAveragePoints = 0;
            
      // Establish other necessary variables
      String name = "";
      int play = 1;
      String guess = "";
      int times = 0;
      boolean newbie = false;
      
      // Main loop for playing      
      do {
         // Directions to play
         currentPlayer = null;
         currentPlayer = new Player();
         System.out.println("Here are the Word Finder Game Directions:");
         System.out.println("\tFor each round you will see a randomly selected word.");
         System.out.println("\tYou will have 1 minute to find as many words as you can");
         System.out.println("\tthat can be generated with the letters in your random");
         System.out.println("\tword. Your words do not have to use all of the letters");
         System.out.println("\tin the word but they must be valid words. You will receive");
         System.out.println("\ta point for each letter in each word, so longer words count");
         System.out.println("\tmore than shorter words.");
         System.out.println("\nPlease sign in with your id and password");
         System.out.println("If you are a new player, leave your id field blank");
         System.out.println("and we will set you up with a new account");
         System.out.println("If you would like to end the game, enter 'Quit'\n");
         System.out.print("\tId: ");
         if (times >= 1)
         {
            keyboard.nextLine();
         }
         name = keyboard.nextLine();
         if (name.equals("Quit"))
         {
            break;
         }
         else if (name.equals("") || players.containsId(name) == false)
         {
            // If they're a new player set up an account with an ID and a password 
            newbie = true;
            System.out.println("Welcome new Player!");
            System.out.println("Before playing we must get some information from you");
            System.out.print("\tPlease enter an ID for your account (no spaces): ");
            String newId = keyboard.nextLine();
            while (newId.equals(""))
            {
               System.out.print("Please enter an actual ID that isnt just a space bro: ");
               newId = keyboard.nextLine();
            }
            while (players.containsId(newId) == true)
            {
               // ID cannot already be taken
               System.out.println("\tSorry but that ID is taken. Please try again");
               System.out.print("\tID: ");
               newId = keyboard.nextLine();
            }
            name = newId;
            String pass1 = "";
            String pass2 = "";
            System.out.print("\tPlease enter your password: ");
            pass1 = keyboard.nextLine();
            System.out.print("\tConfirm password: ");
            pass2 = keyboard.nextLine();
            while (pass1.equals(pass2) == false)
            {
               // Passwords must match each other to confirm
               System.out.println("\tSorry but your passwords don't match!");
               System.out.print("\tPlease enter your password: ");
               pass1 = keyboard.nextLine();
               System.out.print("\tConfirm password: ");
               pass2 = keyboard.nextLine();
            }
            currentPlayer.setId(newId);
            currentPlayer.setPass(pass1);
         }
         else if (players.containsId(name))
         {
            // If player has an account ask for their password to confirm its them
            newbie = false;
            String pass = "";
            System.out.print("\tPlease enter your password: ");
            pass = keyboard.nextLine();
            Player checkPlayer = new Player(name);
            checkPlayer.setPass(pass);
            Player returningPlayer = players.authenticate(checkPlayer);
            if (returningPlayer == null)
            {
               // Give them a second try if their password is wrong on the first try
               System.out.println("\tSorry, your password does not match. Please try again.");
               System.out.print("\tPlease enter your password: ");
               pass = keyboard.nextLine();
               checkPlayer.setPass(pass);
               returningPlayer = players.authenticate(checkPlayer);
               if (returningPlayer == null)
               {
                  // If they get the password wrong twice then cancel the sign in and make them make a new account
                  newbie = true;
                  System.out.println("Your password still does not match, so your sign-in has been");
                  System.out.println("canceled. .Please register as a new player");
                  System.out.println("Welcome new Player!");
                  System.out.println("Before playing we must get some information from you");
                  System.out.print("\tPlease enter an ID for your account (no spaces): ");
                  String newId = keyboard.nextLine();
                  while (players.containsId(newId) == true)
                  {
                     // ID can't already be used
                     System.out.println("\tSorry but that ID is taken. Please try again");
                     System.out.print("\tID: ");
                     newId = keyboard.nextLine();
                  }
                  name = newId;
                  String pass1 = "";
                  String pass2 = "";
                  System.out.print("\tPlease enter your password: ");
                  pass1 = keyboard.nextLine();
                  System.out.print("\tConfirm password: ");
                  pass2 = keyboard.nextLine();
                  while (pass1.equals(pass2) == false)
                  {
                     // Passwords gotta match to confrim
                     System.out.println("\tSorry but your passwords don't match!");
                     System.out.print("\tPlease enter your password: ");
                     pass1 = keyboard.nextLine();
                     System.out.print("\tConfirm password: ");
                     pass2 = keyboard.nextLine();
                  }
                  currentPlayer.setId(newId);
                  currentPlayer.setPass(pass1);
               }
               else
               {
                  currentPlayer = returningPlayer;
               }
            }
            else
            {
               currentPlayer = returningPlayer;
            }
         }
         
         // To fix a weird output error caused nextLine
         if (fullTotalRounds >= 1)
         {
            name = keyboard.nextLine();
         }
         System.out.println("Welcome " + name + " to Word Finder!");
         System.out.print("Would you like to play (yes = 1, no = any other number): ");
         play = keyboard.nextInt();
         keyboard.nextLine();
         
         if (play == 1)
         {
            // Establish variables for the current player
            totalUsers += 1;
            int rounds = 0;
            int totalValid = 0;
            int totalPoints = 0;
            double averageWordsPerRound = 0;
            double averagePointsPerRound = 0;
            int samePlay = 0;
            
            do{
               // Start the timer and get the word
               time.start();
               WF.nextWord(7);
               String word = WF.showWord();
               int validCount = 0;
               int points = 0;
               Dictionary guessList = new Dictionary();
               
               do {
                  System.out.print("Word: "+ word + "     Guess? ");
                  guess = keyboard.nextLine();
                  
                  // Judge if the word is valid or not
                  int valid = WF.goodWord(guess);
                  if (valid == 0)
                  {
                     // If the word was already used it can't be used again
                     if (guessList.contains(guess))
                     {
                        System.out.println("\t\t" + guess + " is a duplicate word");
                     }
                     
                     // If the word wasn't already used and its valid then add it to points and word count and list of valid words
                     else
                     {
                        validCount += 1;
                        System.out.println("\t\tAnswer " + validCount + " is " + guess);
                        points += guess.length();
                        guessList.addWord(guess);
                     }
                  }
                  
                  // If guess can't be formed from the word
                  else if (valid == 1)
                  {
                     System.out.println("\t\t" + guess + " cannot be formed from " + word);
                  }
                  
                  // If the guess isn't a valid word in the dictionary
                  else
                  {
                     System.out.println("\t\t" + guess + " is not a legal word in the dictionary");
                  }
                  
               } while (time.check() == true);
               
               // Times up
               System.out.println("Sorry, TIMES UP BROOOOO!!!!");
               
               // Calculate the stats for the round
               totalValid += validCount;
               fullTotalWords += totalValid;
               totalPoints += points;
               fullTotalPoints += totalPoints;
               System.out.println("You found " + validCount + " words!");
               System.out.println("You earned " + points + " points!");
               System.out.println("Here are your words: ");
               System.out.println(guessList);
               
               
               // Ask if they want to play again
               System.out.print("\n\nWould you like to play again (yes = 1, no = any other number): ");
               samePlay = keyboard.nextInt();
               rounds += 1;
               fullTotalRounds += 1;
            } while(samePlay == 1);
            
            // Calculate averages
            averageWordsPerRound = (double)totalValid / (double)rounds;
            averagePointsPerRound = (double)totalPoints / (double)rounds;
            
            // When done show the stats
            System.out.println("Thanks for playing " + name + "!");
            System.out.println("Here are your results: ");
            System.out.println("In this game: ");
            System.out.println("\tRounds played: " + rounds);
            System.out.println("\tTotal words found: " + totalValid);
            System.out.println("\tTotal points: " + totalPoints);
            System.out.println("\tAverage words per round: " + averageWordsPerRound);
            System.out.println("\tAverage points per round: " + averagePointsPerRound);
            if (newbie == false)
            {
               currentPlayer.addRounds(rounds);
               currentPlayer.addWords(totalValid);
               currentPlayer.addPoints(totalPoints);
            }
            else
            {
               currentPlayer.addRounds(rounds);
               currentPlayer.addWords(totalValid);
               currentPlayer.addPoints(totalPoints);
               players.addPlayer(currentPlayer);
            }
            System.out.println("Overall, here are your stats: ");
            System.out.println(currentPlayer.toString());
            times += 1;
         }
         
      } while (play == 1 || name.equals("Quit") == false);
      
      // If they dont want to play again end the program
      // Display the total stats of everyone in the file
      fullAverageWords = (double)fullTotalWords / (double)fullTotalRounds;
      fullAveragePoints = (double)fullTotalPoints / (double)fullTotalRounds;
      System.out.println("\nGame over. Here are the overall stats:");
      System.out.println(players.toString());
      System.out.println("Saving the players back to the file...");
      players.saveList();
   }
}