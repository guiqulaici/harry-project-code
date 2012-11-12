package com.harry.test;

public class TestJava {
	int a = 1;
	String[] str = {"a","b"};
	public void add() {
		a++;
	}
	
	public void edit() {
		str[0] = "nb";
	}
	
	public static void main(String[] args) {
		TestJava tj = new TestJava();
		tj.add();
		System.out.println(tj.a);
		tj.edit();
		System.out.println(tj.str[0]);
	}
}
