// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public String toString();  //returns the grid as a String
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
//    public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability();   // Warshall's Algorithm
//    public List<String> getReachables(String from);  //Warshall extension
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  write constructor, accessor method, and instance methods here  */  
   public AdjMat(int size) {
      grid = new int[size][size];
      
      for (int r = 0; r < grid.length; r++) {
         for (int c = 0; c < grid.length; c++) {
            grid[r][c] = 0;
         }
      }
      
      vertices = new TreeMap<String, Integer>();
   }
   
   public int getCost(int from, int to) {
      return grid[from][to];
   }
   
   public int getCost(String from, String to) {
      int cost = getCost(vertices.get(from), vertices.get(to));
      return cost;
   }
   
   public void allPairsWeighted() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid.length; j++) {
            for (int k = 0; k < grid.length; k++) {
               if ((grid[j][i] + grid[i][k]) < grid[j][k]) {
                  grid[j][k] = grid[j][i] + grid[i][k];
               }
            }
         }
      }
   }
   
   public boolean isEdge(String from, String to) {
      boolean edge = isEdge(vertices.get(from), vertices.get(to));
      return edge;
   }
   
   public Map<String, Integer> getVertices() {
      return vertices;
   }
      
   public void readNames(String fileName) throws FileNotFoundException {
      Scanner names = new Scanner(new File(fileName));
      int count = 0;
      names.nextLine();
      while (names.hasNextLine()) {
         vertices.put(names.nextLine(), count);
         count++;
      }
   }
   
   public void readGrid(String fileName) throws FileNotFoundException {
      Scanner gridd = new Scanner(new File(fileName));
      int r = 0;
      gridd.nextLine();
      while(gridd.hasNextLine()) {
         ArrayList<String> s = new ArrayList<String>(Arrays.asList(gridd.nextLine().split("[., \"!?]")));
         for (int c = 0; c < s.size(); c++) {
            if (s.get(c) != "") {
               grid[r][c] = Integer.parseInt(s.get(c));
            }
         }
         r++;
      }
   }
   
   public void displayVertices() {
      Iterator<String> it = vertices.keySet().iterator(); 
      
      while (it.hasNext()) {
         String temp = it.next();
         System.out.println(vertices.get(temp) + "-" + temp);
      }
   }
   
   public void allPairsReachability() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid.length; j++) {
            for (int k = 0; k < grid.length; k++) {
               if (grid[j][i] == 1 && grid[i][k] == 1) {
                  grid[j][k] = 1;
               }
            }
         }
      }
   }
   
   public int[][] getGrid() {
      return grid;
   }
   
   public void addEdge(int source, int target) {
      grid[source][target] = 1;
   }
   
   public void removeEdge(int source, int target) {
      grid[source][target] = 0;
   }
   
   public boolean isEdge(int from, int to) {
      if (grid[from][to] == 1) {
         return true;
      } else {
         return false;
      }
   }
   
   public String toString() {
      String toReturn = "";
      for (int r = 0; r < grid.length; r++) {
         for (int c = 0; c < grid.length; c++) {
            toReturn += grid[r][c];
            toReturn += " ";
         }
         toReturn += "\n";
      }
      return toReturn;
   }
   
   public int edgeCount() {
      int count = 0;
      for (int r = 0; r < grid.length; r++) {
         for (int c = 0; c < grid.length; c++) {
            if (grid[r][c] < 9999 && grid[r][c] > 0) {
               count++;
            }
         }
      }
      return count;
   }
   
   public List<Integer> getNeighbors(int source) {
      ArrayList<Integer> neighbors = new ArrayList<>();
      for (int i = 0; i < grid[source].length; i++) {
         if (grid[source][i] == 1) {
            neighbors.add(i);
         }
      }
      return neighbors;
   }
}
