// Name: Zachary Baker
// Date: 11/19/2

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   //no constructor needed
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head;
      for (int i = 0; i < index; i++) {
         temp = temp.getNext();
      }              
      DLNode tempNext = temp.getNext(); 
      if (index == 0) {
         DLNode temp2 = new DLNode(obj, temp, temp.getNext()); 
         temp.getNext().setPrev(temp2);
         temp.setNext(temp2);  
         size++;
      } else {
         DLNode temp2 = new DLNode(obj, temp.getPrev(), temp); 
      
         temp.getPrev().setNext(temp2);
         temp.setPrev(temp2);
         size++;
      }
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head;
      for (int i = 0; i < index + 1; i++) {
         temp = temp.getNext();
      }
      return temp.getValue();
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head;
      for (int i = 0; i < index + 1; i++) {
         temp = temp.getNext();
      }
      Object tempVal = temp.getValue();
      temp.setValue(obj);
      return tempVal;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head.getNext();
      for(int i = 0; i < index; i++){
         temp = temp.getNext();
      }
      Object tempVal = temp.getValue();
      temp.getNext().setPrev(temp.getPrev());
      temp.getPrev().setNext(temp.getNext());
      size--;
      return tempVal;
   }
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      DLNode last = new DLNode(obj, head, head.getNext());
      head.setNext(last);
      head.getNext().setPrev(last);
      size++;
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      DLNode temp = new DLNode(obj, head.getPrev(), head);
      head.setPrev(temp);
      temp.getPrev().setNext(temp);
      size++;
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   {
      if (head == null) {
         return null;
      }
      return remove(0);
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
      if (head == null) {
         return null;
      }
      return remove(size-1);
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String print = "[";
      DLNode temp = head.getNext();
      
      for (int i = 0; i < size -1; i++) {
         print = print + temp.getValue() + ", ";
         temp = temp.getNext();
      }
      print = print + temp.getValue() + "]";
      return print;
   }
}