// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
   /* enter your code here  */
   public Vertex(String namee) {
      name = namee;
      adjacencies = new HashSet<>();
   }
   public String getName() {
      return name;
   }
   
   public HashSet<Vertex> getAdjacencies() {
      return adjacencies;
   }
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v) {
      adjacencies.add(v);
   }
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString() {
      String s = "";
      s += name + " ";
      s += "[";
      List<Vertex> temp = new ArrayList<>(adjacencies);
      for (int i = 0; i < temp.size(); i++) {
         s += (temp.get(i)).getName();
         if (i < (temp.size() - 1)) {
            s += " ";
         }
      }
      s += "]";
      return s;
   }
   
   public int hashCode() {
      return name.hashCode();
   }
   
   public boolean equals(Object o) {
      return name.equals((Vertex)o);
   }
   
   public int compareTo(Vertex other) {
      if (this.name.equals(other.getName())) {
         return 0;
      } else {
         return -1;
      }
   }
  
  
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 

}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public List<Vertex> depthFirstSearch(String name);
   public List<Vertex> breadthFirstSearch(String name);
   /*   extra credit methods */
//    public List<Vertex> depthFirstRecur(String name);
//    public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
      
   /* constructor is not needed because of the instantiation above */
  
   /* enter your code here  */
   public void readData(String cities, String edges) throws FileNotFoundException {
      Scanner inputFile = new Scanner(new File(cities));
      while (inputFile.hasNextLine()) {
         addVertex(inputFile.nextLine());
      }
      
      Scanner edgess = new Scanner(new File(edges));
      while (edgess.hasNextLine()) {
         Scanner s2 = new Scanner(edgess.nextLine());
         String v1 = "";
         String v2 = "";
         for (int i = 0; i < 2; i++) {
            String s = s2.next();
            if (i == 0) {
               v1 = s;
            } else {
               v2 = s;
            }
         }
         addEdge(v1, v2);
      }
   }
   
   public int edgeCount() { 
      int count = 0;
      for (Vertex value : vertexMap.values()) {
          HashSet<Vertex> adjs = value.getAdjacencies();
          for (Vertex v : adjs) {
            count++;
          }
      }
      return count;
   }
   
   public int vertexCount() {
      return vertexMap.size();
   }
   
   public boolean isReachable(String source, String target) {
      List<Vertex> reachables = depthFirstSearch(source);
      Vertex tar = getVertex(target);
      for (Vertex v : reachables) {
         if (tar.compareTo(v) == 0) {
            return true;
         }
      }
      return false;
   }
   
   public boolean isStronglyConnected() {
      for (String key : vertexMap.keySet()) {
         for (String key2 : vertexMap.keySet()) {
            boolean connected = isReachable(key, key2);
            if (connected == false) {
               return false;
            }
         }
      }
      return true;
   }
   
   public List<Vertex> depthFirstSearch(String name) {
      Vertex start = getVertex(name);
      List<Vertex> visited = new ArrayList<>();
      Stack<Vertex> toVisit = new Stack<>();
      HashSet<Vertex> adj = start.getAdjacencies();
      for (Vertex v : adj) {
         toVisit.add(v);
      }
      while (!toVisit.isEmpty()) {
         Vertex curr = toVisit.pop();
         boolean push2 = true;
         for (Vertex v : visited) {
            if (curr.compareTo(v) == 0) {
               push2 = false;
            }
         }
         if (!visited.contains(curr) && push2 == true) {
            visited.add(curr);
         }
         HashSet<Vertex> adj2 = curr.getAdjacencies();
         for (Vertex v2 : adj2) {
            boolean push = true;
            for (Vertex v : toVisit) {
               if (v2.compareTo(v) == 0) {
                  push = false;
               }
            }
            boolean push3 = true;
            for (Vertex v : visited) {
            if (v2.compareTo(v) == 0) {
               push3 = false;
            }
         }
            if (push == true && push3 == true && !visited.contains(v2) && !toVisit.contains(v2)) {
               toVisit.push(v2);
            }
         }
      }
      return visited;
   }
   
   public List<Vertex> breadthFirstSearch(String name) {
      List<Vertex> visited = new ArrayList<>();
      Queue<Vertex> toVisit = new LinkedList<>();
      Vertex start = getVertex(name);
      HashSet<Vertex> adj = start.getAdjacencies();
      for (Vertex v : adj) {
         toVisit.add(v);
      }
      while (!toVisit.isEmpty()) {
         Vertex curr = toVisit.remove();
         if (!visited.contains(curr)) {
            visited.add(curr);
         }
         HashSet<Vertex> adj2 = curr.getAdjacencies();
         for (Vertex v2 : adj2) {
            if (!visited.contains(v2)) {
               toVisit.add(v2);
            }
         }

      }
      return visited;
   }
   
   public Set<Vertex> getVertices() {
      Set<Vertex> vertices = new HashSet<>();
      for(String s : vertexMap.keySet()) {
         vertices.add(vertexMap.get(s));
      }
      return vertices;
   }
   
   public Vertex getVertex(String vName) {
      return vertexMap.get(vName);
   }
   
   public Map<String, Vertex> getVertexMap() {
      return vertexMap;
   
   }  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName) {
      if (!vertexMap.containsKey(vName)) {
         Vertex v = new Vertex(vName);
         HashSet<Vertex> set = new HashSet<>();
         set.add(v);
         vertexMap.put(vName, v);
      }
   }
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target) {
      Vertex curr = vertexMap.get(source);
      curr.addAdjacent(vertexMap.get(target));
   }
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString() {
      String s = "";
      Iterator<String> it = vertexMap.keySet().iterator(); 
      while (it.hasNext()) {
         Vertex temp = vertexMap.get(it.next());
         s += temp.toString();
         if (it.hasNext()) {
            s += "\n";
         }
      }
      return s;
   }
}


