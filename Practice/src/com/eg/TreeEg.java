//$Id$
package com.eg;

import java.util.Scanner;

public class TreeEg {

	public static void main(String[] args) {
		Tree tree=new Tree();
		int n,num;
		Scanner sc=new Scanner(System.in);
		System.out.println("give the count");
		n=sc.nextInt();
		System.out.println("enter "+n+" numbers");
		for(int i=0;i<n;i++) {
			num=sc.nextInt();
			tree.insertData(num);
		}
		System.out.println("Data Inserted");
		
		tree.show();
		
		System.out.println("enter number to search"+tree.size());
		/*
		 * n=sc.nextInt(); if(tree.searchTree(n)!=null) System.out.println("data = "+tree.searchTree(n).data); else System.out.println("No data found");
		 */
		sc.close();
	}

}
class Tree
{
	
	public class TreeNode{
		int data;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int data){
			this.data=data;
			this.left=null;
			this.right=null;
		}
	}
	TreeNode root;
	Tree(){
		root=null;
	}
	public TreeNode insert(TreeNode root,int data) {
		if(root==null) 
		{
			root=new TreeNode(data);
			return root;
		}
		if(root.data>data) 
			root.left=insert(root.left,data);
		else if(root.data<data) 
			root.right=insert(root.left,data);
		return root;
	}
	
	public TreeNode search(TreeNode root,int data) {
		if(root==null) System.out.println("no data found");
		else if(root.data==data) return root;
		else if(root.data>data) return search(root.left,data);
		return search(root.right,data);
	}
	public void inOrder(TreeNode root) {
		if (root != null) {
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
	}
	
	public void insertData(int data) {
		root=insert(root,data);
	}
	public TreeNode searchTree(int data) {
		return search(root, data);
	}
	public void show() {
		inOrder(root);
	}
	int size()
    {
        return size(root);
    }
 
    /* computes number of nodes in tree */
    int size(TreeNode node)
    {
        if (node == null)
            return 0;
        else
            return(size(node.left) + 1 + size(node.right));
    }
}
