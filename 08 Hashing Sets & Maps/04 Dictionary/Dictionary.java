// Name: 
// Date: 

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      System.out.println(e);
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> dictionary = new TreeMap<String, Set<String>>();
      while (infile.hasNextLine()) {
         add(dictionary, infile.nextLine(), infile.nextLine());
      }
      return dictionary;
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      if (!dictionary.containsKey(word)) {
         TreeSet<String> tempSet = new TreeSet<>();
         tempSet.add(translation);
         dictionary.put(word, tempSet);
      } else {
         dictionary.get(word).add(translation);
      }
   }
   
   public static void display(Map<String, Set<String>> m)
   {
      String s = "";
      Iterator<String> it = m.keySet().iterator(); 
      while (it.hasNext()) {
         String temp = it.next();
         s += temp;
         s += " ";
         s += m.get(temp);
         s += "\n";
      }
      System.out.print(s);
      System.out.print("\n");
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
//       Queue<String> english = new LinkedList<>();
//       Queue<String> spanish = new LinkedList<>();
//       Iterator<String> it = dictionary.keySet().iterator();
//       
//       while (it.hasNext()) {
//          String temp = it.next();
//          english.add(temp);
//          Iterator<String> spans = dictionary.get(temp).iterator();
//          while (spans.hasNext()) {
//             spanish.add(spans.next());
//          }
//       }
//       
//       Map<String, Set<String>> spanToEng = new TreeMap<String, Set<String>>();
//       while (!english.isEmpty() && !spanish.isEmpty()) {
//          String currEng = english.remove();
//          String currSpan = spanish.remove();
//          
//          if (!spanToEng.containsKey(currSpan)) {
//             TreeSet<String> tempSet = new TreeSet<>();
//             tempSet.add(currEng);
//             spanToEng.put(currSpan, tempSet);
//          } else {
//             spanToEng.get(currSpan).add(currEng);
//          }
//       }
//       return spanToEng;
      
      Map<String, Set<String>> spanToEng = new TreeMap<String, Set<String>>();
      
      Iterator<String> it = dictionary.keySet().iterator();
   
      while (it.hasNext()) {
         String temp = it.next();
         Iterator<String> spans = dictionary.get(temp).iterator();
         while (spans.hasNext()) {
            String temp2 = spans.next();
            if (!spanToEng.containsKey(temp2)) {
               TreeSet<String> tempSet = new TreeSet<>();
               tempSet.add(temp);
               spanToEng.put(temp2, tempSet);
            } else {
               spanToEng.get(temp2).add(temp);
            }
         }
      }
      return spanToEng;
   }
}


   /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/