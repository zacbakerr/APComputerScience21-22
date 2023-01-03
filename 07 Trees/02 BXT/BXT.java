// Name: 
// Date:  
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }
    
   public void buildTree(String str)
   {  
      Stack<TreeNode> nodes = new Stack<>();
      ArrayList<String> nums = new ArrayList<String>(Arrays.asList(str.split(" ")));
      for (int i = 0; i < nums.size(); i++) {
         if (operators.indexOf(nums.get(i)) != -1) {
            TreeNode operand1 = nodes.pop();
            TreeNode operand2 = nodes.pop();
            TreeNode temp = new TreeNode("" + nums.get(i), operand1, operand2);
            nodes.push(temp);
         }
         else {
            nodes.push(new TreeNode("" + nums.get(i), null, null));
         }
      }
      root = nodes.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if (t == null) {
         return 0.0;
      } else if (isOperator(t.getValue().toString())) {
         return computeTerm(t.getValue().toString(), evaluateNode(t.getRight()), evaluateNode(t.getLeft()));
      } else {
         return Double.parseDouble(t.getValue().toString());
      }
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if (s.equals("+")) {
         return a + b;   
      } else if (s.equals("-")) {
         return a - b;
      } else if (s.equals("*")) {
         return a * b;
      } else if (s.equals("/")) {
         return a / b;
      } else if (s.equals("%")) {
         return a % b;
      } else {
         return 0;
      }
   }
   
   private boolean isOperator(String s)
   {
      if (operators.indexOf(s) != -1) {
         return true;
      } else {
         return false;
      }
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String stringThing = "";
      if (t == null) {
         return "";
      }
      stringThing += inorderTraverse(t.getLeft());    
      stringThing += t.getValue() + " ";
      stringThing += inorderTraverse(t.getRight());
      return stringThing;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";
      toReturn += preorderTraverse(root.getLeft());
      toReturn += preorderTraverse(root.getRight());
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}