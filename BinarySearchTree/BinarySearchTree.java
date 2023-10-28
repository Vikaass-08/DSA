package BinarySearchTree;


public class BinarySearchTree {
  public static void main(String[] args) {
    ConstructTree tree = new ConstructTree();
    tree.insert(10);
    tree.insert(20);
    tree.insert(5);
    tree.insert(10);
    tree.inorder();
  }

}


class ConstructTree {
  Node root;
  public ConstructTree() {
    this.root = null;
  }

  public Node insert(int value) {
    if(root == null){
      root = new Node(value);
      return root;
    }
    return insert_rec(value, root);
  }

  private Node insert_rec(int value, Node node){
    if(node == null) {
      return new Node(value);
    }
    if(value < node.value) {
      node.left = insert_rec(value, node.left);
    }
    else if(value > node.value) {
      node.right = insert_rec(value, node.right);
    }
    return node;
  }

  public Node delete(int value) {
    return delete_rec(value, root);
  }

  private Node delete_rec(int value, Node node) {
    if(node == null) return null;
    if(value < node.value){
      node.left = delete_rec(value, node.left);
    }
    else if(value > node.value) {
      node.right = delete_rec(value, node.right);
    }
    else {
      if(node.right == null) return node.left;
      else if(node.left == null) return node.right;
      Node smallestRight = findSmallest(node.right);
      node.value = smallestRight.value;
      node.right = delete_rec(smallestRight.value, node.right);
    }
    return node;
  }

  private Node findSmallest(Node node){
    while(node.left != null) node = node.left;
    return node;
  }

  public void inorder(){
    traverse(root);
  }

  private void traverse(Node node) {
    if (node != null) {
      traverse(node.left);
      System.out.println(node.value);
      traverse(node.right);
    }
  }

}


class Node {
  int value;
  Node left, right;
  public Node(int val){
    this.value = val;
    this.left = null;
    this.right = null;
  }
}