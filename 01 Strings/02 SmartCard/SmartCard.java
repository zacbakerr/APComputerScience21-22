//Name: Zac Baker    
//Date: 08/27/21
//Pd 5

import java.text.DecimalFormat;

public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   /* enter the private fields */
   private double money = 0;
   private Station station = null;

   private double zone2 = 0;
   private String zone2name = null;

   private boolean isBoarded = false;
   
   /* the one-arg constructor  */
   public SmartCard(double initBalance)
   {
      money = initBalance;
   }

   //these three getter methods only return your private data
   //they do not make any changes to your data
   public boolean getIsBoarded() 
   { 
     return isBoarded;
   }
   
   public double getBalance()
   {
      return money;
   }

   public String getFormattedBalance() {
      return("$" + money);
   }
         
   public Station getBoardedAt()
   {
      return station;
   }
    
   /* write the instance methods  */
   void board(Station s) {
      if(isBoarded == true) {
         System.out.print("Error: already boarded?!");
         return;
      }
      if(money < .5) {
         System.out.print("Insufficient funds to board. Please add more money.");
         return;
      }
      isBoarded = true;
      zone2 = s.getZone();
      zone2name = s.getName();
   }

   double cost(Station s) {
      int zone = s.getZone();

      double cost = (Math.abs(zone - zone2))*0.75+0.50;
      return cost;
   }
   
   void exit(Station s) {
      double cost = cost(s);
      String zonename = s.getName();
      

      if(isBoarded == false) {
         System.out.println("Error: Did not board?!");
         return;
      }
      else if(cost > money) {
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
      } 
      else {
         
         money -= cost;
         System.out.println("From " + zone2name + " to " + zonename + " costs " + cost + ". Smartcard has " + getFormattedBalance());
         isBoarded = false;
         return;
      }
      }

   void addMoney(double d) {
      money += d;
   }
   
    

}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station
{
   private String name;
   public int zonenumber;

   public Station() {
      name = null;
      zonenumber = 0;
   }

   public Station(String stationname, int zone) {
      name = stationname;
      zonenumber = zone;
   }

   public int getZone() {
      return zonenumber;
   }

   public String getName() {
      return name;
   }
}