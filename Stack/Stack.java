package com.wyh.stack;

public interface Stack<E> {
	
	boolean isEmpty();
	void push(E e);
	E pop();
	E peck();
	int getSize();
}
