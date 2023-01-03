 //Name:     
 //Date:

import java.util.*;
import java.io.*;

public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);  //students write
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);   //students write
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   
   public static void print(double[] a)
   {
      // for(int k = 0; k < a.length; k++)
         // System.out.println(a[k]);
      for(double temp: a)         //for-each
         System.out.print(temp+" ");
      System.out.println();
   }
   
   public static void print(Object[] papaya)
   {
      for(Object temp : papaya)    
         System.out.print(temp+" ");
   }
   
   public static boolean isAscending(double[] a)
   {
      for (int i = 0; i < a.length; i++) {
         if (i > 0) {
            if (a[i] < a[i-1])
               return false;
         }
      }
      return true;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      Comparable first = a[0];
      for (int i = 0; i < a.length; i++) {
            if (!(a[i].compareTo(first) >= 0))
               return false;
         }
      return true;
   }
}

//**********************************************************

class Insertion
{
   public static void sort(double[] array)
   { 
      for (int i = 0; i < array.length; i++) {
         shift(array, i, array[i]);
      }
   }
 
   private static int shift(double[] array, int index, double value)
   {
      while (index > 0 && array[index - 1] > value) {
         array[index] = array[index - 1];
         index--; 
      }
      array[index] = value;
      return index;
   }
 
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   { 
      for (int i = 0; i < array.length; i++) {
         shift(array, i, array[i]);
      }
   }
   
   @SuppressWarnings("unchecked")
   private static int shift(Comparable[] array, int index, Comparable value)
   {
      while (index > 0 && array[index - 1].compareTo(value) > 0) {
         array[index] = array[index - 1];
         index--;
      }
      array[index] = value;
      return index;
   }
}
