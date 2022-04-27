package com.eg;

import com.example.*;
public class AccessModefier {

	public static void main(String[] args) {
		AccesMod o=new AccesMod();
		System.out.println("outside package");
		System.out.println("i= default"+"\nj= "+o.j+"\nk= private"+"\nl= protected");
		Test t=new Test();
		t.display();
	}

}
class Test extends AccesMod{
	void display() {
		System.out.println("inherited class");
		System.out.println("i= default"+"\nj= "+j+"\nk= private"+"\nl= "+l);
	}
}
