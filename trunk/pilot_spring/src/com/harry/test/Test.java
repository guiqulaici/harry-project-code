package com.harry.test;

import com.spring.mvc.domain.HArryCard;

public class Test {
	int i = 0;
	HArryCard card = new HArryCard();
	
	public void get() {
		i = 101;
		card.setId(100);
	}
	
	public void get1() {
		System.out.println(card.getId());
		System.out.println(i);
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		t.get1();
		t.get();
		
//		System.out.println(t.i);
//		System.out.println(t.card.getId());
	}
}
