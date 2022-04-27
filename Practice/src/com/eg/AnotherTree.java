//$Id$
package com.eg;

public class AnotherTree {

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        tree.inorder();
        System.out.println("\n Size of the tree is "+tree.size());

	}

}
class BinarySearchTree {

    class Node
    {
        int key;
        Node left, right;
 
        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }
 
    Node root;
    BinarySearchTree()
    {
         root = null;
    }
 
    void insert(int key)
    {
         root = insert(root, key);
    }
    Node insert(Node root, int key)
    {
 
        if (root == null)
        {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insert(root.left, key);
        else if (key > root.key)
            root.right = insert(root.right, key);
 
        return root;
    }
 
    void inorder()
    {
         inorder(root);
    }
 
    void inorder(Node root)
    {
        if (root != null) {
        	inorder(root.left);
            System.out.println(root.key);
            inorder(root.right);
        }
    }
    int size()
    {
        return size(root);
    }
 
    int size(Node node)
    {
        if (node == null)
            return 0;
        else
            return(size(node.left) + 1 + size(node.right));
    }
    
}