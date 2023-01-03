// Name:              Date:
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode("");
      TreeNode temp = root;
      
      while (huffLines.hasNext()) {
         temp = root;
         String line = huffLines.nextLine();
         String value = line.substring(0, 1);
         
         for (int i = 1; i < line.length(); i++) {
            String curr = line.substring(i, i + 1);
            if (curr.equals("0")) {
               if (temp.getLeft() == null) {
                  temp.setLeft(new TreeNode(""));
                  temp = temp.getLeft();
               } else {
                  temp = temp.getLeft();
               }
            } else {
               if (temp.getRight() == null) {
                  temp.setRight(new TreeNode(""));
                  temp = temp.getRight();
               } else {
                  temp = temp.getRight();
               }
            }
         }
         temp.setValue(value);
      }
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode temp = root;
      String word = "";
      int counter = 0;
      
      while (text.length() != 0) {
         if (temp.getValue() != "") {
            word += temp.getValue();
            text = text.substring(counter);
            temp = root;
            counter = 0;
         }
         else if (text.substring(counter, counter + 1).equals("0")) {
            temp = temp.getLeft();
            counter++;
         } else {
            temp = temp.getRight();
            counter++;
         }
      }
      return word;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
