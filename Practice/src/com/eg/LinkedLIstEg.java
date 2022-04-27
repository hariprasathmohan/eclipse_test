//$Id$
package com.eg;

public class LinkedLIstEg {

	public static void main(String[] args) {
		LinkOperations lo=new LinkOperations();
		lo.insert(5);
		lo.insert(10);
		lo.insert(17);
		lo.InsertFirst(1);
		//lo.insertAt(100,2);
		//lo.deleteEnd();
		//lo.deleteStart();
		lo.deleteAt(3);
		lo.show();
		System.out.println(lo.getCount());
	}

}

class Node{
	int data;
	Node next;
}
class LinkOperations{
	Node head;
	void insert(int data) {
		Node n=new Node();
		n.data=data;
		n.next=null;
		if(head==null) head=n;
		else {
			Node n1=head;
			while(n1.next!=null) n1=n1.next;
			n1.next=n;
		}
	}
	void InsertFirst(int data) {
		Node n=new Node();
		n.data=data;
		n.next=head;
		head=n;
	}
	void insertAt(int data,int index) {
		if(index>getCount()) System.out.println("Index out of bound");
		else {
		Node node=head;
		Node n=new Node();
		n.data=data;
		for(int i=1;i<index-1;i++) {
			node=node.next;
		}
		n.next=node.next;
		node.next=n;
		}
	}
	void show() {
		if(head!=null) {
			Node n=head;
			while(n.next!=null) {
				System.out.println("data = "+n.data);
				n=n.next;
			}
			System.out.println("data = "+n.data);
		}
		else System.out.println("NO DATA FOUND");
	}
	int getCount() {
		int count=0;
		Node node=head;
		while(node.next!=null) {
			count++;
			node=node.next;
		}
		return ++count;
	}
	void deleteEnd() {
		if(head == null) {
			System.out.println("nothing here to delete");
		}
		else {
			Node node=head;
			Node prev= new Node();
			while(node.next!=null) {
				prev=node;
				node=node.next;
			}
			prev.next=null;
		}
	}
	void deleteStart() {
		if(head == null) {
			System.out.println("nothing here to delete");
		}
		else {
			head=head.next;
		}
	}
	void deleteAt(int index) {
		if(index>getCount()) System.out.println("Index out of bound");
		else if(index==1) deleteStart();
		else {
		Node node=head;
		Node prev= new Node();
		for(int i=0;i<index-1;i++) {
			prev=node;
			node=node.next;
		}
		prev.next=node.next;
		}
	}
}



