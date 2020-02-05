package com.wyh.linkedlistqueue;

public interface Queue<E> {
	
	int getSize();
	boolean isEmpty();
	E dequeue();
	E getfront();
	void enqueue(E e);
}
