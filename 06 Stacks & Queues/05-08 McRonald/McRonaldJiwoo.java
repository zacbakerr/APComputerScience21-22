
import java.util.*;
import java.io.*;
public class McRonaldJiwoo
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
          outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceAreaaaaa.txt"));
       }
       catch(IOException e)
       {
          System.out.println("File not created");
          System.exit(0);
       }
      
       mcRonaldJiwoo(TIME, outfile);   //run the simulation
      
       outfile.close(); 
    }
   
    public static void mcRonaldJiwoo(int TIME, PrintWriter of)
    {
       /***************************************
            Write your code for the simulation  
       **********************************/
       Queue<Customer> que = new LinkedList<Customer>();
       Customer current = null;
       int servingTime = 0;
       int extraWait = 0;
       int timeArrived = 0;
       int totalWait = 0;
       int totalTime = 0; 
          
       for(int i = 0; i < TIME; i++)
       {
          // handles randomly generating customers
          if(Math.random() < CHANCE_OF_CUSTOMER)
          {
             Customer c = new Customer(i, timeToOrderAndBeServed());
             que.add(c);
             customers++;
             outfile.println("new customer min#" + i);
          }      
         
      
          if(!que.isEmpty() && servingTime == 0)
          {
         
             if(que.size() > longestQueue)
                longestQueue = que.size();
         
             current = que.remove();
             timeArrived = current.getArrivedAt();
             extraWait = i - timeArrived;
             servingTime = current.getOrderAndBeServed();
            
             totalWait = extraWait + servingTime;
             totalTime = totalWait + timeArrived;
            
             totalMinutes += totalWait;
            
             if(totalWait > longestWaitTime)
                longestWaitTime = totalWait;
          }
         
          if(servingTime == 0 && totalWait != 0)
          {
             outfile.println("Customer#" + timeArrived + " leaves and waited a total of " + totalWait + " minutes.");
             totalWait = 0;
          }
      
          outfile.println(i + ": " + que);
         
          if(que.isEmpty() && servingTime == 0)
             outfile.println("\tServiceArea#1 " + -1 + ":" + -1);
         
          if(servingTime > 0)
          {
             outfile.println("\tServiceArea#1 " + timeArrived + ":" + servingTime);           
             servingTime--;
          }  
         
          if(totalTime == i && i != 0)
          {
                           
             if(!que.isEmpty() || current == null)
                current = que.peek();               
          }
          
          outfile.println("");
          outfile.println("");
      
         
          // handles customer
                    
               
       }
      
        
        
             
       /*   report the data to the screen    */ 
       System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
       System.out.println("Total customers served = " + getCustomers());
      
       System.out.println("Total time: " + totalMinutes);
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
   
       public Customer(int arrival, int order)
       {
          arrivedAt = arrival;
          orderAndBeServed = order;
       }
     
       public int getArrivedAt()
       {
          return arrivedAt;
       }
     
       public int getOrderAndBeServed()
       {
          return orderAndBeServed;
       }
      
       public String toString()
       {
          String line = "";
          line = line + arrivedAt;
         
          return line;
       }
    
    }
 }