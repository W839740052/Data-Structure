package com.wyh.priorityqueue;
/**
 * 用最大堆来实现优先队列
 * */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
	
	private MaxHeap<E> maxheap;
	
	public PriorityQueue() {
		maxheap=new MaxHeap<>();
	}
	@Override
	public int getSize() {
		return maxheap.size();
	}
	@Override 
	public boolean isEmpty() {
		return maxheap.isEmpty();
	}
	@Override
	public E getfront() {
		return maxheap.findMax();
	}
	@Override
	public void enqueue(E e) {
		maxheap.add(e);
	}
	@Override
	public E dequeue() {
		return maxheap.extractMax();
	}
}
