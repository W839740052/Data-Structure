package com.wyh.set;

public class LinkedListSet<E> implements Set<E> {//linkedlist数据类型并不要求可比性
	private LinkedList<E> list;
	
	public LinkedListSet() {
		list=new LinkedList<>();
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
	public void add(E e) {//链表中可以包含重复元素，然而set里不可以
		if(!list.contains(e))
			list.addFirst(e);
	}
	@Override
	public boolean contains(E e) {
		return list.contains(e);
	}
	@Override
	public  void remove(E e) {
		list.removeElement(e);
	}
}
