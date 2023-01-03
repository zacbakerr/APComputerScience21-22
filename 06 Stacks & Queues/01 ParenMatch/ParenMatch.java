// Name:
// Date:

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5 + 7");
      parenExp.add("15 + -7");
      parenExp.add(") 5 + 7 (");
      parenExp.add("( ( 5.0 - 7.3 ) * 3.5 )");
      parenExp.add("< { 5 + 7 } * 3 >");
      parenExp.add("[ ( 5 + 7 ) * ] 3");
      parenExp.add("( 5 + 7 ) * 3");
      parenExp.add("5 + ( 7 * 3 )");
      parenExp.add("( ( 5 + 7 ) * 3");
      parenExp.add("[ ( 5 + 7 ] * 3 )");
      parenExp.add("[ ( 5 + 7 ) * 3 ] )");
      parenExp.add("( [ ( 5 + 7 ) * 3 ]");
      parenExp.add("( ( ( ) $ ) )");
      parenExp.add("( ) [ ]");
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
     
   //returns the index of the left parentheses or -1 if it is not there
   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   
   public static boolean checkParen(String exp)
   {
     /* enter your code here */
     Stack<Character> stack = new Stack<Character>();
     
     for (int i = 0; i < exp.length(); i++) {
     
      // for debugging
      char currChar = exp.charAt(i);
      
      if (LEFT.indexOf(exp.charAt(i)) != -1) {
         stack.push(exp.charAt(i));
      } else if (RIGHT.indexOf(exp.charAt(i)) != -1) {
         if (stack.empty()) {
            return false;
         } else {
            int indexOf;
            char curr = stack.peek();
            indexOf = LEFT.indexOf(stack.pop());
            if ((RIGHT.charAt(indexOf) == exp.charAt(i))) {
               // search rest of exp and search for remaining right
               //return true;
            } else if ((RIGHT.charAt(indexOf) != exp.charAt(i))) {
               return false;
            }
         }
      }
     }
     if (stack.empty()) {
      return true;
     } else {
      return false;
     }       
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
