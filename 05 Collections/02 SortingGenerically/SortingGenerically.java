// Name: Zachary Baker
// Date: 12/05/21
// Period: 2 

import java.io.*;      //the File class
import java.util.*;    //ArrayList & the Scanner class in Java 1.5
 
public class SortingGenerically
{
   public static void main(String[] args) throws Exception
   {
      //Widgets
      List<Comparable> apple = inputWidgets("widgets.txt");
      sort(apple);
      output(apple);
      System.out.println("There are " + apple.size() +" widgets, sorted.");
      
      //Strings
      List<Comparable> strList = inputStrings("strings.txt");
      sort(strList);
      output(strList);
      System.out.println("There are " + strList.size() +" strings, alphabetized.");
   }
   
   public static List<Comparable> inputStrings(String filename) throws Exception
   {
      Scanner sc;
      try {
         sc = new Scanner(new File(filename));
      } catch (FileNotFoundException e) {
          return null;
      }
       
      List<Comparable> list = new ArrayList<>();
      while (sc.hasNextLine()) {
         String[] strings = sc.nextLine().split(" ");
         for (String s : strings) {
            list.add(s);
        }
      }
      return list;
   }
   
   public static List<Comparable> inputWidgets(String filename) throws Exception
   {
      Scanner sc;
      try {
        sc = new Scanner(new File(filename));
      } catch (FileNotFoundException e) {
          return null;
      }
      
      List<Comparable> list = new ArrayList<>();
      while (sc.hasNextLine()) {
        list.add(new Widget(Integer.parseInt(sc.nextLine()), Integer.parseInt(sc.nextLine())));
      }
      return list;
   }
    
   /*  these methods are all GENERIC   */
   public static <T extends Comparable<T>> void sort(List<T> array)
   {
      for (int upper = array.size() - 1; 0 <= upper; upper--) {
         swap(array, findMax(array, upper), upper);
      }
   } 
   public static <T extends Comparable<T>> int findMax(List<T> array, int upper)
   {   
      T maxValue = array.get(0);
      int i = 1;
      for (int k = 0; k <= upper; k++) {
         if (array.get(k).compareTo(maxValue) > 0) {
            i = k;
            maxValue = array.get(k);
         }
      }
      return i;
   }
   
   public static <T> void swap(List<T> array, int a, int b)
   {
      T swapped = array.get(a);
      array.set(a, array.get(b));
      array.set(b, swapped);
   }  
   
   public static <T> void output(Collection<T> array)
   {
      Iterator iterator = array.iterator();
      
      while (iterator.hasNext() == true) {
         System.out.println(iterator.next());
      }
   }
}

// /*************************************
//  0 cubits 14 hands
//  1 cubits 3 hands
//  2 cubits 14 hands
//  5 cubits 14 hands
//  10 cubits 14 hands
//  11 cubits 11 hands
//  12 cubits 0 hands
//  12 cubits 7 hands
//  13 cubits 9 hands
//  15 cubits 12 hands
//  17 cubits 5 hands
//  18 cubits 13 hands
//  19 cubits 13 hands
//  19 cubits 13 hands
//  22 cubits 6 hands
//  23 cubits 7 hands
//  24 cubits 15 hands
//  24 cubits 15 hands
//  26 cubits 2 hands
//  28 cubits 5 hands
//  28 cubits 12 hands
//  29 cubits 15 hands
//  31 cubits 0 hands
//  32 cubits 1 hands
//  32 cubits 11 hands
//  32 cubits 11 hands
//  32 cubits 12 hands
//  35 cubits 3 hands
//  39 cubits 2 hands
//  39 cubits 5 hands
//  41 cubits 10 hands
//  43 cubits 2 hands
//  43 cubits 5 hands
//  43 cubits 6 hands
//  51 cubits 2 hands
//  54 cubits 14 hands
//  55 cubits 8 hands
//  56 cubits 3 hands
//  57 cubits 12 hands
//  62 cubits 15 hands
//  63 cubits 0 hands
//  64 cubits 13 hands
//  67 cubits 3 hands
//  70 cubits 0 hands
//  73 cubits 5 hands
//  74 cubits 7 hands
//  75 cubits 9 hands
//  81 cubits 5 hands
//  85 cubits 14 hands
//  86 cubits 3 hands
//  90 cubits 13 hands
//  91 cubits 3 hands
//  92 cubits 1 hands
//  92 cubits 8 hands
//  96 cubits 1 hands
//  98 cubits 8 hands
//  99 cubits 5 hands
//  There are 57 widgets, sorted.
//  APCS
//  Encapsulation
//  Exam
//  Generics
//  Inheritance
//  Java
//  Method
//  OOP
//  Object
//  Oriented
//  Polymorphism
//  Programming
//  There are 12 strings, alphabetized.   
//  ****************************************/