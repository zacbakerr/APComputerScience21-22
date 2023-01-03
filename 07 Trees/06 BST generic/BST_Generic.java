// Name:
// Date: 
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
   private TreeNode<E> root;
   private int size;

   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public E add(E s) 
   {
      root = add(root, s); 
      size++;
      return s;
   }
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {      
      if (t == null) {
         return new TreeNode<E>(s, null, null);
      }
      if (s.compareTo(t.getValue()) <= 0 ) {
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
   private String display(TreeNode<E> t, int level) //recursive helper method
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
   
   public boolean contains(E obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if (t == null) {
         return false;
      }
      if (x.compareTo(t.getValue()) < 0) {
         return contains(t.getLeft(), x);
      } else if (x.compareTo(t.getValue()) > 0) {
         return contains(t.getRight(), x);
      }
      return true;
   }
   
   public E min()
   {
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if (t == null) {
         return null;
      }
      while (t.getLeft() != null) {
         t = t.getLeft();
      }
      return t.getValue();
   }
   
   public E max()
   {
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if (t == null) {
		   return null;
	   }
	   while (t.getRight() != null) {
		   t = t.getRight();
	   }
	   return t.getValue();
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
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
   
   public ArrayList<E> toList() {
      ArrayList<E> tempList = new ArrayList<>();
      return toList(root, tempList);
   }
      
   private ArrayList<E> toList(TreeNode<E> root, ArrayList<E> list) {
        if (root == null) {
            return null;
        }
        toList(root.getLeft(), list);
        list.add(root.getValue());
        toList(root.getRight(), list);
        
        return list;
   }


   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target;
   }
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
      TreeNode<E> parent = current;
      TreeNode<E> curr = current; 
      // To check if the node is a left or right child (left = true and right = false)
      boolean leftOrRightChild = false;
      boolean TRUE = true;
      
      if (size() == 1) {
         current = null;
         return current;
      }
      
      while (TRUE = true) {
         if (target.compareTo(curr.getValue()) > 0) {
            parent = curr;
            leftOrRightChild = false;
            curr = curr.getRight();
         } else if (target.compareTo(curr.getValue()) < 0) {
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
         TreeNode<E> maxLeftSubTree = curr.getLeft();
         TreeNode<E> maxLeftSubTreeParent = curr;

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

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;

    public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }

    public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }

    public E getValue()
   { 
      return value; 
   }

    public TreeNode<E> getLeft() 
   { 
      return left; 
   }

    public TreeNode<E> getRight() 
   { 
      return right; 
   }

    public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }

    public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }

    public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }
}