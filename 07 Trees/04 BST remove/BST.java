// Name: 
// Date: 

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);  //BST_AVL
   public void remove(String obj);    
   //public void removeBalanced(String obj); //extra lab Red_Black
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
BST. Implement the remove() method.
Test it with BST_Remove_Driver.java
**********************/
public class BST implements BSTinterface
{
   /*  copy your BST code here  */
   private TreeNode root;
   private int size;

   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s); 
      size++;
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if (t == null) {
         return new TreeNode(s, null, null);
      }
      if (s.compareTo(t.getValue().toString()) <= 0 ) {
         t.setLeft(add(t.getLeft(), s));
      } else {
         t.setRight(add(t.getRight(), s));
      }  
      return t;     
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if (t == null) {
         return false;
      }
      if (x.compareTo(t.getValue().toString()) < 0) {
         return contains(t.getLeft(), x);
      } else if (x.compareTo(t.getValue().toString()) > 0) {
         return contains(t.getRight(), x);
      }
      return true;
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if (t == null) {
         return null;
      }
      while (t.getLeft() != null) {
         t = t.getLeft();
      }
      return t.getValue().toString();
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if (t == null) {
		   return null;
	   }
	   while (t.getRight() != null) {
		   t = t.getRight();
	   }
	   return t.getValue().toString();
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String stringThing = "";
      if (t == null) {
         return "";
      }
      stringThing += toString(t.getLeft());    
      stringThing += t.getValue() + " ";
      stringThing += toString(t.getRight());

      return stringThing;
   }


   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      TreeNode parent = current;
      TreeNode curr = current; 
      // To check if the node is a left or right child (left = true and right = false)
      boolean leftOrRightChild = false;
      boolean TRUE = true;
      
      if (size() == 1) {
         current = null;
         return current;
      }
      
      while (TRUE = true) {
         if (target.compareTo(curr.getValue().toString()) > 0) {
            parent = curr;
            leftOrRightChild = false;
            curr = curr.getRight();
         } else if (target.compareTo(curr.getValue().toString()) < 0) {
            parent = curr;
            leftOrRightChild = true;
            curr = curr.getLeft();
         } else {
            break;
         }
      }
      // case 1a and 1b
      if (curr.getRight() == null && curr.getLeft() == null) {
         if (parent == curr) {
            curr = null;
            parent = null;
         }
         if (leftOrRightChild == true) {
            parent.setLeft(null);
         } else {
            parent.setRight(null);
         }
      }   
      // case 2c and 2d
      else if ((curr.getRight() == null && curr.getLeft() != null) || (curr.getRight() != null && curr.getLeft() == null)) {
         if (curr == current) {
            if (current.getRight() != null) {
               if (parent == current) {
                  current = curr.getRight();
               }
               if (leftOrRightChild == true) {
                  parent.setLeft(current.getRight());
               } else {
                  parent.setRight(current.getRight());
               }
            } else {
               if (parent == current) {
                  parent = null;
                  current = current.getLeft();
                  return current;
               }
               if (leftOrRightChild == true) {
                  parent.setLeft(curr.getLeft());
               } else {
                  parent.setRight(curr.getLeft());
               }
            }
         }
         else {
            if (curr.getRight() != null) {
               if (parent == curr) {
                  curr = curr.getRight();
               }
               if (leftOrRightChild == true) {
                  parent.setLeft(curr.getRight());
               } else {
                  parent.setRight(curr.getRight());
               }
            } else {
               if (parent == curr) {
                  parent = null;
                  curr = curr.getRight();
               }
               if (leftOrRightChild == true) {
                  parent.setLeft(curr.getLeft());
               } else {
                  parent.setRight(curr.getLeft());
               }
            }
         }
      }
      
      // 3a, 3b
      else if (curr.getRight() != null && curr.getLeft() != null) {
         TreeNode maxLeftSubTree = curr.getLeft();
         TreeNode maxLeftSubTreeParent = curr;

   	   while (maxLeftSubTree.getRight() != null) {
            maxLeftSubTreeParent = maxLeftSubTree;
   		   maxLeftSubTree = maxLeftSubTree.getRight();
   	   }
         
         curr.setValue(maxLeftSubTree.getValue());
         
         if (maxLeftSubTreeParent == curr) {
            if (maxLeftSubTree.getLeft() != null) {
               maxLeftSubTreeParent.setLeft(maxLeftSubTree.getLeft());
               maxLeftSubTree.setValue(null);
            } else {
               maxLeftSubTreeParent.setLeft(null);
               maxLeftSubTree.setValue(null);
            }

         } else if (maxLeftSubTree.getLeft() != null) {
            maxLeftSubTreeParent.setRight(maxLeftSubTree.getLeft());
            maxLeftSubTree.setValue(null);
         } else {
            maxLeftSubTreeParent.setRight(null);
            maxLeftSubTree.setValue(null);
         }

      //etc.
      
     }
     return current; 

   }
}