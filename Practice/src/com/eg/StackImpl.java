//$Id$
package com.eg;

public class StackImpl {
	public static void main(String[] args) {
		Stack s=new Stack();
		System.out.println(s.size());
		System.out.println(s.isEmpty());
		s.push(5);
		s.push(10);
		s.push(15);
		System.out.println(s.pop());
		System.out.println(s.peek());
		s.show();
		System.out.println(s.size());
		System.out.println(s.isEmpty());
		
	}
}
class Stack{
	int[] stack=new int[5];
	int top=0;
	
	void push(int data) {
		stack[top]=data;
		top++;
		
	}
	int pop() {
		int data;
		top--;
		data=stack[top];
		stack[top]=0;
		
		return data;
	}
	int peek(){
		int data;
		data=stack[top-1];
		return data;
	}
	boolean isEmpty() {
		if(top==0) return true;
		else return false;
	}
	int size() {
		return top;
	}
	void show() {
		for(int i=0;i<top;i++)
			System.out.println("data = "+stack[i]);
	}
	
}
