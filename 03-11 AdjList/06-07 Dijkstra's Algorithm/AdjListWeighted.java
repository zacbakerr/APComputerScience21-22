// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces for 
 *              Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Neighbor implements Comparable<Neighbor>
{
   //2 Neighbors are equal if and only if they have the same name
   //implement all methods needed for a HashSet and TreeSet to work with Neighbor objects
   private final wVertex target;
   private final double edgeDistance;
   
   public Neighbor(wVertex t, double d) {
      target = t;
      edgeDistance = d;
   }
   
   //add all methods needed for a HashSet and TreeSet to function with Neighbor objects
   //use only target, not distances, since a vertex can't have 2 neighbors that have the same target
   //.........
   
   public wVertex getTarget() {
      return target;
   }
   
   public double getEdgeDistance() {
      return edgeDistance;
   }
   
   public wVertex getVertex() {
      return target;
   }
   
   public int compareTo(Neighbor other) {
      return (int)(edgeDistance-other.getEdgeDistance());
   }
   
   public String toString()
   {
      return target.getName() + " " + edgeDistance;  
   }
}

 /**************************************************************/
class PQelement implements Comparable<PQelement> { 
//used just for a PQ, contains a wVertex and a distance, also previous that is used for Dijksra 7
//compareTo is using the distanceToVertex to order them such that the PriorityQueue works
//will be used by the priority queue to order by distance
 
   private wVertex vertex;
   private Double distanceToVertex; 
   private wVertex previous; //for Dijkstra 7
      
   public PQelement(wVertex v, double d) {
      vertex = v;
      distanceToVertex = d;
      previous = null;
   }
   
   //getter and setter methods provided
   public wVertex getVertex() {
      return this.vertex;
   }
   
   public Double getDistanceToVertex() {
      return this.distanceToVertex;
   }
   
   public void setVertex(wVertex v) {
      this.vertex = v;
   }
   
   public void setDistanceToVertex(Double d) {
      this.distanceToVertex = d;
   }   
   
   public int compareTo(PQelement other) {
      //we assume no overflow will happen since distances will not go over the range of int
      return (int)(distanceToVertex - other.distanceToVertex);
   }
   
   public wVertex getPrevious()  //Dijkstra 7
   {
      return this.previous;
   }
   public void setPrevious(wVertex v) //Dijkstra 7
   {
      this.previous = v;
   } 
   
   //implement toString to match the sample output   
   public String toString()
   { 
      String toReturn = "";
      //your code here...
      toReturn += vertex.getName();
      toReturn += " ";
      toReturn += distanceToVertex;
      return toReturn;
   }
}

/********************* wVertexInterface ************************/
interface wVertexInterface 
{
   public String getName(); 
      
    //returns an arraylist of PQelements that store distanceToVertex to another wVertex
   public ArrayList<PQelement> getAlDistanceToVertex();
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v);
      
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v);
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double m);
   public Set<Neighbor> getNeighbors(); 
   public void addAdjacent(wVertex v, double d);  
   public String toString(); 
}

class wVertex implements Comparable<wVertex>, wVertexInterface 
{ 
   public static final double NODISTANCE = Double.POSITIVE_INFINITY; //constant to be used in class
   private final String name;
   private Set<Neighbor> neighbors;  
   private ArrayList<PQelement> alDistanceToVertex; //should have no duplicates, enforced by the getter/setter methods
  
   /* constructor, accessors, modifiers  */
   public wVertex(String namee){
      name = namee;
      neighbors = new HashSet<>();
      alDistanceToVertex = new ArrayList<>();
   } 
   
   /* 2 vertexes are equal if and only if they have the same name
      add all methods needed for a HashSet and TreeSet to function with Neighbor objects
      use only target, not distances, since a vertex can't have 2 neighbors that have the same target   
   */
   
   public String getName() {
      return name;
   }
      
    //returns an arraylist of PQelements that store distanceToVertex to another wVertex
   public ArrayList<PQelement> getAlDistanceToVertex() {
      return alDistanceToVertex;
   }
   
   //returns the PQelement that has the vertex equal to v
   public PQelement getPQelement(wVertex v) {
      PQelement q = null;
      for (PQelement p : alDistanceToVertex) {
         if ((p.getVertex()).equals(v)) {
            q = p;
         }
      }
      return q;
   }
   
   public double getDist(wVertex other) {
      for (PQelement p : alDistanceToVertex) {
         if (other.equals(p.getVertex())) {
            return p.getDistanceToVertex();
         }
      }
      return 1000.0;
   }
   
   public int compareTo(wVertex other) {
      return name.compareTo(other.getName());
   }
      
   /*
   postcondition: returns null if wVertex v is not in the alDistanceToVertex
                  or the distance associated with that wVertex in case there is a PQelement that has v as wVertex
   */
   public Double getDistanceToVertex(wVertex v) {
      Double d = null;
      for (PQelement p : alDistanceToVertex) {
         if ((p.getVertex()).equals(v)) {
            d = p.getDistanceToVertex();
         }
      }
      return d;
   }
   
   /*
   precondition:  v is not null
   postcondition: - if the alDistanceToVertex has a PQelement that has the wVertex component equal to v
                  it updates the distanceToVertex component to d
                  - if the alDistanceToVertex has no PQelement that has the wVertex component equal to v,
                  then a new PQelement is added to the alDistanceToVertex using v and d   
   */
   public void setDistanceToVertex(wVertex v, double m) {
      boolean there = false;
      for (PQelement p : alDistanceToVertex) {
         if ((p.getVertex()).equals(v)) {
            there = true;
            p.setDistanceToVertex(m);
         }
      }
      if (there == false) {
         PQelement n = new PQelement(v, m);
         alDistanceToVertex.add(n);
      }
   }
   
