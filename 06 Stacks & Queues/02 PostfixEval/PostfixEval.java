// Name: Zac Baker
// Date: 1/12/22

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      postfixExp.add("4 5 * 3 +");
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Double> numbers = new Stack<Double>();
      Double doubleFinal = 0.0;
            
      for (int i = 0; i < postfixParts.size(); i++) {
         if (isOperator(postfixParts.get(i))) {
            double b = numbers.pop();
            double a = numbers.pop();
            numbers.push(eval(a, b, postfixParts.get(i)));
         } else if (postfixParts.get(i).equals("!")) {
            double a = numbers.pop();
            double b = 0.0;
            numbers.push(eval(a, b, postfixParts.get(i)));
         } else {
            numbers.push(Double.parseDouble(postfixParts.get(i)));
         }
      }
      
      return numbers.get(0);
   }
   
   public static double eval(double a, double b, String ch)
   {
      if (ch.equals("+")) {
         return a + b;
      } else if (ch.equals("-")) {
         return a - b;
      } else if (ch.equals("*")) {
         return a * b;
      } else if (ch.equals("/")) {
         return a / b;
      } else if (ch.equals("%")) {
         return a % b;
      } else if (ch.equals("^")) {
         double power = a;
         for (int i = 1; i < b; i++) {
            power = power * a;
         }
         return power;
      } else if (ch.equals("!")) {
         double factorial = a;
         for (double i = a-1; i > 0; i--) {
            factorial = factorial * i;
         }
         return factorial;
      } else {
         return 420.0;
      }
   }
   
   public static boolean isOperator(String op)
   {
      if (operators.indexOf(op) != -1 && !op.equals("!")) {
         return true;
      } else {
         return false;
      }
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/