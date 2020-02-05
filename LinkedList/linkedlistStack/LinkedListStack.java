package com.wyh.linkedlistStack;
/**
 * 实现链表栈的用法
 * */
public class LinkedListStack<E> implements Stack<E> {
	private LinkedList<E> list;
	
	public LinkedListStack() {
		list =new LinkedList<>();
		
	}
	@Override
	public int getSize() {
		return list.getSzie();
	}
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public void push(E e) {
		list.addFirst(e);
		
	}
	
	@Override
	public E pop() {
		return list.removeFirst();
	}
	
	@Override
	public E peck() {
		return list.getFirst();
	}
	
	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		res.append("Stack: top ");
		res.append(list);
		return res.toString();
	}
	public static void main(String[] args) {
		LinkedListStack<Integer> linkedliststack=new LinkedListStack<>();
		
		for(int i=0;i<5;i++) {
			linkedliststack.push(i);
			System.out.println(linkedliststack);
		}
		linkedliststack.pop();
		System.out.println(linkedliststack);
	}

}

