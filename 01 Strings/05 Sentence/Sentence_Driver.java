// Name: Zac Baker
// Date: 09/15.21
//Period 5

import java.util.StringTokenizer;

public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
        
      
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
         
      
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
         
      // Lots more test cases.  Test every line of code.  Test
      // the extremes, test the boundaries.  How many test cases do you need?
      
   }
}
class Sentence
{
   private String mySentence;
   private int myNumWords;
      
      //Constructor.  Creates sentence from String str.
      //						Finds the number of words in sentence.
      //Precondition:  Words in str separated by exactly one blank.
   public Sentence(String str)
   { 
      mySentence = str;
      StringTokenizer counter = new StringTokenizer(str);
      myNumWords = counter.countTokens();
   }
      
   public int getNumWords()
   {  
      return myNumWords;  
   }
      
   public String getSentence()
   {
      return mySentence; 
   }
      
      //Returns true if mySentence is a palindrome, false otherwise.
   public boolean isPalindrome()
   {
      String s = getSentence();

      s = lowerCase(s);
      s = removeBlanks(s);
      s = removePunctuation(s);

      if(s.length() == 1 || s.length() == 0)
         return true;
      else
         return isPalindrome(s, 0, s.length()-1);             
   }
      //Precondition: s has no blanks, no punctuation, and is in lower case.
      //Returns true if s is a palindrome, false otherwise.
   public static boolean isPalindrome( String s, int start, int end )
   {
      if ((end-start) < 2) {
         return true;
      } else if ((s.charAt(start)) != (s.charAt(end))) {
         return false;
      } else {
         return isPalindrome(s, start+1, end-1);
      }
   }
      //Returns copy of String s with all blanks removed.
      //Postcondition:  Returned string contains just one word.
   public static String removeBlanks( String s )
   {    
      return s.replace(" ", "");
   }
      
      //Returns copy of String s with all letters in lowercase.
      //Postcondition:  Number of words in returned string equals
      //						number of words in s.
   public static String lowerCase( String s )
   {  
      return s.toLowerCase();
   }
      
      //Returns copy of String s with all punctuation removed.
      //Postcondition:  Number of words in returned string equals
      //						number of words in s.
   public static String removePunctuation( String s )
   { 
      s = s.toLowerCase();
      s = s.replace(",", "");
      s = s.replace(".", "");
      s = s.replace(";", "");
      s = s.replace("!", "");
      s = s.replace("?", "");
      s = s.replace("\'", "");
      s = s.replace("\"", "");
   
      return s;
   }
}

//  /*****************************************
   
//  PALINDROME TESTER
//  "Hello there!" she said.
//  4
//  false
 
//  A Santa lived as a devil at NASA.
//  8
//  true
 
//  Flo, gin is a sin! I golf.
//  7
//  true
 
//  Eva, can I stab bats in a cave?
//  8
//  true
 
//  Madam, I'm Adam.
//  3
//  true

//  **********************************************/

