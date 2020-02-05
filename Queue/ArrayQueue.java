package com.wyh.queue;
/**
 *   Queue<E>   <-----ArrayQueue<E>
 *   void enqueue(E)		O(1)
 *   E dequeue()			O(n)
 *   E front()				O(1)
 *   int getSzie()			O(1)
 *   boolean isEmpty()		O(1)
 *   */
//简单的队列形式一般为数组队列
public class ArrayQueue<E> implements Queue<E> {
	private Array<E> array;
	
	public ArrayQueue(int capacity) {
		array=new Array<>(capacity);
	}
	public ArrayQueue() {
		array=new Array<>();
	}
	@Override
	public int getSize() {
		return array.getSize();
	}
	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}
	public int getCapacity() {
		return array.getCapacity();
	}
	@Override
	public void enqueue(E e) {
		array.addLast(e);
	}
	
	@Override
	public E dequeue() {
		return array.removeFirst();
	}
	@Override
	public E getfront() {
		return array.getFirst();
	}
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Queue: ");
		res.append("front[");
		for(int i=0;i<array.getSize();i++) {
			res.append(array.get(i));
			if(i !=array.getSize()-1) {
				res.append(",");
			}
		}
		res.append("]tail");
		return res.toString();
	}
	
	public static void main(String[] args) {
		ArrayQueue<Integer> queue=new ArrayQueue<>();
		for(int i=0;i<10;i++) {
			queue.enqueue(i);
			System.out.println(queue);
			if(i%3==2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}
	}
}
