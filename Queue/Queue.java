package com.wyh.queue;

public interface Queue<E> {
	
	int getSize();
	boolean isEmpty();
	E dequeue();
	E getfront();
	void enqueue(E e);
}
