// Name: Zac Baker
// Date: 08/25/21
//Pd 5

public class Modes
{
   public static void main(String[] args)
   {
      int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
      //test with other values in the tally
      System.out.println(toString(tally));
      int[] modes = calculateModes(tally);
      System.out.println(toString(modes));
      int sum = 0;
      for(int k = 0; k < tally.length; k++)
         sum += tally[k];
      System.out.println("kth \tindex"); 
      for(int k = 1; k <= sum; k++)
         System.out.println(k + "\t\t" + kthDataValue(tally, k));
   }
     
  /**
   * precondition: tally.length > 0
   * postcondition: returns an int array that contains the modes(s);
   *                the array's length equals the number of modes.
   */
   public static int[] calculateModes(int[] tally)
   {
      int numberofmodes = 0;
      int count = 0;
      int count2 = 0;
      int mode_treshold = findMax(tally);
      int[] modes = new int[]{};


      for(int i = 0; i < tally.length; i++) {
         if (tally[i] == mode_treshold || tally[i] == 0) {
            count2 ++;
         }
         if (tally[i] == mode_treshold) {
            numberofmodes ++;
         }
      }

      modes = new int[numberofmodes];

         for(int i = 0; i<tally.length; i++) {
            if(count2 == tally.length) {
               modes = new int[]{};
               break;
            } 
            if(tally[i]==mode_treshold) {
               modes[count] = i;
               count++; 
            }
         }
         return modes;
   }
     
  /**  
   * precondition:  tally.length > 0
   * 	             0 < k <= total number of values in data collection
   * postcondition: returns the kth value in the data collection
   *                represented by tally
   */
   public static int kthDataValue(int[] tally, int k)
   {
      int kthvalue = 0;

      int sum = 0;
      for(int i = 0; i < tally.length; i++)
         sum += tally[i];

      while(k > 0 && k <= sum){
         k -= tally[kthvalue];
         kthvalue++;
      }
      
      return kthvalue-1;
   }
     
  /**
   * precondition:  nums.length > 0
   * postcondition: returns the maximal value in nums
   */
   public static int findMax(int[] nums)
   {
      int pos = 0;
      for(int k = 1; k < nums.length; k++)
         if(nums[k] > nums[pos])
            pos = k;
      return nums[pos];
   }
   
   public static String toString(int[] arg)
   {
      if(arg.length == 0)
         return("[]");
      String s = "[";
      for(int k = 0; k < arg.length - 1; k++)
         s += arg[k] + ",";
      s += arg[arg.length - 1] + "]";
      return s;
   }  
}

/****************  SAMPLE RUN  **********************

 [0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1]
 [2,4,11]
 kth 	index
 1		2
 2		2
 3		2
 4		2
 5		2
 6		2
 7		2
 8		2
 9		2
 10		2
 11		3
 12		3
 13		3
 14		3
 15		3
 16		4
 17		4
 18		4
 19		4
 20		4
 21		4
 22		4
 23		4
 24		4
 25		4
 26		6
 27		6
 28		6
 29		6
 30		6
 31		6
 32		6
 33		7
 34		9
 35		9
 36		9
 37		9
 38		9
 39		9
 40		11
 41		11
 42		11
 43		11
 44		11
 45		11
 46		11
 47		11
 48		11
 49		11
 50		12
 51		12
 52		12
 53		15
 
****************************************/
