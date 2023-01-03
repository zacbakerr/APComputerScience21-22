// Name: 
// Date: 

import java.io.*;
import java.util.*;  

/* This program takes a text file, creates an index (by line numbers)
 * for all the words in the file and writes the index
 * into the output file.  The program prompts the user for the file names.
 */
public class IndexMakerMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String infileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(infileName));
      
      DocumentIndex index = makeIndex(inputFile);
      
      //System.out.println( index.toString() );
      PrintWriter outputFile = new PrintWriter(new FileWriter("fishIndex.txt"));
      outputFile.println(index.toString());
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static DocumentIndex makeIndex(Scanner inputFile)
   {
      DocumentIndex index = new DocumentIndex(); 	
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      return index;  
   }
}

class DocumentIndex extends TreeMap<String, TreeSet<Integer>>
{
  
  /** Extracts all the words from str, skipping punctuation and whitespace
   *  and for each word calls addWord(word, num).  A good way to skip punctuation
   *  and whitespace is to use String's split method, e.g., split("[., \"!?]") 
   */
   public void addAllWords(String str, int lineNum) 
   {
      ArrayList<String> s = new ArrayList<String>(Arrays.asList(str.split("[., \"!?]")));
      for (int i = 0; i < s.size(); i++) {
         if (s.get(i) != "") {
            addWord(s.get(i), lineNum);
         }
      }
   }

  /** Makes the word uppercase.  If the word is already in the map, updates the lineNum.
   *  Otherwise, adds word and TreeSet to the map, and updates the lineNum   
   */
   public void addWord(String word, int lineNum)
   {
      String upper = word.toUpperCase();
      if (!this.containsKey(upper)) {
         TreeSet<Integer> set = new TreeSet<>();
         set.add(lineNum);
         this.put(upper, set);
      } else {
         this.get(upper).add(lineNum);
      }
   }
   
   public String toString()
   {
      String s = "";
      Iterator<String> it = this.keySet().iterator(); 
      while (it.hasNext()) {
         String temp = it.next();
         s += temp;
         s += " ";
         Iterator<Integer> ints = this.get(temp).iterator();
         while (ints.hasNext()) {
            s += ints.next();
            if (ints.hasNext()) {
               s += ", ";
            }
         }
         s += "\n";
      }
      return s;
   }
}

/**********************************************
     ----jGRASP exec: java -ea IndexMakerMap
 
 Enter input file name: fish.txt
 Done.
 
  ----jGRASP: operation complete.
  
************************************************/
/****************** fishIndex.txt  **************
A 12, 14, 15
ARE 16
BLACK 6
BLUE 4, 7
CAR 14
FISH 1, 2, 3, 4, 6, 7, 8, 9, 16
HAS 11, 14
LITTLE 12, 14
LOT 15
NEW 9
OF 16
OLD 8
ONE 1, 11, 14
RED 3
SAY 15
STAR 12
THERE 16
THIS 11, 14
TWO 2
WHAT 15   
   ************************/