   public Set<Neighbor> getNeighbors() {
      return neighbors;
   }
   
   public void addAdjacent(wVertex v, double d) {
      Neighbor n = new Neighbor(v, d);
      neighbors.add(n); 
   }
   
    
   public String toString()
   { 
      String toReturn = name;
      toReturn += " "+ neighbors;
      toReturn += " List: " + alDistanceToVertex; 
      return toReturn;
   }
}

/*********************   Interface for Graphs 6:  Dijkstra ****************/
interface AdjListWeightedInterface 
{
   public Set<wVertex> getVertices();  
   public Map<String, wVertex> getVertexMap();  //this is just for codepost testing
   public wVertex getVertex(String vName);
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName);
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d);
   public void minimumWeightPath(String vertexName); // Dijstra's algorithm
   public String toString();  
}  

 /***********************  Interface for Graphs 7:  Dijkstra with Cities   */
interface AdjListWeightedInterfaceWithCities 
{       
   public List<String> getShortestPathTo(wVertex source, wVertex target);
   public void readData(String vertexNames, String edgeListData) ;
}
 
/****************************************************************/ 
/**************** this is the graph  ****************************/
public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, wVertex> vertexMap = new TreeMap<String, wVertex>();
   
   /* default constructor -- not needed!  */
   public List<String> getShortestPathTo(wVertex source, wVertex target) {      
      Stack<wVertex> backPath = new Stack<wVertex>();
      List<String> path = new ArrayList<String>();
      PQelement temp = target.getPQelement(target);
      PQelement temp2 = source.getPQelement(source);
      while(target.getDist(source) != 0 && temp.getPrevious() != null)
      {
         temp = temp.getPrevious().getPQelement(temp.getPrevious());
         backPath.push(temp.getVertex());
      }
      while(!backPath.isEmpty()){
         path.add(backPath.pop().getName());
      }
      path.add(target.getName());
      return path; 
   }
   
   public void readData(String vertexNames, String edgeListData) {
      try {
         Scanner inputFile = new Scanner(new File(vertexNames));
         inputFile.nextLine();
         while (inputFile.hasNextLine()) {
            addVertex(inputFile.nextLine());
         }
         
         Scanner edgess = new Scanner(new File(edgeListData));
         while (edgess.hasNextLine()) {
            Scanner s2 = new Scanner(edgess.nextLine());
            String v1 = "";
            String v2 = "";
            String v3 = "";
            for (int i = 0; i < 3; i++) {
               String s = s2.next();
               if (i == 0) {
                  v1 = s;
               } else if (i == 1) {
                  v2 = s;
               } else {
                  v3 = s;
               }
            }
            v3 += ".0";
            addEdge(v1, v2, Double.parseDouble(v3));
         }
      }
      catch (FileNotFoundException fnfe) {
         System.out.println(fnfe);
      }
   }
  
   /* similar to AdjList, but handles distances (weights) and wVertex*/ 
   public Set<wVertex> getVertices() {
      Set<wVertex> vertices = new HashSet<>();
      for(String s : vertexMap.keySet()) {
         vertices.add(vertexMap.get(s));
      }
      return vertices;
   }
    
   public Map<String, wVertex> getVertexMap() {  //this is just for codepost testing
      return vertexMap;
   }
   
   public wVertex getVertex(String vName) {
      return vertexMap.get(vName);
   }
   
   /* 
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(logn)
   */
   public void addVertex(String vName) {
      wVertex v = new wVertex(vName);

      if (!vertexMap.containsKey(vName)) {
         HashSet<wVertex> set = new HashSet<>();
         set.add(v);
         vertexMap.put(vName, v);
      }
      for (String s : vertexMap.keySet()) {
         wVertex curr = vertexMap.get(s);
         v.setDistanceToVertex(curr, curr.NODISTANCE);
         curr.setDistanceToVertex(v, curr.NODISTANCE);
      }
      v.setDistanceToVertex(v, 0);
   }
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
                     addEdge should work in O(1)
   */   
   public void addEdge(String source, String target, double d) {
      Neighbor nn = new Neighbor(getVertex(target), d);
      getVertex(source).getNeighbors().add(nn);
   }
   
   public void minimumWeightPath(String vertexName) { // Dijstra's algorithm
      PriorityQueue<PQelement> p = new PriorityQueue<>();
      ArrayList<wVertex> visited = new ArrayList<>();
      wVertex source = vertexMap.get(vertexName);
      p.add(source.getPQelement(source));
      while (!p.isEmpty()) {
         PQelement curr = p.remove();
         for (Neighbor n : curr.getVertex().getNeighbors()) {
            if (!visited.contains(curr.getVertex())) {
               p.add(n.getVertex().getPQelement(n.getVertex()));
               if (n.getEdgeDistance() + curr.getVertex().getDistanceToVertex(source) < source.getDist(n.getVertex())) {
                  source.setDistanceToVertex(n.getVertex(), n.getEdgeDistance() + curr.getVertex().getDistanceToVertex(source));
                  n.getVertex().setDistanceToVertex(source, n.getEdgeDistance() + curr.getVertex().getDistanceToVertex(source));
                  n.getVertex().getPQelement(n.getVertex()).setPrevious(curr.getVertex());
               }
            }
         }
         visited.add(curr.getVertex());
      }
   }
   
   public String toString()
   {
      String strResult = "";
      for(String vName : vertexMap.keySet())
      {
         strResult += vertexMap.get(vName) + "\n"; 
      }
      return strResult;
   }
   
}