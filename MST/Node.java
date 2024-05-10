package MST;

import java.util.Comparator;

public class Node implements Comparator<Node> {
  private int v;
  private int weight;

  Node(int _v, int _w) { v = _v; weight = _w; }

  Node() {}

  int getV() { return v; }
  int getWeight() { return weight; }

  @Override
  public int compare(Node node1, Node node2)
  {
    if (node1.weight < node2.weight)
      return -1;
    if (node1.weight > node2.weight)
      return 1;
    return 0;
  }
}
