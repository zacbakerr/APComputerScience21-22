// Name:
// Date:
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
      infixExp.add("5 - 1 - 1");
      infixExp.add("5 - 1 + 1");
      infixExp.add("12 / 6 / 2");
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("1.3 + 2.7 + -6 * 6");
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("5 + [ 2 - ( 1 + 3 ) + 4 % 3 ]");
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
         //System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      Stack<String> ops = new Stack<String>();
      String postFix = "";
      
      for (int i = 0; i < nums.size(); i++) {
         if (operators.indexOf(nums.get(i)) != -1) {
            if (ops.empty()) {
               ops.push(nums.get(i));
            }
            else if (isHigherOrEqual(ops.peek(), nums.get(i))) {
               postFix += ops.pop() + " ";
               ops.push(nums.get(i));
            } else {
               ops.push(nums.get(i));
            }
         } else if (nums.get(i).equals("(")) {
            ops.push(nums.get(i));
         } else if (nums.get(i).equals(")")) {
            String temp2 = "";
            String temp3 = ops.peek();
            while (!temp3.equals("(")) {
               temp2 = ops.pop();
               postFix += temp2 + " ";
               temp3 = ops.peek();
            }
            ops.pop();
         } else {
            postFix += nums.get(i) + " ";
         }
      }
      while (!ops.empty()) {
         postFix += ops.pop() + " ";
      }
      return postFix;
   }
   
   //enter your precedence method below
   public static boolean isHigherOrEqual(String a, String b) {
      int avalue = 0;
      int bvalue = 0;
      if (a.equals("^")) {
         avalue = 3;
      } else if (a.equals("*") || a.equals("/") || a.equals("%")) {
         avalue = 2;
      } else if  (a.equals("+") || a.equals("-")) {
         avalue = 1;
      }
      if (b.equals("^")) {
         bvalue = 3;
      } else if (b.equals("*") || b.equals("/") || b.equals("%")) {
         bvalue = 2;
      } else if  (b.equals("+") || b.equals("-")) {
         bvalue = 1;
      }
      if (avalue >= bvalue) {
         return true;
      } else {
         return false;
      }
   }
   // if not higher add to stack, if higher pop stack then add operator to atack
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/
