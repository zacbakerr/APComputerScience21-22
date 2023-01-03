// Name:
// Date:

public class HeapSort
{
   public static int N;  //9 or 100
	
   public static void main(String[] args)
   {
      /* Part 1: Given a heap, sort it. Do this part first. */
      N = 9;  
      //double heap[] = {-1,99,80,85,17,30,84,2,16,1};  // size of array = N+1
      double heap[] = {-1, 7.2, 3.4, 6.4, 9.9};
      
      display(heap);
      sort(heap);
      display(heap);      System.out.println(isSorted(heap));
      
      /* Part 2:  Generate 100 random numbers, make a heap, sort it.  */
      /*
      N = 100;
      double[] heap = new double[N + 1];  // size of array = N+1
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, N);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      */
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      int lastIndex = array.length - 1;
      while (lastIndex != 1) {
         if (array[1] > array[lastIndex]) {
            swap(array, 1, lastIndex);
         }
         lastIndex = lastIndex - 1;
         heapDown(array, 1, lastIndex);
      }
   
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int lastIndex)
   {  
      while (2 * k < lastIndex) {
         if (array[k] > array[2 * k] && array[k] > array[2 * k + 1]) {
            return;
         }
         else if (array[2 * k] > array[2 * k + 1]) {
            swap(array, 2 * k, k);
            k = 2 * k;
         } else {
            swap(array, 2 * k + 1, k);
            k = 2 * k + 1;
         }
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      for (int i = 2; i < arr.length; i++) {
         if (arr[i - 1] > arr[i]) {
            return false;
         }
      }
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      for (int i = 1; i <= 100; i++) {
         array[i] = Math.random();
      }
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int lastIndex)
   {
      int middle = lastIndex/2;
      for (int i = middle; i > 0; i--) {
         heapDown(array, i, lastIndex);
      }
   }
}

