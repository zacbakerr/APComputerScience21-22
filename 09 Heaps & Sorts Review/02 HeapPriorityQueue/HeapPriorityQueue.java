 //Name:   
 //Date:
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size() - 1;
   }
   
   public boolean isEmpty()
   {
      if (myHeap.size() == 1) {
         return true;
      } else {
         return false;
      }
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
      E temp = myHeap.get(1);
      swap(1, lastIndex());
      myHeap.remove(lastIndex());
      heapDown(1, lastIndex());
      return temp;
   }
   
   public E peek()
   {
      if (myHeap.size() > 1) {
         return myHeap.get(1);
      } else {
         return null;
      }
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
      while (k > 1) {
         if (myHeap.size() <= 2) {
            return;
         }
         if (myHeap.get(k).compareTo(myHeap.get(k/2)) >= 0) {
            return;
         } else if (myHeap.get(k).compareTo(myHeap.get(k/2)) < 0) {
            swap(k, k/2); 
            k = k/2;        
         }
      }      
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      while (2 * k < lastIndex) {
         if (myHeap.get(k).compareTo(myHeap.get(2 * k)) < 0 && myHeap.get(k).compareTo(myHeap.get(2 * k + 1)) < 0) {
            return;
         }
         else if (myHeap.get(2 * k).compareTo(myHeap.get(2 * k + 1)) < 0) {
            swap(2 * k, k);
            k = 2 * k;
         } else {
            swap(2 * k + 1, k);
            k = 2 * k + 1;
         }
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
