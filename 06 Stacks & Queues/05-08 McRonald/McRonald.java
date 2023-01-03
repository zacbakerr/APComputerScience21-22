//Updated on 12.14.2020 v2

//Name:   Date:
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   //public static final int numberOfServiceWindows = 3;  //for McRonald 3
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file   
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
      
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      Queue<Customer> customersQueue = new LinkedList<>();
      int count = 0;
      int tempLargeSize = 0;
 
      for (int i = 1; i <= TIME; i++) {
         if (Math.random() < CHANCE_OF_CUSTOMER) {
            customersQueue.add(new Customer(i));
            customers += 1;
            tempLargeSize += 1;    
         }
         if (customersQueue.peek() != null) {
            if (customersQueue.peek().orderComplexity() <= count + 1) {
               int tempPeek = customersQueue.peek().arrived();
               totalMinutes += (i - tempPeek);
               if (i - tempPeek > longestWaitTime) {
                  longestWaitTime = i - tempPeek;
               }
               customersQueue.remove();
               tempLargeSize -= 1;
               count = 0;
            }
            count += 1;
         }
         displayTimeAndQueue(customersQueue, i);

         if (customersQueue.peek() != null) {
            int tempPeek = customersQueue.peek().orderComplexity();
            outfile.println("    " + customersQueue.peek() + " is now being served for " + (tempPeek - count) + " minutes.");
         }
         
         if (tempLargeSize > longestQueue) {
            longestQueue = tempLargeSize;
         }
      }
      
      int minutesCount = TIME + 1;
      
      while (customersQueue.peek() != null) {
         displayTimeAndQueue(customersQueue, minutesCount);
         if (customersQueue.peek().orderComplexity() <= count + 1) {
            customersQueue.remove();
            count = 0;
         }
         if (customersQueue.peek() == null) {
            break;
         }
         count += 1;
         int tempPeek = customersQueue.peek().orderComplexity();
         outfile.println("    " + customersQueue.peek() + " is now being served for " + (tempPeek - count) + " minutes.");
         
         minutesCount += 1;
      }
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
      public Customer (int arrived) {
         arrivedAt = arrived;
         orderAndBeServed = (int)(Math.random() * 6 + 2);
      }
      
      public String toString() {
         return String.valueOf(arrivedAt);
      }
      
      public int orderComplexity() {
         return orderAndBeServed;
      }
      
      public int arrived() {
         return arrivedAt;
      }
   }
}