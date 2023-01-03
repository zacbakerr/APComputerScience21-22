// Name: Zac Baker
// Date: 09/01/21
// Pd: 5
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
      // String pigLatin = pig("What!?");
      // System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      // pigLatin = pig("{(Hello!)}");
      // System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      // pigLatin = pig("\"McDonald???\"");
      // System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      String originalString = s;
      if(s.length() == 0)
         return "";
   
      //remove and store the beginning punctuation 
      String beginningPunctuation = "";       
      int firstLetter = 0;
      for (int i = 0; i < s.length(); i++) {
         if (punct.indexOf(s.charAt(i)) != -1) {
            beginningPunctuation +=""+ s.charAt(i);
         } else {
            firstLetter = i;
            break;
         }
      }
      //remove and store the ending punctuation 
      String endPunctuation = "";      

      for (int i = s.length() - 1; i >= 0; i--) {
         if (punct.indexOf(s.charAt(i)) != -1) {
            endPunctuation = s.charAt(i) + endPunctuation;
         } else {
            break;
         } 
      }

      s = s.replace(beginningPunctuation, "");
      s = s.replace(endPunctuation, "");
      // s = s.replace(s.charAt(0), Character.toLowerCase(s.charAt(0))); 

      
      //START HERE with the basic case:
      String pigLatinized = "";
      String qutrue = "";
      for (int i = 0; i < s.length(); i++) {
         if (vowels.indexOf(s.charAt(i)) != -1 && i == 0) {
            pigLatinized = beginningPunctuation + s + "way" + endPunctuation;
            break;
         } else if (vowels.indexOf(s.charAt(i)) != -1) {
            if (qutrue != "") {
               pigLatinized = beginningPunctuation + s.substring(i + 1) + s.substring(0, 1).toLowerCase() + s.substring(1, i + 1) + "ay" + endPunctuation;
               System.out.println(beginningPunctuation);
               System.out.println(s.substring(i + 1));
               System.out.println(s.substring(0, 1).toLowerCase());
               System.out.println(s.substring(1, i));
               System.out.println(endPunctuation);
            } else { 
               pigLatinized = beginningPunctuation + s.substring(i) + s.substring(0, 1).toLowerCase() + s.substring(1, i) + "ay" + endPunctuation; 
            }
            break;
         } else if ((s.charAt(i) == 'q' || s.charAt(i) == 'Q') && (s.charAt(i + 1) == 'u' || s.charAt(i + 1) == 'U')) {
            qutrue += s.charAt(i + 1);
         } else if ((s.charAt(i) == 'y' || s.charAt(i) == 'Y') && i != 0) {
            pigLatinized = beginningPunctuation + s.substring(i) + s.substring(0, 1).toLowerCase() + s.substring(1, i) + "ay" + endPunctuation;
            break;
         } else {

         }
      } 
      
      //     y is a vowel if it is not the first letter
      //     qu
      
      //if no vowel has been found 
      int noVowel = 0;
      for (int i = 0; i < s.length() + 1; i++) {
         if (noVowel == s.length()) {
            pigLatinized = "**** NO VOWEL ****";
         }  else if (vowels.indexOf(s.charAt(i)) == -1) {
            noVowel += 1;
         } else if (vowels.indexOf(s.charAt(i)) != -1) {
            break;
         }
      } 

      // int count = 0;
      // for (int i = 0; i < s.length(); i++) {
      //    if (punct.indexOf(s.charAt(count)) != -1) {
      //       count += 1;
      //       System.out.print(count);
      //    } else if (Character.isUpperCase(originalString.charAt(0)) == true) {
      //       System.out.print(count);
      //       pigLatinized = pigLatinized.replace(pigLatinized.charAt(0), Character.toUpperCase(pigLatinized.charAt(0)));
      //       break;
      //    }
      // }
      if (Character.isUpperCase(originalString.charAt(firstLetter)) == true) {
         pigLatinized = pigLatinized.substring(0, 1).toUpperCase() + pigLatinized.substring(1);
      }
      
      //is the first letter capitalized?
      
      
      //return the piglatinized word 
      return pigLatinized;
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      String s = "";
      while( infile.hasNext() )
       	{
            s = infile.nextLine();
            String[] x = s.split(" ");
            for (int i = 0; i < x.length; i++) {
               outfile.print(pig(s.split(" ")[i]));
               if (i < x.length - 1){
                  outfile.print(" ");
               }
            }
            outfile.println("");
       	}
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
      return s;
   }
}
