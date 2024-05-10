package BinarySearchTree;

public class Client {
  public static void main(String[] args) {
    BSTConstructor tree = new BSTConstructor();
    tree.insert(10);
    tree.insert(20);
    tree.insert(5);
    tree.insert(10);
    tree.inorder();
  }
}
