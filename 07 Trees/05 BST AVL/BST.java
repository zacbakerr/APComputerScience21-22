// Name: 
// Date: 

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
   /*  copy your BST code  here  */
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


   /*  start the addBalanced methods */
   private int calcBalance(TreeNode t) //height to right minus 
   {                                    //height to left
      return (height(t.getRight()) - height(t.getLeft()));
   }

   private int height(TreeNode t)   //from TreeLab
   {
      int heightLeft = 0;
      int heightRight = 0;
      if (t == null) {
         return -1;
      }
      heightLeft = 1 + height(t.getLeft());
      heightRight = 1 + height(t.getRight());
      if (heightLeft >= heightRight) {
         return heightLeft;
      } else {
         return heightRight;
      }
   }

   public void addBalanced(String value)  
   {
      add(value);
      root = balanceTree(root);   // for an AVL tree. Put in the arguments you want.
   }
   private TreeNode balanceTree(TreeNode t)  //recursive.  Whatever makes sense.
   {  
      if (t == null) {
         return t;
      }
      
      t.setLeft(balanceTree(t.getLeft()));
      t.setRight(balanceTree(t.getRight()));
   
      if (calcBalance(t) >= -1 && calcBalance(t) <= 1) { // already balanced
         return t;
      } else if (calcBalance(t) > 1) { // right heavy
         if (calcBalance(t.getRight()) == 1) {
            if (height(t.getRight().getRight()) > height(t.getRight().getLeft())) {
               t = singleLeft(t);
            } else {
               t = doubleRight(t);
            }
         } else if (calcBalance(t.getRight()) < 1) { // if the right subtree is left heavy
            // double left rotation
            t = doubleRight(t);
         } else {
            // single left rotation
            t = singleLeft(t);
         }
      } else { // left heavy
         if (calcBalance(t.getLeft()) == 1) {
            if (height(t.getLeft().getLeft()) > height(t.getLeft().getRight())) {
               t = singleRight(t);
            } else {
               t = doubleLeft(t);
            }
         } else if (calcBalance(t.getLeft()) > 1) { // if the left subtree is right heavy
            // double right rotation
            t = doubleLeft(t);
         } else {
            // single right rotation
            t = singleRight(t);
         }
      }
      return t;
   }
   // 4 rotation methods
   private TreeNode singleRight(TreeNode t) {
      TreeNode newMiddle = t.getLeft();
      if (newMiddle.getRight() != null) {
         t.setLeft(newMiddle.getRight());
      } else {
         t.setLeft(null);
      }
      newMiddle.setRight(t);
      return newMiddle;
   }
   
   private TreeNode singleLeft(TreeNode t) {
      TreeNode newMiddle = t.getRight();
      if (newMiddle.getLeft() != null) {
         t.setRight(newMiddle.getLeft());
      } else {
         t.setRight(null);
      }
      newMiddle.setLeft(t);
      return newMiddle;
   }
   
   private TreeNode doubleRight(TreeNode t) {
      TreeNode newRight = t.getRight();
      TreeNode newMiddle = newRight.getLeft();   
      if (newMiddle.getLeft() != null) {
         t.setRight(newMiddle.getLeft());
      } else {
         t.setRight(null);
      }
      if (newMiddle.getRight() != null) {
         newRight.setLeft(newMiddle.getRight());
      } else {
         newRight.setLeft(null);
      }
      newMiddle.setRight(newRight);
      newMiddle.setLeft(t);
      return newMiddle;
   }
   
   private TreeNode doubleLeft(TreeNode t) {
      TreeNode newLeft = t.getLeft();
      TreeNode newMiddle = newLeft.getRight();   
      if (newMiddle.getRight() != null) {
         t.setLeft(newMiddle.getRight());
      } else {
         t.setLeft(null);
      }
      if (newMiddle.getLeft() != null) {
         newLeft.setRight(newMiddle.getLeft());
      } else {
         newLeft.setRight(null);
      }
      newMiddle.setLeft(newLeft);
      newMiddle.setRight(t);
      return newMiddle;
   }
}