package Dijkstra;

import java.util.*;

public class DijkstraPriorityQueueSolution {

  private int dist[];
  private Set<Integer> settled;
  private PriorityQueue<Node> pq;
  private final int V;
  private List<List<Node>> adj;

  public DijkstraPriorityQueueSolution(int V, List<List<Node>> adj) {
    this.adj = adj;
    this.V = V;
    dist = new int[V];
    settled = new HashSet<>();
    pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
  }

  public void dijkstra(int src) {
    for (int i = 0; i < V; i++)
      dist[i] = Integer.MAX_VALUE;

    pq.add(new Node(src, 0));
    dist[src] = 0;

    while (settled.size() != V && !pq.isEmpty()) {
      int u = pq.remove().node;
      if(settled.contains(u)) continue;
      settled.add(u);
      e_Neighbours(u);
    }

    printDistance(src);
  }

  public void e_Neighbours(int u) {

    int edgeDistance = -1;
    int newDistance = -1;

    for (int i = 0; i < adj.get(u).size(); i++) {
      Node v = adj.get(u).get(i);

      // If current node hasn't already been processed
      if (!settled.contains(v.node)) {
        edgeDistance = v.cost;
        newDistance = dist[u] + edgeDistance;

        // If new distance is cheaper in cost
        if (newDistance < dist[v.node])
          dist[v.node] = newDistance;

        // Add the current node to the queue
        pq.add(new Node(v.node, dist[v.node]));
      }
    }
  }

  public void printDistance(int src){
    for(int i = 0; i < dist.length; i++){
      System.out.println(src + " -> " + i + " = " + dist[i]);
    }
  }
}