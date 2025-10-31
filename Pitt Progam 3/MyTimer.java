// CS 401 Fall 2023
// Assignment 2 MyTimer class
// Use this class to add a timer to your word finder program.  For some help on 
// using this class, see program TimerTest.java

// Note: There are some things (such as Threads and synchronized methods) in this
// implementation with which you may not be familiar.  However, as we discussed
// in lecture, due to data abstraction, you do not need to know these implementation
// details in order to utilize this class.  You simply need to understand what this
// class does in general, what the methods do and their headers (specifications).
// Therefore, for Assignment 2 focus on the descriptions of the methods rather than
// on the actual code within them.

public class MyTimer implements Runnable
{
	private Thread theThread;
	private boolean running;
	private long delta;
	
	// Initialize a new MyTimer object.  This will simply create the object and
	// initialize variables to default values.
	public MyTimer()
	{
		running = false;
		theThread = null;
	}
	
	// Set the duration of the timer.  This determines how long the timer will
	// run before it expires.  Note that the time is in milliseconds.
	public synchronized void set(long millis)
	{
		if (!running)
		{
			delta = millis;
		}
		else
			System.out.println("Cannot set timer while running");
	}
	
	// Start the timer running.  It will continue to run until the set time
	// expires.
	public synchronized void start()
	{
		if (!running)
		{
			running = true;
			new Thread(this).start();
		}
	}
	
	// This method will return true as long as the timer is running.  It will
	// return false when the timer is not running.
	public synchronized boolean check()
	{
		return running;
	}
	
	// This method is necessary for the timer but the user of the timer does
	// not need to know it -- it is abstracted out.
	public void run()
	{
		try 
		{
			Thread.sleep(delta);
		}
		catch (InterruptedException e)
		{
			System.out.println("Thread Error");
		}
		running = false;
	}		
}