package com.wyh.linkedlistqueue;

public class LinkedListQueue<E> implements Queue<E> {
	private class Node{
		public E e;
		public Node next;
		
		public Node(E e, Node next) {
			this.e=e;
			this.next=next;
		}
		public Node(E e) {
			this(e,null);
		}
		public Node() {
			this(null,null);
		}
		@Override
		public String toString() {
			return e.toString();
		}
	}
	
	private Node head,tail;
	private int size;
	
	public LinkedListQueue() {
		head=null;
		tail=null;
		size=0;
	}
	@Override
	public int getSize() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	@Override
	//对于链表的入栈，从链表的尾部进行，因为链表尾部的删除操作比较难，栈的尾部只可以添加，因此解决了链表尾部的删除操作
	public void enqueue(E e) {
		if(tail==null) {
			tail=new Node(e);
			head=tail;
		}else {
			tail.next=new Node(e);
			tail=tail.next;
		}
		size++;
	}
	
	@Override
	public E dequeue() {
		if(isEmpty()) 
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		Node retNode=head;
		head=head.next;
		retNode.next=null;
		if(head==null) 
			tail=null;
		return retNode.e;
	}
	@Override
	public E getfront() {
		if(isEmpty())
			throw new IllegalArgumentException("Queue is empty.");
		return head.e;
	}
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Queue: front ");//链表头，负责队列的出队
		for(Node cur=head;cur!=null; cur=cur.next) 
			res.append(cur+"->");
		res.append("NULL tail");//链表尾，负责队列的入队
		return res.toString();
		
	}
	public static void main(String [] args) {
		LinkedListQueue<Integer> linkedlistqueue=new LinkedListQueue<>();
		for(int i=0;i<10;i++) {
			linkedlistqueue.enqueue(i);
			System.out.println(linkedlistqueue);
			if(i%3==2) {
				linkedlistqueue.dequeue();
				System.out.println(linkedlistqueue);
			}
		}
		
	}
}
