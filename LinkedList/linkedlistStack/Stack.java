package com.wyh.linkedlistStack;

public interface Stack<E> {
	
	boolean isEmpty();
	
	E pop();
	E peck();
	int getSize();
	void push(E e);
}
